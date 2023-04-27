package com.springboot.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.sms.data.GraphData;
import com.springboot.sms.entity.Compte;
import com.springboot.sms.entity.Student;

public interface CompteRepository extends JpaRepository<Compte, Integer> {
	
	@Query("SELECT s FROM Compte s WHERE "
			+ "CONCAT(s.password, s.Username, s.prof.id)"
			+" LIKE %?1%")
	public List<Compte> search(String keyword);
	
	
//	@Query("select count(id) from Compte ")
//	public String StudentsNBR();
//	
//	
//	@Query("SELECT s.departement.name as departement , COUNT(s.departement.name ) as nbDepartement FROM Student s  GROUP BY s.departement.name")
//	public List<?> reportByDepartement();
}
