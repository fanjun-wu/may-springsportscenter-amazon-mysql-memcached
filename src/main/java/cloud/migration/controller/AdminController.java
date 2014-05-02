package cloud.migration.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

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

import cloud.migration.dao.AdminDao;
import cloud.migration.model.Admin;
import cloud.migration.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	private AdminService adminService;
	private SessionFactory session;


	public AdminService getAdminService() {
		return adminService;
	}



	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public SessionFactory getSession() {
		return session;
	}


	public void setSession(SessionFactory session) {
		this.session = session;
	}

	@RequestMapping(value="/adminList", method=RequestMethod.GET)
	public ModelAndView list() {
		logger.info("Listing admins.");
		Collection<Admin> admins = adminService.getAdmins();
		
		System.out.println("admins size: "+admins.size());
		
		for(Admin ad:admins)
			System.out.println(": "+ad.toString());
		
		Map<String,Object>model = new HashMap<String,Object>();
		model.put("admins", admins);
		return new ModelAndView("admin/adminList", model);
	}
	
	

	@RequestMapping(value="/getAdmin", method=RequestMethod.GET)
	public ModelAndView fetchAdmin(@RequestParam("adminId") int adminId) {
		logger.info("Fetching admin " + adminId);
		Admin admin = adminService.getAdmin(adminId);
		Map<String,Object>modelAndView = new HashMap<String,Object>();
		modelAndView.put("admin", admin);
		return new ModelAndView("admin/modifyAdmin", modelAndView);
	}
	
	
	@RequestMapping(value="/deleteAdmin", method=RequestMethod.GET)
	public String deleteAdmin(@RequestParam("adminId") int adminId) {
		logger.info("Deleting admin " + adminId);
		adminService.deleteAdmin(adminId);
		session.getCurrentSession().flush();
		return "redirect:adminList";
	}
	
	
	
	@RequestMapping(value="/newAdmin", method=RequestMethod.GET)
	public ModelAndView newAdmin() {
		logger.info("Create new admin instance");
		Admin admin = new Admin();
		Map<String,Object>model = new HashMap<String,Object>();
		model.put("admin", admin);
		return new ModelAndView("admin/newAdmin", model);
	}
	
	
	
	@RequestMapping(value="/saveAdmin", method=RequestMethod.POST)
	public String createAdmin(@ModelAttribute("admin") Admin admin,
			BindingResult result, Model model) {
		logger.info("Save admin instance");
		adminService.saveAdmin(admin);
		return "redirect:adminList";
	}
	
	@RequestMapping(value="/updateAdmin", method=RequestMethod.POST)
	public String updateAdmin(@ModelAttribute("admin") Admin admin,
			BindingResult result, Model model) {
		logger.info("Save admin instance");
		
		adminService.editAdmin(admin);
		session.getCurrentSession().flush();
		return "redirect:adminList";
	}
	

	
	
}
