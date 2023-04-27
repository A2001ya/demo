package com.springboot.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.sms.entity.Compte;
import com.springboot.sms.repository.CompteRepository;



@Service
public class CompteService {
	@Autowired
	private CompteRepository service;
	
	public List<Compte> listAll(){
		
		return service.findAll();
		
	}

}
