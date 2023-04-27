package com.springboot.sms.coontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.sms.data.GraphData;
import com.springboot.sms.entity.Compte;
import com.springboot.sms.entity.Login;
import com.springboot.sms.entity.Student;
import com.springboot.sms.repository.CompteRepository;
import com.springboot.sms.repository.DepartementRepository;
import com.springboot.sms.repository.FiliereRepository;
import com.springboot.sms.repository.StudentRepository;
import com.springboot.sms.repository.professeurRepository;

@Controller
public class AppController {
	
	@Autowired
	StudentRepository studentRepository;
	@Autowired 
	CompteRepository compterepository;
	@Autowired
	DepartementRepository departementRepository;

	@Autowired
	professeurRepository professeurRepository;
	@Autowired
	FiliereRepository filiereRepository;
	@Autowired
	private StudentRepository repo;
	List<GraphData> report = new ArrayList<>();
	Map<String, GraphData> map = new HashMap<>();
	
	
	
	@GetMapping("")
	public String viewHomePage(Model model) {
		
		String var=studentRepository.StudentsNBR();
		model.addAttribute("var", var);
		String dp = departementRepository.DepartementNBR();
		model.addAttribute("dp", dp);
		String pr = professeurRepository.professeursNBR();
		model.addAttribute("pr", pr);
		String fl = filiereRepository.FilieressNBR();
		model.addAttribute("fl", fl);
		model.addAttribute("compte", new Login());
		return "login1";
	}
	@GetMapping("/index")
	public String viewHomePage1(Model model) {
		
		String var=studentRepository.StudentsNBR();
		model.addAttribute("var", var);
		String dp = departementRepository.DepartementNBR();
		model.addAttribute("dp", dp);
		String pr = professeurRepository.professeursNBR();
		model.addAttribute("pr", pr);
		String fl = filiereRepository.FilieressNBR();
		model.addAttribute("fl", fl);
		model.addAttribute("compte", new Login());
		return "index";
	}

	
	@GetMapping("/reportByDepartement")
	@ResponseBody
	public List<?> getReport() {
		List<?> report = null;
		report = studentRepository.reportByDepartement();
		return report;
	}
	
//	@GetMapping("/numberStudent")
//	@ResponseBody
//	public String getStudentNumber(Model model){
//		String repot = null;
//		repot = studentRepository.StudentsNBR();
//		model.addAttribute("repot", repot);
//		return repot;
//	}
}
