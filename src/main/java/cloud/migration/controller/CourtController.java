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

import cloud.migration.model.Court;
import cloud.migration.model.Hall;
import cloud.migration.service.CapabilityService;
import cloud.migration.service.CourtService;

@Controller
@RequestMapping("/admin")
public class CourtController {

private static final Logger logger = LoggerFactory.getLogger(CourtController.class);
	
	private CourtService courtService;
	private SessionFactory session;
	
	public SessionFactory getSession() {
		return session;
	}


	public void setSession(SessionFactory session) {
		this.session = session;
	}


	public CourtService getCourtService() {
		return courtService;
	}

	public void setCourtService(CourtService courtService) {
		this.courtService = courtService;
	}






	@RequestMapping(value="/courtList", method=RequestMethod.GET)
	public ModelAndView list() {
		
		System.out.println("Listing courts.");
		logger.info("Listing courts.");
		Collection<Court> courts = courtService.getCourts();
		Map<String,Object>model = new HashMap<String,Object>();
		model.put("courts", courts);
		return new ModelAndView("court/courtList", model);
	}
	
	
	
	
	

	@RequestMapping(value="/getCourt", method=RequestMethod.GET)
	public ModelAndView fetchCourt(@RequestParam("courtId") int courtId) {
		logger.info("Fetching court " + courtId);
		Court court = courtService.getCourt(courtId);
		Map<String,Object>modelAndView = new HashMap<String,Object>();
		modelAndView.put("court", court);
		return new ModelAndView("court/modifyCourt", modelAndView);
	}
	
	
	@RequestMapping(value="/deleteCourt", method=RequestMethod.GET)
	public String deleteCourt(@RequestParam("courtId") int courtId) {
		logger.info("Deleting court " + courtId);
		courtService.deleteCourt(courtId);
		session.getCurrentSession().flush();
		return "redirect:courtList";
	}
	
	
	
	@RequestMapping(value="/newCourt", method=RequestMethod.GET)
	public ModelAndView newCourt() {
		logger.info("Create new court instance");
		Court court = new Court();
		Map<String,Object>model = new HashMap<String,Object>();
		model.put("court", court);
		return new ModelAndView("court/newCourt", model);
	}
	
	
	
	@RequestMapping(value="/saveCourt", method=RequestMethod.POST)
	public String createCourt(@ModelAttribute("court") Court court,
			BindingResult result, Model model) {
		logger.info("Save court instance");
		courtService.saveCourt(court);
		return "redirect:courtList";
	}
	
	@RequestMapping(value="/updateCourt", method=RequestMethod.POST)
	public String updateCourt(@ModelAttribute("court") Court court,
			BindingResult result, Model model) {
		logger.info("Save court instance");
		
		courtService.editCourt(court);
		session.getCurrentSession().flush();
		return "redirect:courtList";
	}
	

}
