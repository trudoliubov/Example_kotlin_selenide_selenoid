package utils;

import javax.mail.*;
import java.util.Properties;

public class DeleteMails {
    public void delete(String host, String port, String username, String password)
    {
            //create properties field
            Properties properties = new Properties();

            properties.put("mail.imap.host", host);
            properties.put("mail.imap.port", port);
            properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.setProperty("mail.imap.socketFactory.fallback", "false");
            properties.setProperty("mail.imap.socketFactory.port", String.valueOf(port));
            Session emailSession = Session.getDefaultInstance(properties);
        try {
            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("imap");

            store.connect(username, password);

            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_WRITE);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();

            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];
                if (message.getSubject().contains("Ваш код подтверждения")) {
                    message.setFlag(Flags.Flag.DELETED, true);
                }
                if (message.getSubject().contains("Разрешение в")) {
                    message.setFlag(Flags.Flag.DELETED, true);
                }
                //message.setFlag(Flags.Flag.DELETED, true);
            }

            //close the store and folder objects
            boolean expunge = true;
            emailFolder.close(expunge);
            store.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
