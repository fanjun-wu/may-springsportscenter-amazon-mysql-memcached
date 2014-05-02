<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">



	<jsp:include page="/WEB-INF/views/includes/header.jsp" />

	<jsp:include page="/WEB-INF/views/includes/logo.jsp" />
	
<div>





			<h1>Reservation List</h1>
						

			<table id="hovertable" class="hoverable">
				<thead>
				<tr>
					<th>Discription</th>
					<th>Tips</th>
					<th>Time Reserved</th>
					<th>Subscriber</th>
					
					<th>Court Name</th>
					
				</tr>
				</thead>
				<c:forEach items="${reservations}" var="reservation">
					<tr class="hoverable" id="id${reservation.id}" onClick="document.location.href = 'getReservation?reservationId=${reservation.id}';">
						<td>${reservation.discription}</td>
						<td>${reservation.tips}</td>
						
						
						<td>${reservation.timeInterval}</td>
						
						<td>${reservation.subscriber}</td>
						<th>${reservation.court}
						
					</tr>
				</c:forEach>
			</table>
			<br/>
			<a href="newReservation">Add Reservation</a>

			<jsp:include page="/WEB-INF/views/includes/footer.jsp" />

		 </div>

		 
		 </div>

	</tiles:putAttribute>
</tiles:insertDefinition>

		 