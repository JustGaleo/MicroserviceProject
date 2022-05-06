package com.justgaleo.microservices.app.users.services;

import java.util.Optional;

import com.justgaleo.microservices.app.users.models.entity.User;

public interface IUserService {
	
	public Iterable<User> findAll();
	
	public Optional<User> findById(Long id);
	
	public User save(User user);
	
	public void deleteById(Long id);
	

}
