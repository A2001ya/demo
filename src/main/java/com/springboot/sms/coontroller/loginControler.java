package com.springboot.sms.coontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.springboot.sms.entity.Compte;
import com.springboot.sms.entity.Login;
@Controller
class LoginController {
	@PostMapping("/Login/compte")
	String login(Model model) {
		 Login login =new Login();
	
	 if(login.getPassword()<0) {
			
		 return "index";
		 }
	 else {
	 
	 	model.addAttribute("compte", new Login()); 
	return "index"	;}
	 
	}
}
