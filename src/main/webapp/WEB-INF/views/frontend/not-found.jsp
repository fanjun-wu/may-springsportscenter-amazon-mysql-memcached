<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	
	setTimeout(function(){homePageLoad();}, 2000);
	
	function homePageLoad()
	{
		
		var homeURL="${pageContext.request.contextPath}/home";    		
		window.location.replace(homeURL);
		
	}	
});


</script>



<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">



<h1>Sorry, the page you are looking for is not found, please check you url for cancel reservation</h1>

</div>

	</tiles:putAttribute>
</tiles:insertDefinition>