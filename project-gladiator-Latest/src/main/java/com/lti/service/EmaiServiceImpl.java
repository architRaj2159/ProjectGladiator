package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmaiServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(SimpleMailMessage email) {
		try{
			mailSender.send(email);
			return true;
		}catch(MailException e) {
			return false;
		}
	}
	
	public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("nk.theraja@gmail.com");
            message.setTo("niihalrai@gmail.com");
            message.setSubject("Hi");
            message.setText("Testing mail");

            mailSender.send(message);
            
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

}
