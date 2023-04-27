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
import com.springboot.sms.entity.professeur;
import com.springboot.sms.repository.DepartementRepository;

import com.springboot.sms.repository.professeurRepository;

import com.springboot.sms.service.professeurService;

import com.springboot.sms.util.professeurPDFExporter;
@Controller
public class professeurContoller {

	@Autowired
	private professeurRepository studenttrepo;
	
	@Autowired
	private professeurService repo;
	
	@Autowired
	private DepartementRepository departementRepo;
	
	@GetMapping("/professeurs/new")
	public String showNewStudentForm(Model model) {
		List<Departement> listDepartements =departementRepo.findAll();
		
		model.addAttribute("professeur", new professeur());
		model.addAttribute("listDepartements", listDepartements);
		model.addAttribute("added", true);
		
		return "professeur_from";
	}
	
	@GetMapping("/professeurs/save")
	public String saveprofesseur(professeur p) {
		studenttrepo.save(p);
		return "redirect:/professeur";
	}
	
	@GetMapping("/professeur")
	public String listprofesseur(Model model) {
		
		List<professeur> listprofesseurs = studenttrepo.findAll();
		model.addAttribute("listprofesseurs", listprofesseurs);
		
		return "professeur";
	}
	
	@GetMapping("/professeur/search")
	public String Searchprofesseur(Model model, @Param("keyword") String keyword) {
		List<professeur> listprofesseurs = studenttrepo.search(keyword);
		model.addAttribute("listprofesseurs", listprofesseurs);
		model.addAttribute("keyword", keyword);
		
		return "professeur";
	}
	
	@GetMapping("/professeurs/edit/{id}")
	public String showEditStudentForm(@PathVariable("id") Integer id, Model model) {
		professeur professeur =studenttrepo.findById(id).get();
		model.addAttribute("professeur", professeur);
		model.addAttribute("edit", true);
		
		List<Departement> listDepartements =departementRepo.findAll();
		
		model.addAttribute("listDepartements", listDepartements);
		return "professeur_from";
	}
	
	@GetMapping("/professeurs/delete/{id}")
	public String deleteprofesseur(@PathVariable("id") Integer id, Model model) {
		
		studenttrepo.deleteById(id);
		return "redirect:/professeur";
	}
	
	@GetMapping("/professeur/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=professeurs_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<professeur> listprofesseurs = repo.listAll();
        
        professeurPDFExporter exporter = new professeurPDFExporter(listprofesseurs);
        exporter.export(response);
        
      

}
}
