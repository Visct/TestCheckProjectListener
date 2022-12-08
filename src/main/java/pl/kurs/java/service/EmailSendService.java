package pl.kurs.java.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class EmailSendService {

    private final JavaMailSender javaMailSender;

    public void sendSimpleMessage(String email, File file) throws MessagingException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("testcheckmaster@gmail.com");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Test");
        mimeMessageHelper.setText("text");
        mimeMessageHelper.addAttachment("dirCompressed.zip", new ByteArrayResource(Files.readAllBytes(file.toPath())));

        javaMailSender.send(mimeMessage);
    }
}
