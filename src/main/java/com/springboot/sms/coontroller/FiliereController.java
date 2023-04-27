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
import com.springboot.sms.entity.Filiere;
import com.springboot.sms.entity.Student;
import com.springboot.sms.repository.DepartementRepository;
import com.springboot.sms.repository.FiliereRepository;
import com.springboot.sms.repository.StudentRepository;
import com.springboot.sms.service.FiliereService;
import com.springboot.sms.service.StudentService;
import com.springboot.sms.util.FilierePDFExporter;
import com.springboot.sms.util.StudentPDFExporter;
@Controller
public class FiliereController {

	@Autowired
	private FiliereRepository studenttrepo;
	
	@Autowired
	private FiliereService repo;
	
	@Autowired
	private DepartementRepository departementRepo;
	
	@GetMapping("/Filieres/new")
	public String showNewStudentForm(Model model) {
		List<Departement> listDepartements =departementRepo.findAll();
		
		model.addAttribute("Filiere", new Filiere());
		model.addAttribute("listDepartements", listDepartements);
		model.addAttribute("added", true);
		
		return "Filiere_form";
	}
	
	@PostMapping("/Filieres/save")
	public String saveFiliere(Filiere Filiere) {
		studenttrepo.save(Filiere);
		return "redirect:/Filiere";
	}
	
	@GetMapping("/Filiere")
	public String listFiliere(Model model) {
		
		List<Filiere> listFilieres = studenttrepo.findAll();
		model.addAttribute("listFilieres", listFilieres);
		
		return "Filiere";
	}
	
	@GetMapping("/Filiere/search")
	public String SearchStudent(Model model, @Param("keyword") String keyword) {
		List<Filiere> listFilieres = studenttrepo.search(keyword);
		model.addAttribute("listFilieres", listFilieres);
		model.addAttribute("keyword", keyword);
		
		return "Filiere";
	}
	
	@GetMapping("/Filieres/edit/{id}")
	public String showEditStudentForm(@PathVariable("id") Integer id, Model model) {
		Filiere filiere =studenttrepo.findById(id).get();
		model.addAttribute("Filiere", filiere);
		model.addAttribute("edit", true);
		
		List<Departement> listDepartements =departementRepo.findAll();
		
		model.addAttribute("listDepartements", listDepartements);
		return "Filiere_form";
	}
	
	@GetMapping("/Filieres/delete/{id}")
	public String deleteFiliere(@PathVariable("id") Integer id, Model model) {
		
		studenttrepo.deleteById(id);
		return "redirect:/Filiere";
	}
	
	@GetMapping("/Filiere/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Filiere_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Filiere> listFilieres = repo.listAll();
        
        FilierePDFExporter exporter = new FilierePDFExporter(listFilieres);
        exporter.export(response);
}
}
