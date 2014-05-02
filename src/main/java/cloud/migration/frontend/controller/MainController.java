package cloud.migration.frontend.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import cloud.others.MailMail;

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	private final String URLContex="http://springsportscenter.elasticbeanstalk.com/";
	
	//private final String URLContex="http://localhost:8080/springsportscenter-amazon-mysql-memcached/";
	
	private final String emailFrom="lihuachenchen0725@gmail.com";
	private final String emailSubject="Spring Sports Center Reservation";
	private String emailTo="nobody";
	
	
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




	private Capability _capability=new Capability();
	private List<TimeInterval> _timeIntervals = new ArrayList<TimeInterval>();
	
	private Set<Hall> _hallsAvailable = new HashSet<Hall>();
	private Set<Court> _allCourtsThisCap = new HashSet<Court>();
	private Set<Court> _courtsAvailable= new HashSet<Court>();
	private Set<Reservation> _reservations=new HashSet<Reservation>();
	
	private Set<Integer> _startTimeSetAvailable=new HashSet<Integer>();
	private Date dateSelected=null; 
	private String _startTimeRecieved=null;	
	private Subscriber _subscriber=new Subscriber();
	
	
	private String _nameOfHallRecieved=null;
	private String _nameOfCourtRecieved=null;
	
	private int testingIntegerFlag=0;
	
	//===================================================================================
	//===================================================================================
	//in /getCapabilities  GET ----> none variables calculated
	//in /getCapabilities  POST ----> _capability,_allCourtsThisCap
	//in /getDate POST  ----> dateSelected, _timeIntervals, _reservations
	//in /getTimeIntervals GET--->  _startTimeSetAvailable
	//in /getTimeIntervals POST--->  _startTimeRecieved
	//in /getHalls GET ---> _hallsAvailable
	//in /getHalls POST ---> _nameOfHallRecieved
	//in /getCourts GET ---> _courtsAvailable
	//in /getCourts POST ---> _nameOfCourtRecieved
	//in /getUserInfo  POST---> _subscriber
	//===================================================================================
	//===================================================================================
	

	@RequestMapping(value = "/getCapabilities", method = RequestMethod.GET)
	public @ResponseBody
	List<Capability> getCapabilities() {

		List<Capability> capabilitiesTemp = capabilityService.getCapabilities();
		
		List<Capability> capabilities=new ArrayList<Capability>();
		
		for(Capability cap:capabilitiesTemp)
		{
			
			capabilities.add(new Capability(cap.getResource(),cap.getCondition(),cap.getDiscription()));
			
		}		
		System.out.println("capability types: "+capabilities.toString());
		
		return capabilities;
				
	}

	
	@RequestMapping(value = "/getCapabilities", method = RequestMethod.POST)
	public @ResponseBody
	void getCapabilitySelected(@RequestBody String capability) {

		System.out.println("inside //getCapabilities  POST");
		System.out.println("Capability recieved: "+capability);
		
		for(int i=0;i<capabilityService.getCapabilities().size();i++)
		{
			Capability cap=(Capability)capabilityService.getCapabilities().get(i);
			if(cap.getResource().equalsIgnoreCase(capability))
				_capability=cap;			
		}
		
		//get all the courts can be used for this sport
		_allCourtsThisCap=_capability.getCourt();	
		System.out.println("size of courts for this sport: "+_allCourtsThisCap.size());
		
		cleanVariablesAfterSelectSports();
	}
	
	
	@RequestMapping(value="/getDate", method=RequestMethod.POST)
	public @ResponseBody void getDateSelected(@RequestBody String dateRecieved)
	{		

		System.out.println("inside /getDate  POST");
		System.out.println("Date recieved: "+dateRecieved);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			dateSelected = formatter.parse(dateRecieved);
		
			System.out.println("Date recieved after parse: "+dateSelected);
			_timeIntervals=timeIntervalService.getTimeIntervalByDate(dateSelected);
				
			
			//get all the reservations for this date
			if(_timeIntervals.size()!=0)
				for(TimeInterval tt:_timeIntervals)
				{
					if(tt.getReservation().size()!=0)
						for(Reservation res:tt.getReservation())
						{
							_reservations.add(res);					
						}

				}			
			
			System.out.println("TimeInterval(with same date) Size: "+_timeIntervals.size());
			
			System.out.println("Reservations size: "+_reservations.size()+" at "+dateRecieved);
			
			cleanVariablesAfterSelectDate();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//list whose element[start time] begins from open to close, and return the elements[start time] which are not inside theset of timeSetReserved
	public Set<Integer> timeSetLeft(int open,int close,Set<Integer> timeSetReserved)
	{		
		Set<Integer> timeSetStandard=new HashSet<Integer>();
		Set<Integer> timeSetLeft=new HashSet<Integer>();

		for(int i=open;i<close;i++)
			timeSetStandard.add(i);
		
		System.out.println("timeSetReserved size: "+timeSetReserved.size());
		
		for(Integer t:timeSetReserved)
			System.out.println(": "+t);
		
		if(timeSetReserved.size()!=0)
		{
			
			for(Integer itr:timeSetStandard)
			{
				if(!timeSetReserved.contains(itr))
					timeSetLeft.add(itr);
			}
			
		}
		else
			timeSetLeft=timeSetStandard;
		
		return timeSetLeft;
	}


	@RequestMapping(value = "/getTimeIntervals", method = RequestMethod.GET)
	public @ResponseBody
	List<Integer> getTimeIntervals() {
		

		//get the start time list available for this sport at this day
		List< Set<Integer> > _startTimeSetLeftTotal=new ArrayList<Set<Integer>>();
		
		for(Court ct:_allCourtsThisCap)
		{
			Set<Integer> _startTimeSetReservedSingleCourt=new HashSet<Integer>();
			Set<Integer> _startTimeSetLeftSingleCourt=new HashSet<Integer>();				
			
			//get all the reservation for that court in that day and manipulate the start time Set			
			
			System.out.println("ct getReservation() size(): "+ct.getReservation().size());			
			if(ct.getReservation().size()!=0)
				for(Reservation res: ct.getReservation())
				{

//					System.out.println("getTimeInterval() size: "+res.getTimeInterval().toString());
					for(TimeInterval tvl:res.getTimeInterval())
					{
						if( compareTwoDate(tvl.getDate(),dateSelected))
						{
							_startTimeSetReservedSingleCourt.add(tvl.getStartTime());
						}
							
					}									
				}

			System.out.println("_startTimeSetReservedSingleCourt size: "+_startTimeSetReservedSingleCourt.size());
			
			
			_startTimeSetLeftSingleCourt=timeSetLeft(ct.getHall().getOpenTime(),ct.getHall().getCloseTime(),_startTimeSetReservedSingleCourt);
			
			
			System.out.println("_startTimeSetLeftSingleCourt size: "+_startTimeSetLeftSingleCourt.size());
			
			_startTimeSetLeftTotal.add(_startTimeSetLeftSingleCourt);							
		}
		
		System.out.println("_startTimeSetLeftTotal size: "+_startTimeSetLeftTotal.size());
		
		for(Set<Integer> setElement:_startTimeSetLeftTotal )
			for(Integer element:setElement)
			{
				_startTimeSetAvailable.add(element);
				System.out.println("-->  "+element);
			}

		System.out.println("_startTimeSetAvailable size: "+_startTimeSetAvailable.size());
		
		List<Integer> listTemp=new ArrayList<Integer>();
		for(Integer itg:_startTimeSetAvailable)
			listTemp.add(itg);
		Collections.sort(listTemp);
		
		/*
		for(Integer itg:listTemp)
		{
			System.out.println("<--  "+itg);
			_startTimeSetAvailable.add(itg);
		}
		
		for(Integer itg:_startTimeSetAvailable)
			System.out.println("==:   "+itg);
		
		return _startTimeSetAvailable; 
		*/
		
		return listTemp;
		
		
	}

	
	
	public boolean compareTwoDate(Date d1,Date d2)
	{
		if(d1.getYear()==d2.getYear()&&d1.getMonth()==d2.getMonth()&&d1.getDay()==d2.getDay())
			return true;
		else
			return false;
	}
	
	
	
	@RequestMapping(value = "/getTimeIntervals", method = RequestMethod.POST)
	public @ResponseBody
	void getTimeIntervalSelected(@RequestBody String interval) {
		
		_startTimeRecieved=interval;
		System.out.println("start time recieved: "+interval);
		
		cleanVariablesAfterSelectTimeInterval();
		
	}

	
	@RequestMapping(value = "/getHalls", method = RequestMethod.GET)
	public @ResponseBody
	Set<Hall> getHallAvailables() {
		//calculate the halls available
				for(Court ct:_allCourtsThisCap)
				{
					Set<Integer> startTimeReservedSingleCourt=new HashSet<Integer>();
					for(Reservation res:ct.getReservation())
					{
						for(TimeInterval tv:res.getTimeInterval())
						{							
							if(compareTwoDate(tv.getDate(),dateSelected))							
								startTimeReservedSingleCourt.add(tv.getStartTime());
							
						}
						
					}
					
					
					if(ct.getHall().getOpenTime()<=Integer.parseInt(_startTimeRecieved)&&ct.getHall().getCloseTime()>=(Integer.parseInt(_startTimeRecieved)+1))
					{
						

						//if the start time user choose isn't in the set of start time set reserved, then add this court or hall as available
						if(!startTimeReservedSingleCourt.contains(Integer.parseInt(_startTimeRecieved)))
						{			
							_hallsAvailable.add(ct.getHall());				
						}	
						
						
					}
					
					
				}
				
				System.out.println("available halls size: "+_hallsAvailable.size());
				

				
		Set<Hall> hallTemp=new HashSet<Hall>();		
		for(Hall ha:_hallsAvailable)		
		{
			hallTemp.add(new Hall(ha.getName(),ha.getOpenTime(),ha.getCloseTime(),ha.getIntroduction()));			
		}		

		//don't return _hallsAvailable directly, throw away the relations here, 
		//just the hall info is needed, if not, we will get the json serialize error
		return hallTemp;

	}
	
	
	@RequestMapping(value = "/getHalls", method = RequestMethod.POST)
	public @ResponseBody
	void getHallSelected(@RequestBody String hall) {

		_nameOfHallRecieved=hall;
		System.out.println("hall choosen: "+hall);
		
		cleanVariablesAfterSelectHall();
	}
	


	@RequestMapping(value = "/getCourts", method = RequestMethod.GET)
	public @ResponseBody
	Set<Court> getCourts() {

		
		Hall hallTemp=new Hall();
		for(Hall ha:_hallsAvailable)
		{
			if(ha.getName().equalsIgnoreCase(_nameOfHallRecieved))
			{
				hallTemp=ha;				
			}			
		}
		
		for(Court ct:hallTemp.getCourts())
		{
			Set<Integer> startTimeReservedSingleCourt=new HashSet<Integer>();
			
			for(Capability cap:ct.getCapability())
				if(cap.getResource().equalsIgnoreCase(_capability.getResource()))
				{
					System.out.println("capability: "+_capability.getResource());
					for(Reservation res:ct.getReservation())
					{
						for(TimeInterval tv:res.getTimeInterval())
						{
							if( compareTwoDate(tv.getDate(),dateSelected))
								startTimeReservedSingleCourt.add(tv.getStartTime());
							
						}
						
					}
					
					//if the start time user choose isn't in the set of start time set reserved, then add this court or hall as available
					if(!startTimeReservedSingleCourt.contains(Integer.parseInt(_startTimeRecieved)))
					{			
						System.out.println("this court can be reserved");
						_courtsAvailable.add(ct);				
					}
					
					
				}
			
				
			
		}
		
		
		Set<Court> courtTemp=new HashSet<Court>();
		for(Court ct:_courtsAvailable)
			courtTemp.add(new Court(ct.getName(),ct.getIntroduction()));
			
		return courtTemp;
	}
	
	@RequestMapping(value = "/getCourts", method = RequestMethod.POST)
	public @ResponseBody
	void getCourtSelected(@RequestBody String court) {

		_nameOfCourtRecieved=court;
		
		System.out.println("court selected: "+court);
		
	}
	
	
	@RequestMapping(value="/getUserInfo", method=RequestMethod.POST)
	public @ResponseBody void getUserInfo(@RequestBody String user)
	{				

		String[] userInfoTemp=user.split(","); 
		_subscriber.setName(userInfoTemp[0]);
		_subscriber.setEmail(userInfoTemp[1]);
		_subscriber.setTelephone(userInfoTemp[2]);
		UUID uniqueKey1= UUID.randomUUID();
		UUID uniqueKey2= UUID.randomUUID();
		String key=uniqueKey1.toString()+uniqueKey2.toString();
		_subscriber.setAuthenKey(key);
		System.out.println("user info:"+_subscriber.getName()+" "+_subscriber.getEmail()+" "+_subscriber.getTelephone());
		
		saveReservation(userInfoTemp[0],key,userInfoTemp[1]);
		cleanVaribalesAfterSubmit();
		testingIntegerFlag++;
		
	}
	
	
	public void saveReservation(String name, String key,String email)
	{
		subscriberService.saveSubscriber(_subscriber);
		TimeInterval tal=new TimeInterval(Integer.parseInt(_startTimeRecieved),dateSelected);
		Set<TimeInterval> tals=new HashSet<TimeInterval>();
		tals.add(tal);
		timeIntervalService.saveTimeSet(tals);
		
		Reservation res=new Reservation();
		res.setCourt(courtService.getCourtByName(_nameOfCourtRecieved));
		res.setSubscriber(_subscriber);
		res.setTimeInterval(tals);
		reservationService.saveReservation(res);
		
		session.getCurrentSession().flush();
		
		sendEmail(name,key,email);
		
	}
	
	
	public void sendEmail(String name, String key,String email)
	{
		
		
		ApplicationContext context = 
                new ClassPathXmlApplicationContext("classpath:applicationContextOthers.xml");
    
    	
    	MailMail mm = (MailMail) context.getBean("mailMail");
    
    	emailTo=email;
    	
        mm.sendMail(emailFrom,
    		   emailTo,
    		   emailSubject,
    		   "Hello, "+name+"your reservation is successful, if you want to cancel it, go to your reservation page: "+
    				   URLContex+"login/user-name/"+name+"/user-authen-key/"+key);
    		   
        
        
		System.out.println("sending email...");
		
	}
	
	
	
	

	

	
	public void cleanVaribalesAfterSubmit()
	{
		System.out.println("clean all after you reserve successfully!");
		
		_capability=new Capability();		
		_allCourtsThisCap.clear();
		
		dateSelected=null;
		_timeIntervals.clear();
		_reservations.clear();
		
		_startTimeSetAvailable.clear();
		_startTimeRecieved=null;
		
		_hallsAvailable.clear();
		_nameOfHallRecieved=null;
		
		_courtsAvailable.clear();
		_nameOfCourtRecieved=null;		
		
		_subscriber=new Subscriber();
		
		
	}
	
	public void cleanVariablesAfterSelectSports()
	{
		System.out.println("cleanVariablesAfterSelectSports");
		
		dateSelected=null;
		_timeIntervals.clear();
		_reservations.clear();
		
		_startTimeSetAvailable.clear();
		_startTimeRecieved=null;
		
		_hallsAvailable.clear();
		_nameOfHallRecieved=null;
		
		_courtsAvailable.clear();
		_nameOfCourtRecieved=null;		
		
		_subscriber=new Subscriber();

		
	}
	
	public void cleanVariablesAfterSelectDate()
	{
		System.out.println("cleanVariablesAfterSelectDate");
		_startTimeSetAvailable.clear();
		_startTimeRecieved=null;
		
		_hallsAvailable.clear();
		_nameOfHallRecieved=null;
		
		_courtsAvailable.clear();
		_nameOfCourtRecieved=null;		
		
		_subscriber=new Subscriber();
		
	}
	
	public void cleanVariablesAfterSelectTimeInterval()
	{
		System.out.println("cleanVariablesAfterSelectTimeInterval");
		_hallsAvailable.clear();
		_nameOfHallRecieved=null;
		
		_courtsAvailable.clear();
		_nameOfCourtRecieved=null;		
		
		_subscriber=new Subscriber();
		
	}
	public void cleanVariablesAfterSelectHall()
	{
		System.out.println("cleanVariablesAfterSelectHall");
		_courtsAvailable.clear();
		_nameOfCourtRecieved=null;		
		
		_subscriber=new Subscriber();
		
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
	
	
}
