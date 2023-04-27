package com.springboot.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.sms.entity.professeur;

public interface professeurRepository extends JpaRepository<professeur, Integer> {

	@Query("SELECT s FROM professeur s WHERE "
			+ "CONCAT(s.id, s.nom, s.prenom, s.departement.name)"
			+" LIKE %?1%")
	public List<professeur> search(String keyword);
	
	
	@Query("select count(id) from professeur ")
	public String professeursNBR();
	
	
	@Query("SELECT s.departement.name as departement , COUNT(s.departement.name ) as nbDepartement FROM professeur s  GROUP BY s.departement.name")
	public List<?> reportByDepartement();
}
