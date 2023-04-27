package com.springboot.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.sms.entity.Element;

import com.springboot.sms.repository.ElementRepository;

@Service
public class ElementService {

	@Autowired
	private ElementRepository service;
	
	public List<Element> listAll(){
		
		return service.findAll();
		
	}
}
