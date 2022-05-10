package com.justgaleo.microservices.app.users.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.justgaleo.microservices.app.funko.models.entity.Funko;
import com.justgaleo.microservices.app.users.models.entity.User;
import com.justgaleo.microservices.app.users.services.IUserService;
import com.justgaleo.microservices.commons.controllers.CommonController;

@RestController
public class UserController extends CommonController<User, IUserService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> modifyUser(@RequestBody User user, @PathVariable Long id) {
		Optional<User> o = service.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		User userDb = o.get();
		userDb.setName(user.getName());
		userDb.setLastName(user.getLastName());
		userDb.setEmail(user.getEmail());

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userDb));
	}

	@PutMapping("/{id}/assign-funkos")
	public ResponseEntity<?>assignFunkos(@RequestBody List<Funko> funkos, @PathVariable Long id){
		Optional<User> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		User userDb = o.get();
		funkos.forEach(a->{
			userDb.addFunko(a);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userDb));
	}

	@PutMapping("/{id}/remove-funko")
	public ResponseEntity<?>removeFunko(@RequestBody Funko funko, @PathVariable Long id){
		Optional<User> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		User userDb = o.get();

		userDb.removeFunko(funko); 

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userDb));
	}
	
	@GetMapping("serchByFunkoId/{id}")
	public ResponseEntity<?> searchByFunkoId(@PathVariable Long id){
		return ResponseEntity.ok(service.findUserByFunkoId(id));
	}
	

}
