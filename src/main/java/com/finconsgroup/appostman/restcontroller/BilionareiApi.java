package com.finconsgroup.appostman.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.finconsgroup.appostman.model.Bilionaire;
import com.finconsgroup.appostman.service.BillionaireService;

@RestController
@RequestMapping("/api/bill")
public class BilionareiApi {

	@Autowired
	private BillionaireService service;
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<Object> list(){
		List<Bilionaire> list = service.list();
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}
	
}
