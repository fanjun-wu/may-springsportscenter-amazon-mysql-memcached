<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



	<div class="menu">
		<div class="main-menu">
	
		Menu:
		<ul>
			<li><spring:url value="/home" var="homeUrl" htmlEscape="true" />
				<a href="${homeUrl}">Home</a></li>
			<li><spring:url value="/about" var="aboutUrl" htmlEscape="true" />
				<a href="${aboutUrl}">About</a></li>

			<li><spring:url value="/reservation" var="reservationUrl"
					htmlEscape="true" /> <a href="${reservationUrl}">Reservation</a></li>

		</ul>
		
		</div>
		
		
		<div class="notice">
		Notice:
		<div>
			<ul>
				<li> <a href="#1">Notice 1</a></li>
				<li> <a href="#2">Notice 2</a></li>
				<li> <a href="#3">Notice 3</a></li>
				<li> <a href="#4">Notice 4</a></li>
				<li> <a href="#5">Notice 5</a></li>
				<li> <a href="#6">Notice 6</a></li>			
			</ul>

		</div>

		</div>
		
		
		
		
		
		
		<div class="news">
		News:
		<div>
			<ul>
				<li> <a href="#1">News 1</a></li>
				<li> <a href="#2">News 2</a></li>
				<li> <a href="#3">News 3</a></li>
				<li> <a href="#4">News 4</a></li>
				<li> <a href="#5">News 5</a></li>
				<li> <a href="#6">News 6</a></li>			
			</ul>

		</div>

		</div>
		
		
	</div>


	
