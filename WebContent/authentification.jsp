<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">

<!-- Website CSS style -->
<link rel="stylesheet" type="text/css" href="assets/css/main.css">

<!-- Website Font style -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

<!-- Google Fonts -->
<link href='https://fonts.googleapis.com/css?family=Passion+One'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oxygen'
	rel='stylesheet' type='text/css'>
<title>S'authentifier</title>
</head>
<body>

			<div class="top-content">
	    	<div class="inner-bg">
	    		<div class="row">
					<c:import url="/WEB-INF/menu/menu.jsp"/>
					<div class="col-md-10">
						<p></p>
						<div class="panel-heading">
			               <div class="panel-title text-center">
			               		<h2 class="title" style="color: #4d94ff;">Entrez vos identifiants</h2>
			               		<hr />
			               	</div>
			            </div> 
						<div class="main-login main-center">	
							<c:import url="/WEB-INF/user/authentification_form.jsp" />
							<!--<c:if test="${actionResult=='1'}">
								<c:import url="/WEB-INF/user/card.jsp"/>
								<div class="form-group ">
									<a href=<c:url value='/'/> class="btn btn-primary btn-block">Retour à l'accueil</a>
								</div>
							</c:if>-->	
						</div>
					</div>
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