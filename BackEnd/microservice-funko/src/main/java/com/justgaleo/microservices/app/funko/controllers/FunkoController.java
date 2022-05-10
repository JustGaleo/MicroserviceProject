package com.justgaleo.microservices.app.funko.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.justgaleo.microservices.app.funko.models.entity.Funko;
import com.justgaleo.microservices.app.funko.services.IFunkoService;
import com.justgaleo.microservices.commons.controllers.CommonController;

@RestController
public class FunkoController extends CommonController<Funko, IFunkoService>{
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modifyFunko(@Valid @RequestBody Funko funko, BindingResult result,  @PathVariable Long id){
		if(result.hasErrors()) {
			return this.validate(result);
		}
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

	@PostMapping("/create-with-pic")
	public ResponseEntity<?> createWithPicture(@Valid Funko funko, BindingResult result, @RequestParam MultipartFile archive) throws IOException {
		if(!archive.isEmpty()) {
			funko.setPicture(archive.getBytes());
		}
		return super.create(funko, result);
	}
	
	@PutMapping("/modify-with-pic/{id}")
	public ResponseEntity<?> modifyFunkoWithPicture(@Valid Funko funko, BindingResult result, @PathVariable Long id, @RequestParam MultipartFile archive) throws IOException{
		if(result.hasErrors()) {
			return this.validate(result);
		}
		Optional<Funko> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Funko funkoDb = o.get();
		funkoDb.setName(funko.getName());
		funkoDb.setBrand(funko.getBrand());
		if(!archive.isEmpty()) {
			funkoDb.setPicture(archive.getBytes());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(funkoDb));
	}
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> viewPic(@PathVariable Long id){
		Optional<Funko> o = service.findById(id);
		if(o.isEmpty() || o.get().getPicture() == null) {
			return ResponseEntity.notFound().build();
		}
		
		Resource img = new ByteArrayResource(o.get().getPicture());
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(img);
	}
	

}
