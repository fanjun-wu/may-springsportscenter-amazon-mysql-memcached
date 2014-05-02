<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page session="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Default tiles template</title>

<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	height: 100%;
	overflow: hidden;
}

.page {
	min-height: 100%;
	position: relative;
}

.header {
	width: 100%;
	text-align: center;
	background-color:#6699CC; 
	/* background-image:url("WEB-INF/template/image/header-footer-5.jpg"); */ 
	/* background-image:url('/resources/image/sports.jpg'); */
	/* background-image:url("../image/header-footer-5.jpg");  */
	
	 	
	border-radius:5px;
	
}

.content {
	padding: 10px;
	padding-bottom: 20px; /* Height of the footer element */
	background-color:white;
	
	overflow: hidden;
}

.menu {
	padding: 50px 10px 0px 10px;
	width: 200px;
	
	height:75%;
	float: left;
	
	background-color:#6699CC;
	
	border:1px solid;	 	
	border-radius:15px;
}

.body {
	position:fixed;
	/* background-color:yellow; */
	width:70%;
	height:70%;
	left:20%;
	top:13%;

}

.footer {
	
	position: absolute;
	left: 0;
	text-align: center;
	width: 100%;
	height: 5%;
	
	/* background-image:url('../image/hall-floor.jpg'); */
	 	
	border-radius:5px;
}


.main-menu
{
margin-top:5%;
width: 100%;
height: 30%;
border:1px solid;	 	
border-radius:5px;

}

.notice
{
margin-top:10px;
width: 100%;
height: 30%;
border:1px solid;	 	
border-radius:5px;

}
.news
{
margin-top:10px;
width: 100%;
height: 30%;
border:1px solid;	 	
border-radius:5px;

}



</style>


</head>

<body>
	<div class="page">
		<tiles:insertAttribute name="header" />
		<div class="content">
			<tiles:insertAttribute name="menu" />
			<tiles:insertAttribute name="body" />
		</div>
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html> 