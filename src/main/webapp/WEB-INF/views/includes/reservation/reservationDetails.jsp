<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

				<table>
					<tr>
						<td>Discription</td>
						<td>
							<form:input type="hidden" path="id" value="${reservation.id}"/>
							<form:input type="text" path="discription" value="${reservation.discription}"/>
						</td>
					</tr>
					
					<tr>
						<td>Tips</td>
						<td>
							<form:input type="text" path="tips" value="${reservation.tips}"/>
						</td>
					</tr>
					
					
					
					
				</table>