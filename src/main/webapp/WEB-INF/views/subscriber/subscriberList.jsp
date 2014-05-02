<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>




<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">



	<jsp:include page="/WEB-INF/views/includes/header.jsp" />

	<jsp:include page="/WEB-INF/views/includes/logo.jsp" />
	
<div>





			<h1>Subscriber List</h1>

			<table id="hovertable" class="hoverable">
				<thead>
				<tr>
					<th>Subscriber Name</th>
					<th>Reservation</th>
					<th>Email</th>
					<th>Telephone</th>
				</tr>
				</thead>
				<c:forEach items="${subscribers}" var="subscriber">
					<tr class="hoverable" id="id${subscriber.id}" onClick="document.location.href = 'getSubscriber?subscriberId=${subscriber.id}';">
						<td>${subscriber.name}</td>
						<th>${subscriber.reservation}</th>
						<td>${subscriber.email}</td>
						<td>${subscriber.telephone}</td>						
					
					</tr>
				</c:forEach>
			</table>
			<br/>
			<a href="newSubscriber">Add Subscriber</a>

			<jsp:include page="/WEB-INF/views/includes/footer.jsp" />

		 </div>
	 
	 
	 
	 </div>

	</tiles:putAttribute>
</tiles:insertDefinition>


	 