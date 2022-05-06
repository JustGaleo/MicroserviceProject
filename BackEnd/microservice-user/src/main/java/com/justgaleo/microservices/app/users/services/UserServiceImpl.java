package com.justgaleo.microservices.app.users.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.justgaleo.microservices.app.users.models.entity.User;
import com.justgaleo.microservices.app.users.models.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired(required=true)
	private IUserRepository repository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<User> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public User save(User user) {
		return repository.save(user);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}


}
