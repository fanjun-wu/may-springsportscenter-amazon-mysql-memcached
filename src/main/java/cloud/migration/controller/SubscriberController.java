package cloud.migration.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

import cloud.migration.model.Reservation;
import cloud.migration.model.Subscriber;
import cloud.migration.model.TimeInterval;
import cloud.migration.service.CapabilityService;
import cloud.migration.service.ReservationService;
import cloud.migration.service.SubscriberService;
import cloud.migration.service.TimeIntervalService;

@Controller
@RequestMapping("/admin")
public class SubscriberController {

private static final Logger logger = LoggerFactory.getLogger(SubscriberController.class);
	
	private SubscriberService subscriberService;
	
	private CapabilityService capabilityService;
	private ReservationService reservationService;
	private TimeIntervalService timeIntervalService;
	

	private SessionFactory session;
	
	

	public SubscriberService getSubscriberService() {
		return subscriberService;
	}


	public void setSubscriberService(SubscriberService subscriberService) {
		this.subscriberService = subscriberService;
	}


	public CapabilityService getCapabilityService() {
		return capabilityService;
	}


	public void setCapabilityService(CapabilityService capabilityService) {
		this.capabilityService = capabilityService;
	}


	public ReservationService getReservationService() {
		return reservationService;
	}


	public void setReservationService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}


	public TimeIntervalService getTimeIntervalService() {
		return timeIntervalService;
	}


	public void setTimeIntervalService(TimeIntervalService timeIntervalService) {
		this.timeIntervalService = timeIntervalService;
	}


	public SessionFactory getSession() {
		return session;
	}


	public void setSession(SessionFactory session) {
		this.session = session;
	}


	public static Logger getLogger() {
		return logger;
	}


	@RequestMapping(value="/subscriberList", method=RequestMethod.GET)
	public ModelAndView list() {
		
		System.out.println("Listing subscribers.");
		logger.info("Listing subscribers.");
		Collection<Subscriber> subscribers = subscriberService.getSubscribers();
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("subscribers", subscribers);
		return new ModelAndView("subscriber/subscriberList", model);
	}
	

	@RequestMapping(value="/getSubscriber", method=RequestMethod.GET)
	public ModelAndView fetchSubscriber(@RequestParam("subscriberId") int subscriberId) {
		logger.info("Fetching subscriber " + subscriberId);
		Subscriber subscriber = subscriberService.getSubscriber(subscriberId);
		Map<String,Object>modelAndView = new HashMap<String,Object>();
		modelAndView.put("subscriber", subscriber);
		return new ModelAndView("subscriber/modifySubscriber", modelAndView);
	}
	
	
	@RequestMapping(value="/deleteSubscriber", method=RequestMethod.GET)
	public String deleteSubscriber(@RequestParam("subscriberId") int subscriberId) {
		logger.info("Deleting subscriber " + subscriberId);
		
		//first store reservation(many-to-many owner) locally then delete it, then time interval will be deleted automatically ,finally delete subscriber
		for(Reservation res:subscriberService.getSubscriber(subscriberId).getReservation())
		{			
			reservationService.deleteReservation(res.getId());			
					
		}
		
		subscriberService.deleteSubscriber(subscriberId);
		
		
		session.getCurrentSession().flush();
		
		
		System.out.println("delete subscriber with id: "+subscriberId);
		return "redirect:subscriberList";
	}
	
	
	
	@RequestMapping(value="/newSubscriber", method=RequestMethod.GET)
	public ModelAndView newSubscriber() {
		logger.info("Create new subscriber instance");
		Subscriber subscriber = new Subscriber();
		Map<String,Object>model = new HashMap<String,Object>();
		model.put("subscriber", subscriber);
		return new ModelAndView("subscriber/newSubscriber", model);
	}
	
	
	
	@RequestMapping(value="/saveSubscriber", method=RequestMethod.POST)
	public String createSubscriber(@ModelAttribute("subscriber") Subscriber subscriber,
			BindingResult result, Model model) {
		logger.info("Save subscriber instance");
		subscriberService.saveSubscriber(subscriber);
		return "redirect:subscriberList";
	}
	
	@RequestMapping(value="/updateSubscriber", method=RequestMethod.POST)
	public String updateSubscriber(@ModelAttribute("subscriber") Subscriber subscriber,
			BindingResult result, Model model) {
		logger.info("Save subscriber instance");
		subscriberService.editSubscriber(subscriber);
		session.getCurrentSession().flush();
		return "redirect:subscriberList";
	}
	
	
	@RequestMapping(value="/api/subscriber", method=RequestMethod.GET)
	public void testSubscriber() {
		logger.info("test subscriber instance");
		
		//subscriberService.getReservationsBySubscriber(2);
		
		System.out.println("size 0: "+capabilityService.getCapabilities().size());
		System.out.println("size 1: "+subscriberService.getReservationsBySubscriber(1).size());
		
		
	}
	
	
	
}
