package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Email;
import com.example.services.EmailService;

@RestController
@RequestMapping("/mail")
public class EmailController {

	@Autowired
	EmailService emailService;
	
	
	@PostMapping("/send/{mail}")
	public String sendMail(@PathVariable String mail,@RequestBody Email email)
	{
		emailService.sendMail(mail, email);
		return "Send Mail";
	}

}
