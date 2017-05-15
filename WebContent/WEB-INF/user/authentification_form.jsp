<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
Entrez vos identifiants:<br />
<form method="post" action="j_security_check">
	Nom d'utilisateur:<br /> <input type="text" name="j_username"> <br />
	Mot de passe:<br /> <input type="password" name="j_password" autocomplete="off"> <br />
	<input type="submit" value="Valider"> <br />
</form>
