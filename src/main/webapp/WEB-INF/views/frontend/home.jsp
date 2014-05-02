
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<link href="<c:url value="/resources/css/hall.css"/>" rel="stylesheet"/>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

	<div class="body">

		<div class="app-map">

			<div id="hall-floor">

				<div class="left-zone">
					<div class="badminton-zone">

						<div id="badminton1">
							<img alt="badminton" class="badminton"
								src="<c:url value="/resources/image/badminton-h-3.jpg"/>">
						</div>

						<div id="badminton2">
							<img alt="badminton" class="badminton"
								src="<c:url value="/resources/image/badminton-h-3.jpg"/>">
						</div>

						<div id="badminton3">
							<img alt="badminton" class="badminton"
								src="<c:url value="/resources/image/badminton-h-3.jpg"/>">
						</div>

					</div>




					<div class="basketball-zone">
						<div id="basketball1">
							<img alt="basketball" class="basketball"
								src="<c:url value="/resources/image/basketball-h-1.jpg"/>">
						</div>

						<div id="basketball2">
							<img alt="basketball" class="basketball"
								src="<c:url value="/resources/image/basketball-h-1.jpg"/>">
						</div>

						<div id="basketball3">
							<img alt="basketball" class="basketball"
								src="<c:url value="/resources/image/basketball-h-1.jpg"/>">
						</div>

					</div>



					<div class="pingpong-zone">
						<div id="pingpong1">
							<img alt="pingpong" class="pingpong"
								src="<c:url value="/resources/image/pingpong-v-1.jpg"/>">
						</div>

						<div id="pingpong2">
							<img alt="pingpong" class="pingpong"
								src="<c:url value="/resources/image/pingpong-v-1.jpg"/>">
						</div>

						<div id="pingpong3">
							<img alt="pingpong" class="pingpong"
								src="<c:url value="/resources/image/pingpong-v-1.jpg"/>">
						</div>

						<div id="pingpong4">
							<img alt="pingpong" class="pingpong"
								src="<c:url value="/resources/image/pingpong-v-1.jpg"/>">
						</div>

						<div id="pingpong5">
							<img alt="pingpong" class="pingpong"
								src="<c:url value="/resources/image/pingpong-v-1.jpg"/>">
						</div>

						<div id="pingpong6">
							<img alt="pingpong" class="pingpong"
								src="<c:url value="/resources/image/pingpong-v-1.jpg"/>">
						</div>



						<div id="entrance">
							<img alt="entrance" class="entrance"
								src="<c:url value="/resources/image/entrance-v-1.png"/>">
						</div>

					</div>



				</div>

				<div class="right-zone">
					<div class="tennis-zone">
						<div id="tennis1">
							<img alt="tennis" class="tennis"
								src="<c:url value="/resources/image/tennis-v-1.jpg"/>">
						</div>

						<div id="tennis2">
							<img alt="tennis" class="tennis"
								src="<c:url value="/resources/image/tennis-v-1.jpg"/>">
						</div>


						<div id="tennis3">
							<img alt="tennis" class="tennis"
								src="<c:url value="/resources/image/tennis-v-1.jpg"/>">
						</div>

						<div id="tennis4">
							<img alt="tennis" class="tennis"
								src="<c:url value="/resources/image/tennis-v-1.jpg"/>">
						</div>


					</div>


				</div>

			</div>
		</div>
</div>


	</tiles:putAttribute>
</tiles:insertDefinition>