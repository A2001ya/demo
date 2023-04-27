package com.springboot.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.sms.entity.Student;
import com.springboot.sms.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository service;
	
	public List<Student> listAll(){
		
		return service.findAll();
		
	}

}
