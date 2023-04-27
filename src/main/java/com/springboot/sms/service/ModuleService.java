package com.springboot.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.sms.entity.Module;

import com.springboot.sms.repository.ModuleRepository;

@Service
public class ModuleService {

	@Autowired
	private ModuleRepository service;
	
	public List<Module> listAll(){
		
		return service.findAll();
		
	}
}
