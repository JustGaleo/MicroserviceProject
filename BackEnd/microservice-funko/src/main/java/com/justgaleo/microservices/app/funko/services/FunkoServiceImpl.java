package com.justgaleo.microservices.app.funko.services;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.justgaleo.microservices.app.funko.models.entity.Funko;
import com.justgaleo.microservices.app.funko.models.repository.IFunkoRepository;
import com.justgaleo.microservices.commons.services.CommonServiceImpl;

@Service
public class FunkoServiceImpl extends CommonServiceImpl<Funko, IFunkoRepository> implements IFunkoService{

	@Override
	@Transactional(readOnly = true)
	public List<Funko> findByNameOrBrand(String term) {
		return repository.findByNameOrBrand(term);
	}

}
