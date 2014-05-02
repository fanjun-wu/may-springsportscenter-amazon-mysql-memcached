<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


	<jsp:include page="/WEB-INF/views/includes/header.jsp" />

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">



	<jsp:include page="/WEB-INF/views/includes/logo.jsp" />
		<div>
		
			<h1>Modify Capability</h1>
			
			
			<form:form name="modifyCapabilityForm" commandName="capability" action="updateCapability" method="post">
				<jsp:include page="/WEB-INF/views/includes/capability/capabilityDetails.jsp" />
				<br/>
				<a href="capabilityList">Back to list</a>&nbsp;&nbsp;&nbsp;
				<a href="javascript: document.forms.modifyCapabilityForm.submit();">Update capability</a>&nbsp;&nbsp;&nbsp;
				<a href="deleteCapability?capabilityId=${capability.id}">Delete capability</a>&nbsp;&nbsp;&nbsp;
				
			</form:form>
			
			<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
		
		 </div>

		 
		 
</div>

	</tiles:putAttribute>
</tiles:insertDefinition>