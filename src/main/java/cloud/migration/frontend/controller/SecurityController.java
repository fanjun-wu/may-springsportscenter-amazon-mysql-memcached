package cloud.migration.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


public class SecurityController {
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String welcomeAdmin(ModelMap model) {
 
		model.addAttribute("message", "Spring Security - ROLE_ADMIN");
		return "frontend/hello";
 
	}
}
