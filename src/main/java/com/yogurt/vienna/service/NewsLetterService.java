package com.yogurt.vienna.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NewsLetterService {

    @Value("${from}")
    private String fromEmail;

    @Value("${to}")
    private String toEmail;

    public void sendNewsLetter(String sendGridApiKey) {

        System.out.println(sendGridApiKey);

        Email from = new Email(fromEmail);
        Email to = new Email(toEmail);
        String subject = "Sending news letter for test";
        Content content = new Content("text/plain", "easy to do anywhere");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        //SendGrid sg = new SendGrid(System.getenv(sendGridApiKey)); 이건 작동 안함
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException e) {
            System.out.println("SendGrid IO Exception");
            e.printStackTrace();
        }

    }

}
