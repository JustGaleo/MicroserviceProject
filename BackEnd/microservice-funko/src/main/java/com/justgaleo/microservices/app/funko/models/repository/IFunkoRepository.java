package com.justgaleo.microservices.app.funko.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.justgaleo.microservices.app.funko.models.entity.Funko;

public interface IFunkoRepository extends CrudRepository<Funko, Long>{
	
	@Query("select a from Funko a where a.name like %?1% or a.brand like %?1%")
	public List<Funko> findByNameOrBrand(String term);

}
