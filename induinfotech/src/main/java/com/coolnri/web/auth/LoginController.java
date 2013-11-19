package com.coolnri.web.auth;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value="/welcome.do", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal ) {
		String name = "guest";
		
		if (principal != null) {
			name = principal.getName();
		}
		
		model.addAttribute("username", name);
		model.addAttribute("message", "Spring Security Custom Form example");
		return "index";
 	}

	@RequestMapping(value="/login.do", method = RequestMethod.GET)
	public String login(ModelMap model) {
 		return "login";
 	}
 
	@RequestMapping(value="/loginfailed.do", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 		model.addAttribute("error", "true");
		return "login";
 	}
 
	@RequestMapping(value="/logout.do", method = RequestMethod.GET)
	public String logout(ModelMap model) {
 		return "login";
	}

}
