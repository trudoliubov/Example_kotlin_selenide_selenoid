package utils

import java.util.*
import javax.mail.Folder
import javax.mail.MessagingException
import javax.mail.NoSuchProviderException
import javax.mail.Session

object CheckingMails {
    var code: String? = null
    fun check(host: String, port: String, user: String, password: String): String? {
        try {
            //create properties field
            val properties = Properties()
            properties["mail.imap.host"] = host
            properties["mail.imap.port"] = port
            properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
            properties.setProperty("mail.imap.socketFactory.fallback", "false")
            properties.setProperty("mail.imap.socketFactory.port", port)
            val emailSession = Session.getDefaultInstance(properties)

            //create the POP3 store object and connect with the pop server
            val store = emailSession.getStore("imap")
            store.connect(host, user, password)

            //create the folder object and open it
            val emailFolder = store.getFolder("INBOX")
            emailFolder.open(Folder.READ_WRITE)

            // retrieve the messages from the folder in an array and print it
            val messages = emailFolder.messages
            var i = 0
            val n = messages.size
            while (i < n) {
                val message = messages[i]
                code = message.subject.replace("\\D".toRegex(), "") // replacing all characters except digits
                i++
            }

            //close the store and folder objects
            val expunge = true
            emailFolder.close(expunge)
            store.close()
        } catch (e: NoSuchProviderException) {
            e.printStackTrace()
        } catch (e: MessagingException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return code
    }
}