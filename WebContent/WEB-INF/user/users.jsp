<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>Users</title>
</head>
<body>
	<!-- Top content -->
	<div class="top-content">
		<div class="inner-bg">
			<div class="row">
				<c:import url="/WEB-INF/menu/menu.jsp" />
				<div class="col-md-10">
					<p></p>
					<div class="panel-heading">
						<div class="panel-title text-center">
							<h2 class="title" style="color: #4d94ff;">Liste des
								utilisateurs inscrits</h2>
							<hr />
						</div>
					</div>
					<div class="container">
						<div class="row">
							<div class="col-md-12">
								<table class="table table-bordered">
						            <thead>
						                <tr class="bg-primary">
						                	<th>Email</th>
						                	<th>Name</th>
						                	<th>Catégorie</th>
						                </tr>
						            </thead>
						            <tbody>
										<c:forEach items="${users}" var="user" >
											<tr class="success">
												<td><c:out value="${user.email}" /></td>
												<td><c:out value="${user.nom}" /></td>
												<td><c:out value="${user.categorie}" /></td>
												<td><a class="btn btn-primary glyphicon glyphicon-user" href=<c:url value='/viewprofileuser?email='/><c:out value='${user.email}'/>></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="assets/js/jquery-1.11.1.min.js"></script>
	<script src="assets/js/jquery.backstretch.min.js"></script>
	<script src="assets/js/scripts.js"></script>
	<script type="text/javascript" src="assets/js/bootstrap.js"></script>

	<c:import url="/WEB-INF/footer/footer.html" />
</body>

</html>