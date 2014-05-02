<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<link href="<c:url value="/resources/css/reservation.css" />" rel="stylesheet">
	
	
<script>
	$(document).ready(function() {
		
		//****************************************************************************************
		//
		//Var Decalre and Initialize 
		//
		//****************************************************************************************

		
		
   	  var capabilities=[];	
   	  var intervals=[];
   	  var halls=[]; 
   	  var courts=[];
   	  
   	  var capabilitySelected;
   	  var dateSelected;
   	  var timeIntervalSelected;
   	  var hallSelected;
   	  var courtSelected;
   	  var name;
   	  var email;
   	  var tele;
   	  var userInfo;
   	  
   	  
  	  var capabilityURL="${pageContext.request.contextPath}/getCapabilities";  	  
  	  var intervalURL="${pageContext.request.contextPath}/getTimeIntervals";  	    	  
  	  var hallURL="${pageContext.request.contextPath}/getHalls";
  	  var courtURL="${pageContext.request.contextPath}/getCourts";
  	  var dateSelectedURL="${pageContext.request.contextPath}/getDate";
  	  var userInfoURL="${pageContext.request.contextPath}/getUserInfo";
  	  var successURL="${pageContext.request.contextPath}/success"; 
  	  var successURL="${pageContext.request.contextPath}/home";
  	  
  	  
  		inputDisabledInitialization();
  
  	  
		//****************************************************************************************
		//
		//Capability
		//
		//****************************************************************************************

  	  
		$.getJSON(capabilityURL, {
								tags : "capability",
								tagmode : "any",
								format : "json"
							}).done(
									function(data) {

										console.log("size: "+data.length);
										console.log("Capabilities JSON: "
												+ JSON.stringify(data));
										
									       //clean the data recieved before, don't use capabilities=[] 
									       capabilities.length=0;
									       //alert("0 capabilities size: "+capabilities.length);
										var i;
										for (i = 0; i < data.length; i++)
											capabilities.push(data[i].resource);
										//alert("1 capabilities size: "+capabilities.length);
									});

							$('#capability-search').autocomplete({
								source : capabilities,
								minLength : 0,
								scroll : true,
								select:function(event, ui)
								{
									//alert("2 capabilities size: "+capabilities.length);
									capabilitySelected=ui.item.value;	
									console.log("capability Selected:"+capabilitySelected);
								
									//clean the input value below
									$(".datepicker").val('');
									$("#timeinterval-remains").val('');
									$("#hall-remains").val('');
									$("#court-remains").val('');									
								    
									
									
									$.ajax({
									    type: 'POST',
									    url: capabilityURL,
									    async: false,
									    data: capabilitySelected,
									    
									    contentType: "application/json; charset=utf-8", 
									    
									    success: function(data) {
									       console.log('response data = ' + JSON.stringify(data));
									       
									       
									     //only unblock dater picker
											$(".datepicker").prop('disabled',false);
											$("#timeinterval-remains").prop('disabled',true);
											$("#hall-remains").prop('disabled',true);
											$("#court-remains").prop('disabled',true);
											
							      
									    },
									    error: function(xhr, textStatus, error){
									        console.log("status: "+textStatus);
									        console.log("error: "+error);
									    }
									});
									
								}
							}).focus(function() {
								$(this).autocomplete("search", "");
							});

							//****************************************************************************************
							//
							//Date Picker
							//
							//****************************************************************************************

							// $(".datepicker").datepicker({ dateFormat: 'yy-mm-dd' });
							
							$(".datepicker").datepicker(
									{
										//dateFormat: 'dd/mm/yy',
										dateFormat: 'yy-mm-dd',
										onSelect:function(dateText)
										{											
											/* console.log("date selected: "+dateText); */
											dateSelected=dateText;
											console.log("date selected: "+dateSelected);
											
											
											//clean the input value below											
											$("#timeinterval-remains").val('');
											$("#hall-remains").val('');
											$("#court-remains").val('');
											
											$.ajax({
											    type: 'POST',
											    url: dateSelectedURL,
											    async: false,
											    data: dateSelected,
											    
											    contentType: "application/json; charset=utf-8", 
											    
											    success: function(data) {
											    	
											    	
													//unblock timeinterval
													$("#timeinterval-remains").prop('disabled',false);
													$("#hall-remains").prop('disabled',true);
													$("#court-remains").prop('disabled',true);
											    	
											       console.log("response data = " + data);											       
													//****************************************************************************************
													//
													//Time Intervals
													//
													//****************************************************************************************

								       				
													$.getJSON(intervalURL, {
														tags : "interval",
														tagmode : "any",
														format : "json"
													}).done(
															function(data) {

																console.log("Intervals JSON: "
																		+ JSON.stringify(data));
																intervals.length=0;
																var i;
															
																for (i = 0; i < data.length; i++)
																{
																	var first,second,third;
																	first=data[i].toString()+"h";
																	second="---";
																	if(data[i]==24)																	
																		third="0h";
																	else
																		third=(data[i]+1).toString()+"h";
																	intervals.push(first+second+third);	
																	console.log("i="+i+" "+intervals[i]);
																		
																	
																}
																console.log("data size recieved: "+intervals.length);																
																
															});
												
											    },
											    error: function(xhr, textStatus, error){
											        console.log("status: "+textStatus);
											        console.log("error: "+error);
											    }
											});
								
										}
			
									}
							
							);
							
			
										
							
							$('#timeinterval-remains').autocomplete({
								source : intervals,
								minLength : 0,
								scroll : true,
								
								select:function(event, ui)
								{
									timeIntervalSelected=(ui.item.value).split("h")[0];	
									console.log("start time selected:"+timeIntervalSelected);
									
									
									//clean the input value below
									$("#hall-remains").val('');
									$("#court-remains").val('');
									
									$.ajax({
									    type: 'POST',
									    url: intervalURL,
									    async: false,
									    data: timeIntervalSelected,
									    
									    contentType: "application/json; charset=utf-8", 
									    
									    success: function(data) {
									       console.log('response data = ' + JSON.stringify(data));
									       
											//unblock hall
											$("#hall-remains").prop('disabled',false);
											$("#court-remains").prop('disabled',true);
									       
											//****************************************************************************************
											//
											//Halls
											//after post the start time, retrieve the data of halls available
											//
											//****************************************************************************************
											
											$.getJSON(hallURL, {
																	tags : "hall",
																	tagmode : "any",
																	format : "json"
																}).done(
																		function(data) {

																			console.log("Halls JSON: "
																					+ JSON.stringify(data));
																			halls.length=0;
																			var i;
																			for (i = 0; i < data.length; i++)
																				halls.push(data[i].name);
																		});
											
											

									    },
									    error: function(xhr, textStatus, error){
									        console.log("status: "+textStatus);
									        console.log("error: "+error);
									    }
									});
									
								}
								
							}).focus(function() {
								$(this).autocomplete("search", "");
							});


							
							
							
			
							//****************************************************************************************
							//
							//Halls
							//
							//****************************************************************************************


							$('#hall-remains').autocomplete({
								source : halls,
								minLength : 0,
								scroll : true,
								select:function(event, ui)
								{
									hallSelected=ui.item.value;	
									console.log("hall selected:"+hallSelected);
									
									
									//clean the input value below
									$("#court-remains").val('');
									
									
									
									$.ajax({
									    type: 'POST',
									    url: hallURL,
									    async: false,
									    data: hallSelected,
									    
									    contentType: "application/json; charset=utf-8", 
									    
									    success: function(data) {
									       console.log('response data = ' + JSON.stringify(data));									       
									       
									       //unblock court
											$("#court-remains").prop('disabled',false);
											//****************************************************************************************
											//
											// get Courts available in that hall
											//
											//****************************************************************************************

									       
									       
											
											$.getJSON(courtURL, {
																	tags : "court",
																	tagmode : "any",
																	format : "json"
																}).done(
																		function(data) {

																			console.log("Courts JSON: "
																					+ JSON.stringify(data));
																			courts.length=0;
																			var i;
																			for (i = 0; i < data.length; i++)
																				courts.push(data[i].name);
																		});

									       
									       
							      
									    },
									    error: function(xhr, textStatus, error){
									        console.log("status: "+textStatus);
									        console.log("error: "+error);
									    }
									});
									
								}
							}).focus(function() {
								$(this).autocomplete("search", "");
							});

							//****************************************************************************************
							//
							// Courts
							//
							//****************************************************************************************


							$('#court-remains').autocomplete({
								source : courts,
								minLength : 0,
								scroll : true,
								select:function(event, ui)
								{
									courtSelected=ui.item.value;	
									console.log("capability Selected:"+courtSelected);
									
									
									
									$.ajax({
									    type: 'POST',
									    url: courtURL,
									    async: false,
									    data: courtSelected,
									    
									    contentType: "application/json; charset=utf-8", 
									    
									    success: function(data) {
									       console.log('response data = ' + JSON.stringify(data));
									       									
									    },
									    error: function(xhr, textStatus, error){
									        console.log("status: "+textStatus);
									        console.log("error: "+error);
									    }
									});
									
								}
							}).focus(function() {
								$(this).autocomplete("search", "");
							});
								
					
							
							$("#reserve-submit").click(function()
									{
										name=$("#user-name").val();
										email=$("#user-email").val();
										tele=$("#user-tele").val();
										
										console.log("name: "+name);
										console.log("email: "+email);
										console.log("tele: "+tele);
								
										
										$.ajax({ 
											type: "POST",
											url: userInfoURL, 
											async: false,
											//data: "{'name':name, 'email':email,'tele':tele}", 
											data: name+','+email+','+tele, 
											contentType: "application/json; charset=utf-8", 
											//dataType: "json",  
									
											success: function(data) {
												       console.log('response data = ' + JSON.stringify(data));										      
												        window.location=successURL; 
												       
												    },
										    error: function(xhr, textStatus, error){
												        console.log("status: "+textStatus);
												        console.log("error: "+error);
												    }	
														    
									
										});
										
										
							
									});							
							
														
						});
	
	  function inputDisabledInitialization()
  	  {
  		$("#capability-search").prop('disabled',false);
  	  	$(".datepicker").prop('disabled',true);
  		$("#timeinterval-remains").prop('disabled',true);
  		$("#hall-remains").prop('disabled',true);
  		$("#court-remains").prop('disabled',true);  		
  	  }
  	
	
	</script>
 


<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">


		<div class="body">

			<div id="reservation-body">

				<div class="main-zone">

					<div class="left-zone">

						<div id="sports-post">
							<img alt="sports" class="sports-post"
								src="<c:url value="/resources/image/sports.jpg"/>">
						</div>


					</div>



					<div class="right-zone">


						<div>
							<p class="reservation-header">Reservation Form</p>
						</div>

						<div id="reservation-form">


							<div class="form-field">
								<p>
									<input type="text" id="capability-search"
										placeholder="Sports Name" value="">
								</p>
							</div>


							<div class="form-field">
								<p>
									<input type="text" class="datepicker"
										placeholder="Reserve Date" />
								</p>
							</div>


							<div class="form-field">
								<p>
									<input type="text" id="timeinterval-remains"
										placeholder="Time Available" value="">
								</p>
							</div>


							<div class="form-field">
								<p>
									<input type="text" id="hall-remains"
										placeholder="Halls Available" value="">
								</p>
							</div>

							<div class="form-field">
								<p>
									<input type="text" id="court-remains"
										placeholder="Courts Available" value="">
								</p>
							</div>


							<div class="form-field">
								<p>
									<input type="text" id="user-name" placeholder="Name" value="">
								</p>
							</div>

							<div class="form-field">
								<p>
									<input type="text" id="user-email" placeholder="Email" value="">
								</p>
							</div>

							<div class="form-field">
								<p>
									<input type="text" id="user-tele" placeholder="Tele" value="">
								</p>
							</div>

							<div>
								<span>
									<button id="reserve-submit" type="submit" class="myButton">Submit</button>
								</span>
							</div>

						</div>
					</div>
				</div>
			</div>

		</div>



	</tiles:putAttribute>
</tiles:insertDefinition>
