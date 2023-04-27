package com.springboot.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.sms.entity.Filiere;



public interface FiliereRepository extends JpaRepository<Filiere, Integer>{

 @Query("SELECT s FROM Filiere s WHERE "
			+ "CONCAT(s.id, s.nomFiliere, s.departement.name)"
			+" LIKE %?1%")
	public List<Filiere> search(String keyword);
	
	
	@Query("select count(id) from Filiere ")
	public String FilieressNBR();
	
	
	@Query("SELECT s.departement.name as departement , COUNT(s.departement.name ) as nbDepartement FROM Filiere s  GROUP BY s.departement.name")
	public List<?> reportByDepartement();
}
