<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

				<table>
					<tr>
						<td>Start Time</td>
						<td>
							<%-- <form:input type="hidden" path="id" value="${timeIntervalPOJO.id}"/> --%>
							<form:input type="text" path="startTime" value="${timeIntervalPOJO.startTime}"/>
						</td>
					</tr>
					
					<tr>
						<td>Date</td>
						<td>
							<form:input type="date" path="date" value="${timeIntervalPOJO.date}"/>
						</td>
					</tr>
					
					
					
					
				</table>