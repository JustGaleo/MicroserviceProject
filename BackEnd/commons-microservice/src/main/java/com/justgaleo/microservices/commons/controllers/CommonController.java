package com.justgaleo.microservices.commons.controllers;

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
import org.springframework.web.bind.annotation.RequestBody;

import com.justgaleo.microservices.commons.services.ICommonService;

public class CommonController<E, S extends ICommonService<E>> {
	
	@Autowired
	protected S service;
	
	@GetMapping
	public ResponseEntity<?> readAll(){
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> readById(@PathVariable Long id){
		Optional<E> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(o.get());
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody E entity, BindingResult result){
		if(result.hasErrors()) {
			return this.validate(result);
		}
		E entityDb = service.save(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	protected ResponseEntity<?> validate(BindingResult result) {
		Map<String, Object> errors = new HashMap<>();
		result.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errors);
	}
}
