package cloud.migration.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cloud.migration.model.Admin;
import cloud.migration.model.Capability;
import cloud.migration.service.AdminService;
import cloud.migration.service.CapabilityService;

@Controller
@RequestMapping("/admin")
public class CapabilityController {

	
	private static final Logger logger = LoggerFactory.getLogger(CapabilityController.class);

	private CapabilityService capabilityService;


	private SessionFactory session;
	
	public SessionFactory getSession() {
		return session;
	}


	public void setSession(SessionFactory session) {
		this.session = session;
	}

	
	public CapabilityService getCapabilityService() {
		return capabilityService;
	}

	public void setCapabilityService(CapabilityService capabilityService) {
		this.capabilityService = capabilityService;
	}




	@RequestMapping(value="/capabilityList", method=RequestMethod.GET)
	public ModelAndView list() {
		
		
		System.out.println("Listing capabilities.");
		logger.info("Listing capabilities.");
		Collection<Capability> capabilities = capabilityService.getCapabilities();
		
		System.out.println("capabilities size: "+capabilities.size());
		
		Map<String,Object>model = new HashMap<String,Object>();
		model.put("capabilities", capabilities);
		return new ModelAndView("capability/capabilityList", model);
	}
	
	
	
	
	@RequestMapping(value="/getCapability", method=RequestMethod.GET)
	public ModelAndView fetchCapability(@RequestParam("capabilityId") int capabilityId) {
		logger.info("Fetching capability " + capabilityId);
		Capability capability = capabilityService.getCapability(capabilityId);
		Map<String,Object>modelAndView = new HashMap<String,Object>();
		modelAndView.put("capability", capability);
		return new ModelAndView("capability/modifyCapability", modelAndView);
	}
	
	
	
	@RequestMapping(value="/deleteCapability", method=RequestMethod.GET)
	public String deleteCapability(@RequestParam("capabilityId") int capabilityId) {
		logger.info("Deleting capability " + capabilityId);
		capabilityService.deleteCapability(capabilityId);
		return "redirect:capabilityList";
	}
	
	
	
	@RequestMapping(value="/newCapability", method=RequestMethod.GET)
	public ModelAndView newCapability() {
		logger.info("Create new capability instance");
		Capability capability = new Capability();
		Map<String,Object>model = new HashMap<String,Object>();
		model.put("capability", capability);
		return new ModelAndView("capability/newCapability", model);
	}
	
	
	@RequestMapping(value="/saveCapability", method=RequestMethod.POST)
	public String createCapability(@ModelAttribute("capability") Capability capability,
			BindingResult result, Model model) {
		logger.info("Save capability instance");
		capabilityService.saveCapability(capability);
		return "redirect:capabilityList";
	}

	@RequestMapping(value="/updateCapability", method=RequestMethod.POST)
	public String updateCapability(@ModelAttribute("capability") Capability capability,
			BindingResult result, Model model) {
		logger.info("Save Capability instance");
		capabilityService.editCapability(capability);
		
		session.getCurrentSession().flush();
		return "redirect:capabilityList";
	}
	

}
