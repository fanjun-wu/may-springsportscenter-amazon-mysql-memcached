<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

				<table>
					<tr>
						<td>Court Name</td>
						<td>
							<form:input type="hidden" path="id" value="${court.id}"/>
							<form:input type="text" path="name" value="${court.name}"/>
						</td>
					</tr>
					
					<tr>
						<td>Introduction</td>
						<td>
							<form:input type="text" path="introduction" value="${court.introduction}"/>
						</td>
					</tr>
					
					
				</table>