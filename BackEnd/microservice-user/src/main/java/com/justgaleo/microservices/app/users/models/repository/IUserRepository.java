package com.justgaleo.microservices.app.users.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.justgaleo.microservices.app.users.models.entity.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {

	@Query("select c from User c join fetch c.funkos a where a.id=?1")
	public List<User> findUserByFunkoId(Long id);
	
}
