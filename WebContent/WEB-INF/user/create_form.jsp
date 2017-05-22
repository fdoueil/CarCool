<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<form class="form-horizontal" method="post" action="registerUser">

		<c:import url="/WEB-INF/user/form.jsp"/>	

		<div class="form-group ">
			<button type="submit"
				class="btn btn-primary btn-lg btn-block login-button">Enregistrer</button>
		</div>
		  <div class="form-group ">
			<div class="cols-sm-10" style="color: #0033cc; text-align: center">${actionMessage}</div>
		</div>
	</form>