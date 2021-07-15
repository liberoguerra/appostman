package com.finconsgroup.appostman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finconsgroup.appostman.model.Bilionaire;
import com.finconsgroup.appostman.repository.BillionaireRepository;

@Service
public class BillionaireService {

	@Autowired
	private BillionaireRepository repository;
	
	
	public List<Bilionaire> list() {
		return repository.findAll();
	}
	
}
