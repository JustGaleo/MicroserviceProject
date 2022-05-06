package com.justgaleo.microservices.app.users.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.justgaleo.microservices.app.users.models.entity.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {

}
