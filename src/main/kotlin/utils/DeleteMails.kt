package utils

import java.util.*
import javax.mail.Flags
import javax.mail.Folder
import javax.mail.MessagingException
import javax.mail.Session

class DeleteMails {
    fun delete(host: String?, port: String, username: String?, password: String?) {
        //create properties field
        val properties = Properties()
        properties["mail.imap.host"] = host
        properties["mail.imap.port"] = port
        properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
        properties.setProperty("mail.imap.socketFactory.fallback", "false")
        properties.setProperty("mail.imap.socketFactory.port", port)
        val emailSession = Session.getDefaultInstance(properties)
        try {
            //create the POP3 store object and connect with the pop server
            val store = emailSession.getStore("imap")
            store.connect(username, password)

            //create the folder object and open it
            val emailFolder = store.getFolder("INBOX")
            emailFolder.open(Folder.READ_WRITE)

            // retrieve the messages from the folder in an array and print it
            val messages = emailFolder.messages
            var i = 0
            val n = messages.size
            while (i < n) {
                val message = messages[i]
                if (message.subject.contains("Ваш код подтверждения")) {
                    message.setFlag(Flags.Flag.DELETED, true)
                }
                if (message.subject.contains("Разрешение в")) {
                    message.setFlag(Flags.Flag.DELETED, true)
                }
                i++
            }

            //close the store and folder objects
            val expunge = true
            emailFolder.close(expunge)
            store.close()
        } catch (e: MessagingException) {
            e.printStackTrace()
        }
    }
}