package com.justgaleo.microservices.app.users.services;

import java.util.List;
import java.util.Optional;

import com.justgaleo.microservices.app.users.models.entity.User;
import com.justgaleo.microservices.commons.services.ICommonService;

public interface IUserService extends ICommonService<User>{

	public List<User> findUserByFunkoId(Long id);
	
	public Optional<User> findByEmail(String email);
	
}
