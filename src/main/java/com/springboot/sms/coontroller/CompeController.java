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
import com.springboot.sms.entity.Compte;
import com.springboot.sms.entity.Departement;
import com.springboot.sms.entity.Student;
import com.springboot.sms.repository.CompteRepository;
import com.springboot.sms.repository.DepartementRepository;
import com.springboot.sms.repository.StudentRepository;
import com.springboot.sms.service.CompteService;
import com.springboot.sms.service.StudentService;
import com.springboot.sms.util.StudentPDFExporter;

@Controller
public class CompeController {
	
	@Autowired
	private CompteRepository studenttrepo;
	
	@Autowired
	private CompteService repo;
	
	
	
	@GetMapping("/comptes/new")
	public String showNewStudentForm(Model model) {
		
		model.addAttribute("compte", new Compte());
		
		model.addAttribute("added", true);
		
		return "compte_form";
	}
	
	@PostMapping("/comptes/save")
	public String saveCompte(Compte compte) {
		studenttrepo.save(compte);
		return "redirect:/compte";
	}
	
	@GetMapping("/compte")
	public String listStudent(Model model) {
		
		List<Compte> listStudents = studenttrepo.findAll();
		model.addAttribute("listComptes", listStudents);
		
		return "compte";
	}
	
	@GetMapping("/compte/search")
	public String SearchStudent(Model model, @Param("keyword") String keyword) {
		List<Compte> listComptes = studenttrepo.search(keyword);
		model.addAttribute("listComptes", listComptes);
	    model.addAttribute("keyword", keyword);
	
	     return "Compte";
	}
	
	@GetMapping("/copmtes/edit/{id}")
	public String showEditStudentForm(@PathVariable("id") Integer id, Model model) {
		Compte Compte =studenttrepo.findById(id).get();
		model.addAttribute("compte", Compte);
		model.addAttribute("edit", true);
		
		
		return "compte_form";
	}
	
	@GetMapping("/comptes/delete/{id}")
	public String deleteStudent(@PathVariable("id") Integer id, Model model) {
		
		studenttrepo.deleteById(id);
		return "redirect:/compte";
	}
	
//	@GetMapping("/student/export/pdf")
//    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
//        response.setContentType("application/pdf");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//         
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=Students_" + currentDateTime + ".pdf";
//        response.setHeader(headerKey, headerValue);
//         
//        List<Compte> listStudents = repo.listAll();
//        
//        StudentPDFExporter exporter = new StudentPDFExporter(listStudents);
//        exporter.export(response);
//        
//      
//
//}
}
