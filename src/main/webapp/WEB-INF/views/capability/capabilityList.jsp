<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">



	<jsp:include page="/WEB-INF/views/includes/header.jsp" />

	<jsp:include page="/WEB-INF/views/includes/logo.jsp" />
	
	
		<div>

			<h1>Capability List</h1>

			<table id="hovertable" class="hoverable">
				<thead>
				<tr>
					<th>Resource Type</th>
					<th>Condition</th>
					<th>Discription</th>
					
				</tr>
				</thead>
				<c:forEach items="${capabilities}" var="capability">
					<tr class="hoverable" id="id${capability.id}" onClick="document.location.href = 'getCapability?capabilityId=${capability.id}';">
						<td>${capability.resource}</td>
						<td>${capability.condition}</td>
						<td>${capability.discription}</td>
						
					</tr>
				</c:forEach>
			</table>
			<br/>
			<a href="newCapability">Add Capability</a>

			<jsp:include page="/WEB-INF/views/includes/footer.jsp" />

		 </div>

		 
		 
		 
</div>

	</tiles:putAttribute>
</tiles:insertDefinition>