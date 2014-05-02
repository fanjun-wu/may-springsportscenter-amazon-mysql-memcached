<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value="/resources/css/reservation.css" />" rel="stylesheet">

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<div class="body">
			<h1>Spring</h1>

			<p>Spring is one of the four conventional temperate seasons, following winter and preceding summer. 
			There are various technical definitions of spring, but local usage of the term varies according to 
			local climate, cultures and customs. When it is spring in the Northern Hemisphere, it will be autumn 
			in the Southern Hemisphere. At the spring equinox, days are close to 12 hours long with day length 
			increasing as the season progresses. Spring and "springtime" refer to the season, and also to ideas 
			of rebirth, rejuvenation, renewal, resurrection and regrowth. Subtropical and tropical areas have 
			climates better described in terms of other seasons, e.g. dry or wet, monsoonal or cyclonic. Often the 
			cultures have locally defined names for seasons which have little equivalence to the terms originating 
			in Europe
				</p>

			<div >			
				<img alt="Spring" class="about-background" src="<c:url value="/resources/image/spring-sports-center.jpg"/>">
			</div>	

		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>