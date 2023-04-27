package com.springboot.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.sms.entity.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {
	
	@Query("select count(id) from Departement")
	public String DepartementNBR();

}
