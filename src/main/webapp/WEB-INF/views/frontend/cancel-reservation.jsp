<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<script type="text/javascript">

function cancelReservation(id)
{
	var cancelURL="${pageContext.request.contextPath}/admin/deleteSubscriber?subscriberId="+id;	
	
	$.ajax({
	    type: 'GET',
	    url: cancelURL,
	    async: false,
	    
	    success: function(data) {
	       console.log('response data = ' + JSON.stringify(data));
	       console.log("cancel successfully!");	
	       
	       setTimeout(function(){location.reload();}, 3000);
	    },
	    error: function(xhr, textStatus, error){
	        console.log("status: "+textStatus);
	        console.log("error: "+error);
	    }
	});
}



</script>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">



	<jsp:include page="/WEB-INF/views/includes/header.jsp" />

	<jsp:include page="/WEB-INF/views/includes/logo-for-cancellation.jsp" />
	
<div>

			<h1>Your Reservation</h1>

			<table id="hovertable" class="hoverable">
				<thead>
				<tr>
					<th>Subscriber Name</th>
					<th>Reservation</th>
					<th>Email</th>
					<th>Telephone</th>
					<!-- <th>Gender</th>
					<th>Age</th>
					 <th>AuthenKey</th> -->
					 <th>Cancel</th> 
					
				</tr>
				</thead>			
					<tr class="hoverable" id="id${subscriber.id}" >
						<td>${subscriber.name}</td>
						<th>${subscriber.reservation}</th>
						<td>${subscriber.email}</td>
						<td>${subscriber.telephone}</td>
						<%-- <td>${subscriber.gender}</td>
						<td>${subscriber.age}</td>
						<td>${subscriber.authenKey}</td> --%>
						<td><button type="button" onClick="cancelReservation(${subscriber.id})">Cancel!</button></td>
					
					</tr>
				
			</table>
			<br/>

			<jsp:include page="/WEB-INF/views/includes/footer.jsp" />

		 </div>
	 
	 
	 
	 </div>

	</tiles:putAttribute>
</tiles:insertDefinition>


	 