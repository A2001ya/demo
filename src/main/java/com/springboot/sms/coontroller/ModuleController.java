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

import com.springboot.sms.entity.Filiere;

import com.springboot.sms.entity.Module;

import com.springboot.sms.repository.FiliereRepository;
import com.springboot.sms.repository.ModuleRepository;

import com.springboot.sms.service.ModuleService;

import com.springboot.sms.util.ModulePDFExporter;


@Controller
public class ModuleController {


	@Autowired
	private ModuleRepository studenttrepo;
	
	@Autowired
	private ModuleService repo;
	
	@Autowired
	private FiliereRepository departementRepo;
	
	@GetMapping("/Modules/new")
	public String showNewStudentForm(Model model) {
		List<Filiere> listFilieres =departementRepo.findAll();
		
		model.addAttribute("Module", new Module());
		model.addAttribute("listFilieres", listFilieres);
		model.addAttribute("added", true);
		
		return "Module_form";
	}
	
	@PostMapping("/Modules/save")
	public String saveModule(Module Module) {
		studenttrepo.save(Module);
		return "redirect:/Module";
	}
	
	@GetMapping("/Module")
	public String listModule(Model model) {
		
		List<Module> listModules = studenttrepo.findAll();
		model.addAttribute("listModules", listModules);
		
		return "Module";
	}
	
	@GetMapping("/Module/search")
	public String SearchStudent(Model model, @Param("keyword") String keyword) {
		List<Module> listModules = studenttrepo.search(keyword);
		model.addAttribute("listModules", listModules);
		model.addAttribute("keyword", keyword);
		
		return "Module";
	}
	
	@GetMapping("/Modules/edit/{id}")
	public String showEditStudentForm(@PathVariable("id") Integer id, Model model) {
		Module Module =studenttrepo.findById(id).get();
		model.addAttribute("Module", Module);
		model.addAttribute("edit", true);
		
		List<Filiere> listFilieres =departementRepo.findAll();
		
		model.addAttribute("listFilieres", listFilieres);
		return "Module_form";
	}
	
	@GetMapping("/Modules/delete/{id}")
	public String deleteModule(@PathVariable("id") Integer id, Model model) {
		
		studenttrepo.deleteById(id);
		return "redirect:/Module";
	}
	
	@GetMapping("/Module/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Modules_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Module> listModules = repo.listAll();
        
        ModulePDFExporter exporter = new ModulePDFExporter(listModules);
        exporter.export(response);
        
      

}
}
