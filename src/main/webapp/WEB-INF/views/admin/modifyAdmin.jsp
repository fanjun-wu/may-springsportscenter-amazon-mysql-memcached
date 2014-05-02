<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">


	<jsp:include page="/WEB-INF/views/includes/header.jsp" />

	<jsp:include page="/WEB-INF/views/includes/logo.jsp" />
		<div>
		
			<h1>Modify Admin</h1>
			
			
			<form:form name="modifyAdminForm" commandName="admin" action="updateAdmin" method="post">
				<jsp:include page="/WEB-INF/views/includes/admin/adminDetails.jsp" />
				<br/>
				<a href="adminList">Back to list</a>&nbsp;&nbsp;&nbsp;
				<a href="javascript: document.forms.modifyAdminForm.submit();">Update admin</a>&nbsp;&nbsp;&nbsp;
				<a href="deleteAdmin?adminId=${admin.id}">Delete admin</a>&nbsp;&nbsp;&nbsp;
				<%-- <a href="employeeList?adminId=${admin.id}">Employee list</a>&nbsp;&nbsp;&nbsp; --%>
			</form:form>
			
			<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
		
		 </div>
	 
	 
	 

</div>

	</tiles:putAttribute>
</tiles:insertDefinition>