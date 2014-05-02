<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
				<table>
				
					
					<tr>
						<td>Hall Name</td>
						<td>
							<form:input type="hidden" path="id" value="${hall.id}"/>
							<form:input type="text" path="name" value="${hall.name}"/>
						</td>
					</tr>
					
					
					
					<tr>
						<td>Open Time</td>
						<td>
							<form:input type="text" path="openTime"/>
						</td>
					</tr>
					
					<tr>
						<td>Close Time</td>
						<td>
							<form:input type="text" path="closeTime"/>
						</td>
					</tr>
					
					<tr>
						<td>Decription</td>
						<td>
							<form:input type="text" path="introduction"/>
						</td>
					</tr>
				</table>