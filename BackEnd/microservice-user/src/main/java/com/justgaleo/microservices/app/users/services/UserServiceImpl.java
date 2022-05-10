package com.justgaleo.microservices.app.users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.justgaleo.microservices.app.users.models.entity.User;
import com.justgaleo.microservices.app.users.models.repository.IUserRepository;
import com.justgaleo.microservices.commons.services.CommonServiceImpl;

@Service
public class UserServiceImpl extends CommonServiceImpl<User, IUserRepository> implements IUserService{

	@Override
	@Transactional(readOnly = true)
	public List<User> findUserByFunkoId(Long id) {
		return repository.findUserByFunkoId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findByEmail(String email) {
		return repository.findUserByEmail(email);
	}



}
