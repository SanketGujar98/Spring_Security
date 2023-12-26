package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.models.Email;

@Service
public class EmailService {

	@Autowired
	JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String fromMail;
	
	public void sendMail(String mail,Email email)
	{
		SimpleMailMessage smm=new SimpleMailMessage();
		smm.setFrom(fromMail);
		smm.setSubject(email.getSubject());
		smm.setText(email.getMessage());
		smm.setTo(mail);
		
		javaMailSender.send(smm);
	}
}
