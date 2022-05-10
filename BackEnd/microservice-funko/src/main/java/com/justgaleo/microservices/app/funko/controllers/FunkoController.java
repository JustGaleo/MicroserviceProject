package com.justgaleo.microservices.app.funko.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.justgaleo.microservices.app.funko.models.entity.Funko;
import com.justgaleo.microservices.app.funko.services.IFunkoService;
import com.justgaleo.microservices.commons.controllers.CommonController;

@RestController
public class FunkoController extends CommonController<Funko, IFunkoService>{
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modifyFunko(@RequestBody Funko funko,  @PathVariable Long id){
		Optional<Funko> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Funko funkoDb = o.get();
		funkoDb.setName(funko.getName());
		funkoDb.setBrand(funko.getBrand());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(funkoDb));
	}
	
	@GetMapping("/filter/{term}")
	public ResponseEntity<?> filter(@PathVariable String term){
		return ResponseEntity.ok(service.findByNameOrBrand(term));
	}

}
