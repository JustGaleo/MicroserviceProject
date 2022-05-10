package com.justgaleo.microservices.app.users.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.justgaleo.microservices.app.funko.models.entity.Funko;
import com.justgaleo.microservices.app.users.models.entity.User;
import com.justgaleo.microservices.app.users.services.IUserService;
import com.justgaleo.microservices.commons.controllers.CommonController;

@RestController
public class UserController extends CommonController<User, IUserService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> modifyUser(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) {
		if(result.hasErrors()) {
			return this.validate(result);
		}
		Optional<User> o = service.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		User userDb = o.get();
		userDb.setName(user.getName());
		userDb.setLastName(user.getLastName());
		userDb.setEmail(user.getEmail());
		userDb.setPassword(user.getPassword());

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
	
	@GetMapping("searchByFunkoId/{id}")
	public ResponseEntity<?> searchByFunkoId(@PathVariable Long id){
		return ResponseEntity.ok(service.findUserByFunkoId(id));
	}
	
	@GetMapping("logIn")
	public ResponseEntity<?> logIn(@RequestParam String email, @RequestParam String password){
		Optional<User> o = service.findByEmail(email);
		if(!password.equals(o.get().getPassword())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(true);
	}
}
