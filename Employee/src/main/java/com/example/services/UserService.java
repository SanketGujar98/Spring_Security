package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.models.User;
import com.example.repositories.UserRepository;
import com.example.responsewrapper.AllResponseWrapper;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	
	public ResponseEntity<?> userReg(User user)
	{
		String email=user.getEmail();
		String password=user.getPassword();
		String confpassword=user.getConfpassword();
		
		boolean email_present=userRepository.findByEmail(email).isPresent();
		
		if(email_present)
		{
			throw new ResponseStatusException(HttpStatus.FOUND,"Email Already Exists.");
		}
		else if(! password.equals(confpassword))
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Password doesn't match.");
		}
		else
		{
			user.setPassword(passwordEncoder.encode(password));
		    User user_data=userRepository.save(user);
		    AllResponseWrapper arw=new AllResponseWrapper();
		    arw.setData(user_data);
		    arw.setMessage("User Register Sucessfully.");
		    return new ResponseEntity<>(arw,HttpStatus.OK);
		}
	}
}
