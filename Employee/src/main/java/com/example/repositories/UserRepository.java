package com.example.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@RestResource(exported=false)
	<S extends User> S save(S entity);	

	@RestResource(exported=false)
	Optional<User> findByEmail(String email);
}
