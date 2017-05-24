<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<div class="col-md-2">
		<div id="sidebar" class="well sidebar-nav">
			<h5>
				<i class="glyphicon glyphicon-home"></i> <small><b>TP
						GESTION DES UTILISATEURS</b></small>
			</h5>
			<ul class="nav nav-pills nav-stacked">
				<li><a href="/CarCool">Accueil</a></li>
				<!--<li><a href="#">Search</a></li>-->
			</ul>
			<h5>
				<i class="glyphicon glyphicon-user"></i> <small><b>UTILISATEURS</b></small>
			</h5>
			<ul class="nav nav-pills nav-stacked">
				<li><a href=<c:url value='/registerUser'/>>Créer un compte</a></li>	
				<li><a href=<c:url value='/authentification.jsp'/>>Connexion à mon espace</a></li>			
				<c:if test="${utilisateurConnecte=='1'}">
					<li><a href=<c:url value='/deconnexion'/>>Déconnexion</a></li>	
					<li><a href=<c:url value='/users'/>>Afficher la liste des utilisateurs</a></li>
					<li><a href=<c:url value='/deleteUser'/>>Supprimer mon compte</a></li>
					<li><a href=<c:url value='/updateUser'/>>Modifier mon compte</a></li>
				</c:if>
			</ul>
		</div>
</div>
