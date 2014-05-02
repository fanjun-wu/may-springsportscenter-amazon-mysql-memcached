<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

				<table>
					<tr>
						<td>Admin Name</td>
						<td>
							<form:input type="hidden" path="id" value="${admin.id}"/>
							<form:input type="text" path="name" value="${admin.name}"/>
						</td>
					</tr>
					
					<tr>
						<td>Email</td>
						<td>
							<form:input type="text" path="email" value="${admin.email}"/>
						</td>
					</tr>
					
					<tr>
						<td>Telephone</td>
						<td>
							<form:input type="text" path="telephone" value="${admin.telephone}"/>
						</td>
					</tr>
					
					<tr>
						<td>Gender</td>
						<td>
							<form:input type="text" path="gender" value="${admin.gender}"/>
						</td>
					</tr>
					
					<tr>
						<td>Age</td>
						<td>
							<form:input type="text" path="age" value="${admin.age}"/>
						</td>
					</tr>
				 
					
					<tr>
						<td>Privilege</td>
						<td>
							<form:input type="text" path="privilege" value="${admin.privilege}"/>
						</td>
					</tr>
					
					
				</table>