package cloud.migration.frontend.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cloud.migration.controller.CapabilityController;
import cloud.migration.model.Admin;
import cloud.migration.model.Capability;
import cloud.migration.model.Court;
import cloud.migration.model.Hall;
import cloud.migration.model.Reservation;
import cloud.migration.model.Subscriber;
import cloud.migration.model.TimeInterval;
import cloud.migration.service.AdminService;
import cloud.migration.service.CapabilityService;
import cloud.migration.service.CourtService;
import cloud.migration.service.HallService;
import cloud.migration.service.ReservationService;
import cloud.migration.service.SubscriberService;
import cloud.migration.service.TimeIntervalService;

@Controller
@RequestMapping("/test")
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);


	private SessionFactory session;

	private CapabilityService capabilityService;
	private TimeIntervalService timeIntervalService;
	private AdminService adminService;
	private CourtService courtService;
	private HallService hallService;
	private ReservationService reservationService;
	private SubscriberService subscriberService;
	
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



	@RequestMapping(value="/smallDataFacility",method=RequestMethod.GET)
	public void frontendtest1()
	{
		
		Capability BK=new Capability("Basketball","good","no");
		Capability BD=new Capability("Badminton","good","no");
		Capability PP=new Capability("Pingpong","good","no");
		Capability TN=new Capability("Tennis","good","no");
		
		Set<Capability> BBCapSet=new HashSet<Capability>();
		Set<Capability> BKCapSet=new HashSet<Capability>();
		Set<Capability> BDCapSet=new HashSet<Capability>();
		Set<Capability> PPCapSet=new HashSet<Capability>();
		Set<Capability> TNCapSet=new HashSet<Capability>();
		
		BBCapSet.add(BD);
		BBCapSet.add(BK);
		
		BKCapSet.add(BK);
		BDCapSet.add(BD);
		
		PPCapSet.add(PP);
		TNCapSet.add(TN);
		
		
		Court B1_1=new Court("B1-1","no");
		B1_1.setCapability(BDCapSet);		
		Court B2_1=new Court("B2-1","no");
		B2_1.setCapability(BKCapSet);
		Court B1_2=new Court("B1-2","no");
		B1_2.setCapability(PPCapSet);
		Court B2_2=new Court("B2-2","no");
		B2_2.setCapability(TNCapSet);
		
		
		Set<Court> courtSet_1=new HashSet<Court>();
		courtSet_1.add(B2_1);
		courtSet_1.add(B1_1);
		
		Set<Court> courtSet_2=new HashSet<Court>();
		courtSet_2.add(B2_2);
		courtSet_2.add(B1_2);

		Admin admin_1=new Admin("conna","conna@gmail.com","0483359884","M",23,"admin");
		Admin admin_2=new Admin("back","back@gmail.com","0462359884","M",23,"admin");
		
		adminService.saveAdmin(admin_1);
		adminService.saveAdmin(admin_2);
		
		Hall hall_1=new Hall("HALL1",8,14,"no");
		Hall hall_2=new Hall("HALL2",12,18,"no");
		hall_1.setAdmin(admin_1);
		hall_2.setAdmin(admin_2);
		
		hallService.saveHall(hall_1);
		hallService.saveHall(hall_2);
		
		
		for(Court c:courtSet_1)
		{
			c.setHall(hall_1);
			courtService.saveCourt(c);
		}
		
		for(Court c:courtSet_2)
		{
			c.setHall(hall_2);
			courtService.saveCourt(c);
		}
		
		session.getCurrentSession().flush();
		
		
			
	}

	

	@RequestMapping(value="/smallDataReservation",method=RequestMethod.GET)
	public void frontendreservationtest1()
	{
		
		Subscriber s1=new Subscriber("lihua1","lihua1@gmail.com","0254846854","M",23,"dsqdq484sdqsdsq");
		Subscriber s2=new Subscriber("lihua2","lihua2@gmail.com","0254846854","M",23,"dsqdq484sdqsdsq");
		Subscriber s3=new Subscriber("lihua3","lihua3@gmail.com","0254846854","M",23,"dsqdq484sdqsdsq");
		Subscriber s4=new Subscriber("lihua4","lihua4@gmail.com","0254846854","M",23,"dsqdq484sdqsdsq");
		Subscriber s5=new Subscriber("lihua5","lihua5@gmail.com","0254846854","M",23,"dsqdq484sdqsdsq");
		
		subscriberService.saveSubscriber(s1);  
		subscriberService.saveSubscriber(s2);  
		subscriberService.saveSubscriber(s3);  
		subscriberService.saveSubscriber(s4);  
		subscriberService.saveSubscriber(s5);  
		
		
		TimeInterval t1=new TimeInterval(9,getDateByString("2014-3-20"));
		TimeInterval t2=new TimeInterval(9,getDateByString("2014-3-20"));
		TimeInterval t3=new TimeInterval(10,getDateByString("2014-3-20"));
		TimeInterval t4=new TimeInterval(11,getDateByString("2014-3-20"));
		TimeInterval t5=new TimeInterval(12,getDateByString("2014-3-20"));
						
		Set<TimeInterval> timeIntervals1=new HashSet<TimeInterval>();
		timeIntervals1.add(t1);
		Set<TimeInterval> timeIntervals2=new HashSet<TimeInterval>();
		timeIntervals2.add(t2);
		Set<TimeInterval> timeIntervals3=new HashSet<TimeInterval>();
		timeIntervals3.add(t3);
		Set<TimeInterval> timeIntervals4=new HashSet<TimeInterval>();
		timeIntervals4.add(t4);
		Set<TimeInterval> timeIntervals5=new HashSet<TimeInterval>();
		timeIntervals5.add(t5);
		
		Reservation r1=new Reservation("book basket","no");
		Reservation r2=new Reservation("book basket","no");
		Reservation r3=new Reservation("book badminton","no");
		Reservation r4=new Reservation("book badminton","no");
		Reservation r5=new Reservation("book basket","no");
		
		
		Court courtTemp=new Court();
		for(int i=0;i<courtService.getCourts().size();i++)
		{			
			Court ct=(Court)courtService.getCourts().get(i);
			for(Capability cap:ct.getCapability())
			{
				if(cap.getResource().equalsIgnoreCase("basketball"))
				{
					r1.setCourt(ct);
					r2.setCourt(ct);
					r5.setCourt(ct);
				}
				if(cap.getResource().equalsIgnoreCase("badminton"))
				{
					r3.setCourt(ct);
					r4.setCourt(ct);
				}					
				
			}
		}
		
		r1.setSubscriber(s1);		
		r1.setTimeInterval(timeIntervals1);
		
		r2.setSubscriber(s2);		
		r2.setTimeInterval(timeIntervals2);
				
		r3.setSubscriber(s3);		
		r3.setTimeInterval(timeIntervals3);
			
		r4.setSubscriber(s4);		
		r4.setTimeInterval(timeIntervals4);
			
		r5.setSubscriber(s5);		
		r5.setTimeInterval(timeIntervals5);
		
		
		//save Reservation, TimeInterval, Court, Subscriber in relation
		reservationService.saveReservation(r1); 
		reservationService.saveReservation(r2); 
		reservationService.saveReservation(r3); 
		reservationService.saveReservation(r4); 
		reservationService.saveReservation(r5); 
		
		session.getCurrentSession().flush();
		
		
	}
	

	
	
	
	
	
	@RequestMapping(value="/mediumDataFacility",method=RequestMethod.GET)
	public void frontendtest0()
	{
		
		
		Capability BK=new Capability("Basketball","good","no");
		Capability BD=new Capability("Badminton","good","no");
		
		Capability TN=new Capability("Tennis","good","no");
		Capability PP=new Capability("Pingpong","good","no");
		
		Set<Capability> BBCapSet=new HashSet<Capability>();
		Set<Capability> BKCapSet=new HashSet<Capability>();
		Set<Capability> BDCapSet=new HashSet<Capability>();
		Set<Capability> TNCapSet=new HashSet<Capability>();
		Set<Capability> PPCapSet=new HashSet<Capability>();
		
		BBCapSet.add(BD);
		BBCapSet.add(BK);
		
		BKCapSet.add(BK);
		BDCapSet.add(BD);
		TNCapSet.add(TN);
		PPCapSet.add(PP);
		
		
		Court B1=new Court("B1","no");
		B1.setCapability(BDCapSet);		
		Court B2=new Court("B2","no");
		B2.setCapability(BDCapSet);
		Court B3=new Court("B3","no");
		B3.setCapability(BDCapSet);
		Court B4=new Court("B4","no");
		B4.setCapability(BKCapSet);
		Court B5=new Court("B5","no");
		B5.setCapability(BKCapSet);
		Court B6=new Court("B6","no");
		B6.setCapability(BKCapSet);
		
		
		
		Court TN1=new Court("TN1","no");
		TN1.setCapability(TNCapSet);
		Court TN2=new Court("TN2","no");
		TN2.setCapability(TNCapSet);
		Court TN3=new Court("TN3","no");
		TN3.setCapability(TNCapSet);
		Court TN4=new Court("TN4","no");
		TN4.setCapability(TNCapSet);
		
		Court PP1=new Court("PP1","no");
		PP1.setCapability(PPCapSet);
		Court PP2=new Court("PP2","no");
		PP2.setCapability(PPCapSet);
		Court PP3=new Court("PP3","no");
		PP3.setCapability(PPCapSet);
		Court PP4=new Court("PP4","no");
		PP4.setCapability(PPCapSet);
		Court PP5=new Court("PP5","no");
		PP5.setCapability(PPCapSet);
		Court PP6=new Court("PP6","no");
		PP6.setCapability(PPCapSet);
		
		
		Set<Court> courtSet=new HashSet<Court>();
		courtSet.add(PP6);
		courtSet.add(PP5);
		courtSet.add(PP4);
		courtSet.add(PP3);
		courtSet.add(PP2);
		courtSet.add(PP1);
		courtSet.add(TN4);
		courtSet.add(TN3);
		courtSet.add(TN2);
		courtSet.add(TN1);
		courtSet.add(B6);
		courtSet.add(B5);
		courtSet.add(B4);
		courtSet.add(B3);
		courtSet.add(B2);
		courtSet.add(B1);

		Admin admin=new Admin("conna","conna@gmail.com","0483359884","M",23,"admin");
		
		adminService.saveAdmin(admin);
		
		Hall hall=new Hall("HALL1",8,22,"no");
		hall.setAdmin(admin);
		
		hallService.saveHall(hall);
		
		
		for(Court c:courtSet)
		{
			c.setHall(hall);
			courtService.saveCourt(c);
		}
			
		

	}
	
	
	
	public Date getDateByString(String dateString)
	{
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		
		
		Date date=new Date();
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;		
	}
	
	
	
	
	@RequestMapping(value="/mediumDataReservation",method=RequestMethod.GET)
	public void frontendreservationtest0()
	{
		
		Subscriber s1=new Subscriber("lihua1","lihua1@gmail.com","0254846854","M",23,"dsqdq484sdqsdsq");
		Subscriber s2=new Subscriber("lihua2","lihua2@gmail.com","0254846854","M",23,"dsqdq484sdqsdsq");
		Subscriber s3=new Subscriber("lihua3","lihua3@gmail.com","0254846854","M",23,"dsqdq484sdqsdsq");
		Subscriber s4=new Subscriber("lihua4","lihua4@gmail.com","0254846854","M",23,"dsqdq484sdqsdsq");
		Subscriber s5=new Subscriber("lihua5","lihua5@gmail.com","0254846854","M",23,"dsqdq484sdqsdsq");
		
		subscriberService.saveSubscriber(s1);  
		subscriberService.saveSubscriber(s2);  
		subscriberService.saveSubscriber(s3);  
		subscriberService.saveSubscriber(s4);  
		subscriberService.saveSubscriber(s5);  
		
		
		TimeInterval t1=new TimeInterval(9,getDateByString("2014-3-20"));
		TimeInterval t2=new TimeInterval(9,getDateByString("2014-3-20"));
		TimeInterval t3=new TimeInterval(10,getDateByString("2014-3-20"));
		TimeInterval t4=new TimeInterval(11,getDateByString("2014-3-20"));
		TimeInterval t5=new TimeInterval(12,getDateByString("2014-3-20"));
						
		Set<TimeInterval> timeIntervals1=new HashSet<TimeInterval>();
		timeIntervals1.add(t1);
		Set<TimeInterval> timeIntervals2=new HashSet<TimeInterval>();
		timeIntervals2.add(t2);
		Set<TimeInterval> timeIntervals3=new HashSet<TimeInterval>();
		timeIntervals3.add(t3);
		Set<TimeInterval> timeIntervals4=new HashSet<TimeInterval>();
		timeIntervals4.add(t4);
		Set<TimeInterval> timeIntervals5=new HashSet<TimeInterval>();
		timeIntervals5.add(t5);
		
		Reservation r1=new Reservation("book basket","no");
		Reservation r2=new Reservation("book basket","no");
		Reservation r3=new Reservation("book badminton","no");
		Reservation r4=new Reservation("book badminton","no");
		Reservation r5=new Reservation("book tennis","no");
		
		
		Court courtTemp=new Court();
		for(int i=0;i<courtService.getCourts().size();i++)
		{			
			Court ct=(Court)courtService.getCourts().get(i);
			for(Capability cap:ct.getCapability())
			{
				if(cap.getResource().equalsIgnoreCase("basketball"))
				{
					r1.setCourt(ct);
					r2.setCourt(ct);
				}
				if(cap.getResource().equalsIgnoreCase("badminton"))
				{
					r3.setCourt(ct);
					r4.setCourt(ct);
				}					
				if(cap.getResource().equalsIgnoreCase("tennis"))
				{
					r5.setCourt(ct);
					
				}
			}
		}
		
		r1.setSubscriber(s1);		
		r1.setTimeInterval(timeIntervals1);
		
		r2.setSubscriber(s2);		
		r2.setTimeInterval(timeIntervals2);
				
		r3.setSubscriber(s3);		
		r3.setTimeInterval(timeIntervals3);
			
		r4.setSubscriber(s4);		
		r4.setTimeInterval(timeIntervals4);
			
		r5.setSubscriber(s5);		
		r5.setTimeInterval(timeIntervals5);
		
		
		//save Reservation, TimeInterval, Court, Subscriber in relation
		reservationService.saveReservation(r1); 
		reservationService.saveReservation(r2); 
		reservationService.saveReservation(r3); 
		reservationService.saveReservation(r4); 
		reservationService.saveReservation(r5); 
	}
	
	
	

	@RequestMapping(value="/simpleTest",method=RequestMethod.GET)
	public void frontendtesttest()
	{
		
		
		Court c1=new Court("H1","no");
		Court c2=new Court("H2","no");
		
		Capability cap1=new Capability("Basketball","good","no");
		Capability cap2=new Capability("Badminton","good","no");
		
		Capability cap3=new Capability("tennis","good","no");
		
		
		Set<Capability> capabilities=new HashSet<Capability>();
		capabilities.add(cap1);
		capabilities.add(cap2);
		
		c1.setCapability(capabilities);
		
		courtService.saveCourt(c1);		//  court<---> capability save first
		
		
		Subscriber s1=new Subscriber("hj","kl","dg","uiyi",23,"dsqdq");
		subscriberService.saveSubscriber(s1);    //save subscriber first
		
		TimeInterval t1=new TimeInterval(9,new Date());
		TimeInterval t2=new TimeInterval(19,new Date());
		TimeInterval t3=new TimeInterval(14,new Date());
				
		Set<TimeInterval> timeIntervals=new HashSet<TimeInterval>();
		timeIntervals.add(t1);
		timeIntervals.add(t2);
		timeIntervals.add(t3);
		
		Reservation r1=new Reservation("no","no");
		
		r1.setCourt(c1);
		r1.setSubscriber(s1);
		r1.setTimeInterval(timeIntervals);
		
		reservationService.saveReservation(r1);  //save Reservation, TimeInterval, Court, Subscriber in relation
				
	}
	
	
}
