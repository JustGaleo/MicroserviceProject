package com.justgaleo.microservices.app.users.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.justgaleo.microservices.app.users.models.entity.User;
import com.justgaleo.microservices.app.users.services.IUserService;

@RestController
public class UserController {
	
	@Autowired
	private IUserService service;
	
	@GetMapping
	public ResponseEntity<?> readAllUsers(){
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> readUserById(@PathVariable Long id){
		Optional<User> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(o.get());
	}
	
	@PostMapping
	public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result){
		if(result.hasErrors()) {
			return this.validate(result);
		}
		User userDb = service.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userDb);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modifyUser(@RequestBody User user,  @PathVariable Long id){
		Optional<User> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		User userDb = o.get();
		userDb.setName(user.getName());
		userDb.setLastName(user.getLastName());
		userDb.setEmail(user.getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userDb));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	protected ResponseEntity<?> validate(BindingResult result) {
		Map<String, Object> errors = new HashMap<>();
		result.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errors);
	}
	

}
