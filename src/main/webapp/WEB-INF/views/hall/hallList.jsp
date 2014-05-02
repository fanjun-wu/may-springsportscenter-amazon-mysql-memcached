<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">



	<jsp:include page="/WEB-INF/views/includes/header.jsp" />

	<jsp:include page="/WEB-INF/views/includes/logo.jsp" />
	
	
		<div>
		
			<h1>Hall List</h1>
			
		 <table id="hovertable" class="hoverable">
				<thead>
				<tr>
					<th>Hall Name</th>
					<th>Open Time</th>
					<th>Close Time</th>
					<th>Description</th>
				</tr>
				</thead>
				<c:forEach items="${halls}" var="hall">
				 <tr class="hoverable" id="id${hall.id}" onClick="document.location.href = 'getHall?hallId=${hall.id}';"> 
					
						<td>${hall.name}</td>
						<td>${hall.openTime}</td>
						<td>${hall.closeTime}</td>
						<td>${hall.introduction}</td>
						
					</tr>
				</c:forEach> 
				
					
				
				
			</table>
			<br/>
		 <a href="newHall">Add Hall</a>  
			
			<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
		
		 </div>
	
	
	
</div>

	</tiles:putAttribute>
</tiles:insertDefinition>