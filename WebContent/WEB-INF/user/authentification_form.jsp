<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form class="form-horizontal" method="post" action="authentification">
	<div class="form-group">
		<label for="inputEmail" class="cols-sm-3 control-label">Adresse
		email</label> 

		<div class="cols-sm-10">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-envelope fa"
						aria-hidden="true"></i></span> <input type="email" required
						pattern="[a-zA-Z0-9!#$%&amp;'*+\/=?^_`{|}~.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*"
						title="xxxx@xxxx.xxx" class="form-control" name="email" id="email"
						placeholder="Entrez votre adresse email" value="${newUser.email}" />
				</div>
				<span class="error text-danger">${errors['email']}</span>
		</div>
	</div>
	<div class="form-group">
		<label for="inputPassword" class="cols-sm-3 control-label">Mot de passe</label> 
		<div class="cols-sm-10">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock fa-lg"
						aria-hidden="true"></i></span> <input type="password" required
						class="form-control" name="password" id="inputPassword"
						placeholder="Entrez votre mot de passe" />
				</div>
		</div>
	</div>
	<div class="form-group ">	
		<button class="btn btn-primary btn-lg btn-block login-button" type="submit">S'authentifier</button>
	</div>
</form>

