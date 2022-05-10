package com.justgaleo.microservices.commons.services;

import java.util.Optional;

public interface ICommonService<E> {
	
	public Iterable<E> findAll();
	
	public Optional<E> findById(Long id);
	
	public E save(E e);
	
	public void deleteById(Long id);
	

}
