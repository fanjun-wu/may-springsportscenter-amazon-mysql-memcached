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

import cloud.migration.model.Reservation;
import cloud.migration.service.HallService;
import cloud.migration.service.ReservationService;

@Controller
@RequestMapping("/admin")
public class ReservationController {

	
private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);
	
	private ReservationService reservationService;
	private SessionFactory session;
	

	public ReservationService getReservationService() {
		return reservationService;
	}




	public void setReservationService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	public SessionFactory getSession() {
		return session;
	}


	public void setSession(SessionFactory session) {
		this.session = session;
	}


	@RequestMapping(value="/reservationList", method=RequestMethod.GET)
	public ModelAndView list() {
		
		System.out.println("Listing reservations.");
		logger.info("Listing reservations.");
		Collection<Reservation> reservations = reservationService.getReservations();
		Map<String,Object>model = new HashMap<String,Object>();
		System.out.println("reservation number: "+reservations.size());
		model.put("reservations", reservations);
		return new ModelAndView("reservation/reservationList", model);
	}
	
	
	
	
	

	@RequestMapping(value="/getReservation", method=RequestMethod.GET)
	public ModelAndView fetchReservation(@RequestParam("reservationId") int reservationId) {
		logger.info("Fetching reservation " + reservationId);
		Reservation reservation = reservationService.getReservation(reservationId);
		Map<String,Object>modelAndView = new HashMap<String,Object>();
		modelAndView.put("reservation", reservation);
		return new ModelAndView("reservation/modifyReservation", modelAndView);
	}
	
	
	@RequestMapping(value="/deleteReservation", method=RequestMethod.GET)
	public String deleteReservation(@RequestParam("reservationId") int reservationId) {
		logger.info("Deleting reservation " + reservationId);
		
		reservationService.deleteReservation(reservationId);
		
		session.getCurrentSession().flush();
		
		return "redirect:reservationList";
	}
	
	
	
	@RequestMapping(value="/newReservation", method=RequestMethod.GET)
	public ModelAndView newReservation() {
		logger.info("Create new reservation instance");
		Reservation reservation = new Reservation();
		Map<String,Object>model = new HashMap<String,Object>();
		model.put("reservation", reservation);
		return new ModelAndView("reservation/newReservation", model);
	}
	
	
	
	@RequestMapping(value="/saveReservation", method=RequestMethod.POST)
	public String createReservation(@ModelAttribute("reservation") Reservation reservation,
			BindingResult result, Model model) {
		logger.info("Save reservation instance");
		reservationService.saveReservation(reservation);
		return "redirect:reservationList";
	}
	
	@RequestMapping(value="/updateReservation", method=RequestMethod.POST)
	public String updateReservation(@ModelAttribute("reservation") Reservation reservation,
			BindingResult result, Model model) {
		logger.info("Save reservation instance");
		reservationService.editReservation(reservation);
		session.getCurrentSession().flush();
		return "redirect:reservationList";
	}
	

}
