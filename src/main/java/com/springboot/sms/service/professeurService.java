package com.springboot.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.springboot.sms.entity.professeur;
import com.springboot.sms.repository.professeurRepository;
@Service
public class professeurService {

	@Autowired
	private professeurRepository service;
	
	public List<professeur> listAll(){
		
		return service.findAll();
		
	}
}
