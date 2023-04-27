package com.springboot.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.sms.data.GraphData;
import com.springboot.sms.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	@Query("SELECT s FROM Student s WHERE "
			+ "CONCAT(s.id, s.firstName, s.lastName, s.departement.name)"
			+" LIKE %?1%")
	public List<Student> search(String keyword);
	
	
	@Query("select count(id) from Student ")
	public String StudentsNBR();
	
	
	@Query("SELECT s.departement.name as departement , COUNT(s.departement.name ) as nbDepartement FROM Student s  GROUP BY s.departement.name")
	public List<?> reportByDepartement();
}
