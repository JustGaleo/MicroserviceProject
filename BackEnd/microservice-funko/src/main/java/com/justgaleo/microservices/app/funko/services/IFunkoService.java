package com.justgaleo.microservices.app.funko.services;

import java.util.List;

import com.justgaleo.microservices.app.funko.models.entity.Funko;
import com.justgaleo.microservices.commons.services.ICommonService;

public interface IFunkoService extends ICommonService<Funko>{
	
	public List<Funko> findByNameOrBrand(String term);

}
