package com.springboot.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.sms.entity.Filiere;

import com.springboot.sms.repository.FiliereRepository;

@Service
public class FiliereService {

	@Autowired
	private FiliereRepository service;
	
	public List<Filiere> listAll(){
		
		return service.findAll();
		
	}
}
