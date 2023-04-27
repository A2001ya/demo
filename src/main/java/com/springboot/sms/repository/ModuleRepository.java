package com.springboot.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.sms.entity.Module;
import com.springboot.sms.entity.Student;

public interface ModuleRepository extends JpaRepository<Module, Integer> {

	@Query("SELECT s FROM Module s WHERE "
			+ "CONCAT(s.id, s.nomModule,s.F.nomFiliere)"
			+" LIKE %?1%")
	public List<Module> search(String keyword);
	
	
	@Query("select count(id) from Module ")
	public String ModulesNBR();
	
	/*@Query("SELECT s.departement.name as departement , COUNT(s.departement.name ) as nbDepartement FROM Student s  GROUP BY s.departement.name")
	public List<?> reportByDepartement();*/
	
}
