package com.springboot.sms.coontroller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.lowagie.text.DocumentException;
import com.springboot.sms.entity.Departement;
import com.springboot.sms.entity.Student;
import com.springboot.sms.repository.DepartementRepository;
import com.springboot.sms.repository.StudentRepository;
import com.springboot.sms.service.StudentService;
import com.springboot.sms.util.StudentPDFExporter;

@Controller
public class StudentController {
	
	@Autowired
	private StudentRepository studenttrepo;
	
	@Autowired
	private StudentService repo;
	
	@Autowired
	private DepartementRepository departementRepo;
	@GetMapping("/students/Home")
	public String Home() {
		
		return "index";
	}
	@GetMapping("/students/new")
	public String showNewStudentForm(Model model) {
		List<Departement> listDepartements =departementRepo.findAll();
		
		model.addAttribute("student", new Student());
		model.addAttribute("listDepartements", listDepartements);
		model.addAttribute("added", true);
		
		return "student_form";
	}
	
	@PostMapping("/students/save")
	public String saveStudent(Student student) {
		studenttrepo.save(student);
		return "redirect:/student";
	}
	
	@GetMapping("/student")
	public String listStudent(Model model) {
		
		List<Student> listStudents = studenttrepo.findAll();
		model.addAttribute("listStudents", listStudents);
		
		return "student";
	}
	
	@GetMapping("/student/search")
	public String SearchStudent(Model model, @Param("keyword") String keyword) {
		List<Student> listStudents = studenttrepo.search(keyword);
		model.addAttribute("listStudents", listStudents);
		model.addAttribute("keyword", keyword);
		
		return "student";
	}
	
	@GetMapping("/students/edit/{id}")
	public String showEditStudentForm(@PathVariable("id") Integer id, Model model) {
		Student student =studenttrepo.findById(id).get();
		model.addAttribute("student", student);
		model.addAttribute("edit", true);
		
		List<Departement> listDepartements =departementRepo.findAll();
		
		model.addAttribute("listDepartements", listDepartements);
		return "student_form";
	}
	
	@GetMapping("/students/delete/{id}")
	public String deleteStudent(@PathVariable("id") Integer id, Model model) {
		
		studenttrepo.deleteById(id);
		return "redirect:/student";
	}
	
	@GetMapping("/student/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Students_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Student> listStudents = repo.listAll();
        
        StudentPDFExporter exporter = new StudentPDFExporter(listStudents);
        exporter.export(response);
        
      

}
}
