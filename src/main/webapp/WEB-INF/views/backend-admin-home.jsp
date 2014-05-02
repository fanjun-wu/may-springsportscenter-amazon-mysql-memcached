<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>    
	<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	<title>Home</title>	
</head>
<body>
	
	<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
	
	

	<jsp:include page="/WEB-INF/views/includes/logo.jsp" />
	
<h1>
	Spring Sports Center Backend Admin
</h1>


<p>
	The following controllers are available:</p>
	<ul>
		
		 <%-- <li><a href="<c:url value="admin/hallList"/> ">HallController</a></li>
		<li><a href="<c:url value="admin/adminList"/>">AdminController</a></li>
		<li><a href="<c:url value="admin/courtList"/>">CourtController</a></li>
		<li><a href=" <c:url value="admin/capabilityList"/> ">CapabilityController</a></li>
		<li><a href="<c:url value="admin/reservationList"/> ">ReservationController</a></li>
		<li><a href="<c:url value="admin/timeIntervalList"/> ">TimeIntervalController</a></li>
		<li><a href="<c:url value="admin/subscriberList"/> ">SubscriberController</a></li>
		  --%>
		  
		 <li><a href="hallList">HallController</a></li>
		<li><a href="adminList">AdminController</a></li>
		<li><a href="courtList">CourtController</a></li>
		<li><a href="capabilityList ">CapabilityController</a></li>
		<li><a href="reservationList ">ReservationController</a></li>
		<li><a href="timeIntervalList ">TimeIntervalController</a></li>
		<li><a href="subscriberList">SubscriberController</a></li>
		 
		 
	</ul>


<jsp:include page="/WEB-INF/views/includes/footer.jsp" />



</div>

	</tiles:putAttribute>
</tiles:insertDefinition>


</body>
</html>
