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
import com.springboot.sms.entity.Element;
import com.springboot.sms.entity.Filiere;
import com.springboot.sms.entity.Module;
import com.springboot.sms.entity.professeur;
import com.springboot.sms.repository.DepartementRepository;
import com.springboot.sms.repository.ElementRepository;
import com.springboot.sms.repository.FiliereRepository;
import com.springboot.sms.repository.ModuleRepository;
import com.springboot.sms.repository.professeurRepository;
import com.springboot.sms.service.ElementService;
import com.springboot.sms.service.professeurService;
import com.springboot.sms.util.ElementPDFExporter;
import com.springboot.sms.util.professeurPDFExporter;
@Controller
public class ElementController {


	@Autowired
	private ElementRepository studenttrepo;
	
	@Autowired
	private ElementService repo;
	
	@Autowired
	private FiliereRepository departementRepo;
	@Autowired
	private ModuleRepository moduleRepo;
	
	@GetMapping("/Elements/new")
	public String showNewStudentForm(Model model) {
		List<Filiere> listFilieres =departementRepo.findAll();
		List<Module> listModules =moduleRepo.findAll();
		model.addAttribute("Element", new Element());
		model.addAttribute("listModules", listModules);
		model.addAttribute("listFilieres", listFilieres);
		model.addAttribute("added", true);
		
		return "Element_form";
	}
	
	@PostMapping("/Elements/save")
	public String saveElement(Element p) {
		studenttrepo.save(p);
		return "redirect:/Element";
	}
	
	@GetMapping("/Element")
	public String listElement(Model model) {
		
		List<Element> listElements = studenttrepo.findAll();
		model.addAttribute("listElements", listElements);
		
		return "Element";
	}
	
	@GetMapping("/Element/search")
	public String SearchElement(Model model, @Param("keyword") String keyword) {
		List<Element> listElements = studenttrepo.search(keyword);
		model.addAttribute("listElements", listElements);
		model.addAttribute("keyword", keyword);
		
		return "Element";
	}
	
	@GetMapping("/Elements/edit/{id}")
	public String showEditStudentForm(@PathVariable("id") Integer id, Model model) {
		Element Element =studenttrepo.findById(id).get();
		model.addAttribute("Element", Element);
		model.addAttribute("edit", true);
		List<Module> listmodules =moduleRepo.findAll();
		
		model.addAttribute("listmodules", listmodules);
		List<Filiere> listFilieres =departementRepo.findAll();
		
		model.addAttribute("listFilieres", listFilieres);
		return "Element_form";
	}
	
	@GetMapping("/Elements/delete/{id}")
	public String deleteprofesseur(@PathVariable("id") Integer id, Model model) {
		
		studenttrepo.deleteById(id);
		return "redirect:/Element";
	}
	
	@GetMapping("/Element/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Elements_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Element> listElements = repo.listAll();
        
        ElementPDFExporter exporter = new ElementPDFExporter(listElements);
        exporter.export(response);
        
      

}
}
