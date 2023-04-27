package com.springboot.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.sms.entity.Element;



public interface ElementRepository extends JpaRepository<Element, Integer> {

	@Query("SELECT s FROM Element s WHERE "
			+ "CONCAT(s.id, s.NomElement,s.module.nomModule, s.coef, s.filiere.nomFiliere)"
			+" LIKE %?1%")
	public List<Element> search(String keyword);
	
	
	@Query("select count(id) from Element ")
	public String ElementsNBR();
	
	
	
}
