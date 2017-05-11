<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<form class="form-horizontal" method="post" action="register">

		<div class="form-group">
			<label for="email" class="cols-sm-3 control-label">Adresse
				email</label>
			<div class="cols-sm-10">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-envelope fa"
						aria-hidden="true"></i></span> <input type="email" required="true"
						pattern="[a-zA-Z0-9!#$%&amp;'*+\/=?^_`{|}~.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*"
						title="xxxx@xxxx.xxx" class="form-control" name="email" id="email"
						placeholder="Entrez votre adresse email" value="${newUser.email}" />
				</div>
				<span class="error text-danger">${errors['email']}</span>
				<!--<div style="color: #FF0000;">${errors['email']}</div>-->
			</div>
		</div>

		<div class="form-group">
			<label for="pwd1" class="cols-sm-3 control-label">Mot de
				passe</label>
			<div class="cols-sm-10">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock fa-lg"
						aria-hidden="true"></i></span> <input type="password" required="true"
						class="form-control" name="password1" id="password1"
						placeholder="Entrez votre mot de passe" />
				</div>
			</div>
		</div>

		<div class="form-group">
			<label for="pwd2" class="cols-sm-3 control-label">Confirmation
				du mot de passe</label>
			<div class="cols-sm-10">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock fa-lg"
						aria-hidden="true"></i></span> <input type="password" required="true"
						class="form-control" name="password2" id="password2"
						placeholder="Confirmez votre mot de passe" />
				</div>
				<span class="error text-danger">${errors['password1']}</span>
				<!--<span class="help-block" style="color: #FF0000;">${errors['pwd1']}</span>-->
				<!--<div style="color: #FF0000;">${errors['pwd1']}</div>-->
			</div>
		</div>

		<div class="form-group">
			<label for="name" class="cols-sm-3 control-label">Nom
				d'utilisateur</label>
			<div class="cols-sm-10">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-users fa"
						aria-hidden="true"></i></span> <input type="text" class="form-control"
						name="nom" id="nom" placeholder="Entrez le nom d'utilisateur"
						value="${newUser.nom}" />
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<label for="pwd1" class="cols-sm-3 control-label">Type 
				d'usager</label>
			<div class="cols-sm-10">
				<div class="radio">
						<label><input type="radio"
						class=".radio-inline" name="typeusager" id="typeusager" value="conducteur" checked
						> Conducteur </label>
						<label><input type="radio"
						class=".radio-inline" name="typeusager" id="typeusager" value="passager"
						> Passager </label>
						
				</div>
			</div>
		</div>

		<div class="form-group ">
			<button type="submit"
				class="btn btn-primary btn-lg btn-block login-button">Enregistrer</button>
		</div>
		<div class="form-group ">
			<div class="cols-sm-10" style="color: #0033cc; text-align: center">${actionMessage}</div>
		</div>
	</form>