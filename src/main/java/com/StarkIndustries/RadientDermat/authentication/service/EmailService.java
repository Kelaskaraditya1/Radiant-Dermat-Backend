package com.StarkIndustries.RadientDermat.authentication.service;

import com.StarkIndustries.RadientDermat.keys.Keys;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@Component
public class EmailService {

//    @Value("${APP_PASSWORD}")
//    public String appPassword;

    public boolean verifyEmail(int otp,String email){

        var emailBody="Thank you for signing up! To complete your email verification, please use the One-Time Password (OTP) below:\n" +
                "\n" +
                "Your OTP: "+String.valueOf(otp)+"\n" +
                "\n" +
                "This OTP is valid for the next 10 minutes. Please do not share it with anyone.\n" +
                "\n" +
                "If you did not request this verification, please ignore this email.\n" +
                "\n" +
                "Best regards";

        boolean status = false;
        var properties = System.getProperties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable",true);
        properties.put("mail.smtp.auth",true);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Keys.APP_PASSWORD_EMAIL,System.getenv("APP_PASSWORD"));
            }
        });

        session.setDebug(true);

        try{
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(email));
            mimeMessage.setSubject("Verify for email for secure access!!");
            mimeMessage.setFrom(Keys.APP_PASSWORD_EMAIL);
            mimeMessage.setText(emailBody);

            Transport.send(mimeMessage);
            status=true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return status;
    }
}
