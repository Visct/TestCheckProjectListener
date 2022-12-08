package pl.kurs.java.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import pl.kurs.java.service.EmailSendService;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.mail.MessagingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailEventListener {

    private final EmailSendService emailSendService;

    @JmsListener(destination = "zip-queue")
    public void subscribeEmailEvents(Message message) throws MessagingException, IOException, JMSException {
        BytesMessage bytesMessage = (BytesMessage) message;
        String email = bytesMessage.getStringProperty("email");
        int dataSize = (int) bytesMessage.getBodyLength();
        byte[] buffer = new byte[8192];
        bytesMessage.readBytes(buffer, dataSize);
        File outputFile = new File("dirCompressed.zip");
        try (FileOutputStream fileOutput = new FileOutputStream(outputFile)) {
            fileOutput.write(buffer);
        }
        emailSendService.sendSimpleMessage(email,outputFile);
        outputFile.delete();
    }
}
