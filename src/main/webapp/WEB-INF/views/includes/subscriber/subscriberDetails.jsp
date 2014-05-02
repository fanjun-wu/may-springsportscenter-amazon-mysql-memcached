<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

				<table>
					<tr>
						<td>Subscriber Name</td>
						<td>
							<form:input type="hidden" path="id" value="${subscriber.id}"/>
							<form:input type="text" path="name" value="${subscriber.name}"/>
						</td>
					</tr>
					
					<tr>
						<td>Email</td>
						<td>
							<form:input type="text" path="email" value="${subscriber.email}"/>
						</td>
					</tr>
					
					<tr>
						<td>Telephone</td>
						<td>
							<form:input type="text" path="telephone" value="${subscriber.telephone}"/>
						</td>
					</tr>
					
					<tr>
						<td>Gender</td>
						<td>
							<form:input type="text" path="gender" value="${subscriber.gender}"/>
						</td>
					</tr>
					
					<tr>
						<td>Age</td>
						<td>
							<form:input type="text" path="age" value="${subscriber.age}"/>
						</td>
					</tr>
				 
					
					<tr>
						<td>Privilege</td>
						<td>
							<form:input type="text" path="authenKey" value="${subscriber.authenKey}"/>
						</td>
					</tr>
					
					
				</table>