package com.example.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.models.User;
import com.example.models.UserLogin;
import com.example.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserLoginService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	   User user_found=userRepository.findByEmail(username).orElseThrow(()->{
	   throw new  UsernameNotFoundException("Email Not Found.");
		});
	  
	String userName= user_found.getEmail();
	String password= user_found.getPassword();
	List<String>  roles=user_found.getRoles();
	Collection<GrantedAuthority> authorities=new ArrayList<>();
	for(String role :roles)
	{
		authorities.add(new SimpleGrantedAuthority(role));
	}
	
	UserLogin user=new UserLogin(userName, password, authorities);
		return user;
	}

}
