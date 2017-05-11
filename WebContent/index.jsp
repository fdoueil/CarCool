<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
			<meta name="viewport" content="width=device-width, initial-scale=1">
			<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">
	
			<!-- Website CSS style -->
			<link rel="stylesheet" type="text/css" href="assets/css/main.css">
	
			<!-- Website Font style -->
		    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
			
			<!-- Google Fonts -->
			<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
			<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
			<title>Home</title>
	</head>
	<body>
		<!-- Top content -->
		<div class="top-content">
	    	<div class="inner-bg">
	    		<div class="row">
					<c:import url="/WEB-INF/menu/menu.jsp"/>
				</div>
			</div>
		</div>
		
		<script src="assets/js/jquery-1.11.1.min.js"></script>
	    <script src="assets/js/jquery.backstretch.min.js"></script>
	    <script src="assets/js/scripts.js"></script>
	    <script type="text/javascript" src="assets/js/bootstrap.js"></script>
	    
		<c:import url="/WEB-INF/footer/footer.html"/>
	</body>

</html>