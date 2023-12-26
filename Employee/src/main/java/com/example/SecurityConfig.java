package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.services.UserLoginService;

import jakarta.servlet.DispatcherType;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity.csrf(httpreq->httpreq.disable());
		httpSecurity.authorizeHttpRequests(httpreq->httpreq
				.dispatcherTypeMatchers(DispatcherType.ERROR,DispatcherType.FORWARD).permitAll()
				.requestMatchers(HttpMethod.POST,"/users/register").permitAll()
				.requestMatchers(HttpMethod.POST,"/mail/**").permitAll()
				.requestMatchers(HttpMethod.GET,"/employees").hasAuthority("employee")
				.requestMatchers(HttpMethod.POST,"/employees").hasAuthority("hr")
				.anyRequest().authenticated()).httpBasic(Customizer.withDefaults())
		.authenticationProvider(daoAuthenticationProvider());
		        
		return httpSecurity.build();
	}
	
	@Autowired
	UserLoginService userLoginService;
	
	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider()
	{
		DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
		dao.setUserDetailsService(userLoginService);
		dao.setPasswordEncoder(passwordEncoder());
		return dao;
	}
}
