package cloud.migration.frontend.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cloud.migration.model.Subscriber;
import cloud.migration.service.AdminService;
import cloud.migration.service.CapabilityService;
import cloud.migration.service.CourtService;
import cloud.migration.service.HallService;
import cloud.migration.service.ReservationService;
import cloud.migration.service.SubscriberService;
import cloud.migration.service.TimeIntervalService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private CapabilityService capabilityService;
	public CapabilityService getCapabilityService() {
		return capabilityService;
	}


	public void setCapabilityService(CapabilityService capabilityService) {
		this.capabilityService = capabilityService;
	}


	public TimeIntervalService getTimeIntervalService() {
		return timeIntervalService;
	}


	public void setTimeIntervalService(TimeIntervalService timeIntervalService) {
		this.timeIntervalService = timeIntervalService;
	}


	public AdminService getAdminService() {
		return adminService;
	}


	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}


	public CourtService getCourtService() {
		return courtService;
	}


	public void setCourtService(CourtService courtService) {
		this.courtService = courtService;
	}


	public HallService getHallService() {
		return hallService;
	}


	public void setHallService(HallService hallService) {
		this.hallService = hallService;
	}


	public ReservationService getReservationService() {
		return reservationService;
	}


	public void setReservationService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}


	public SubscriberService getSubscriberService() {
		return subscriberService;
	}


	public void setSubscriberService(SubscriberService subscriberService) {
		this.subscriberService = subscriberService;
	}

	private TimeIntervalService timeIntervalService;
	private AdminService adminService;
	private CourtService courtService;
	private HallService hallService;
	private ReservationService reservationService;
	private SubscriberService subscriberService;
	
	

	@RequestMapping(value={"/reservation","/reservation/"},method=RequestMethod.GET)
	public String getReservationPage()
	{
		
		return "frontend/reservation";
	}
	
	
	@RequestMapping(value={"/success","/success/"},method=RequestMethod.GET)
	public String getSuccessPage()
	{
		
		return "frontend/success";
	}
	

	@RequestMapping(value={"/home","/"," "},method=RequestMethod.GET)
	public String getHomePage()
	{
		
		return "frontend/home";
	}
	

	@RequestMapping(value = {"/about","/about/"}, method = RequestMethod.GET)
	public String getAboutPage(Model model) {
		logger.info("About page !");
		
		return "frontend/about";
	}
	
	@RequestMapping(value = "/login/user-name/{userName}/user-authen-key/{userAuthenKey}", method = RequestMethod.GET)
	public ModelAndView getUserPage(@PathVariable String userName,@PathVariable String userAuthenKey) {
		logger.info("About page !");
		ModelAndView mav = new ModelAndView();
		for(Subscriber sub:(List<Subscriber>)subscriberService.getSubscribers())
			
			if(sub.getAuthenKey().equals(userAuthenKey)&&sub.getName().equals(userName))
			{
				System.out.println("we find you: "+userName);
				mav.addObject("subscriber",sub);
				mav.setViewName("frontend/cancel-reservation");
				return mav;
				
			}
		
		mav.setViewName("frontend/not-found");
		
		return mav;
	}
	
	

	
}
