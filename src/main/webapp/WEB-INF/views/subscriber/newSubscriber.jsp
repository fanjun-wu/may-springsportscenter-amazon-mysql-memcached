<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">



	<jsp:include page="/WEB-INF/views/includes/header.jsp" />

	<jsp:include page="/WEB-INF/views/includes/logo.jsp" />
	
<div>





		
			<h1>New Subscriber</h1>
			
			
			<form:form name="newSubscriberForm" commandName="subscriber" action="saveSubscriber" method="post">
				<jsp:include page="/WEB-INF/views/includes/subscriber/subscriberDetails.jsp" />
				<br/>
				<a href="subscriberList">Back to list</a>&nbsp;&nbsp;&nbsp;
				<a href="javascript: document.forms.newSubscriberForm.submit();">Create new subscriber</a>&nbsp;&nbsp;&nbsp;
			</form:form>
			
			<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
		
		 </div>


</div>

	</tiles:putAttribute>
</tiles:insertDefinition>


