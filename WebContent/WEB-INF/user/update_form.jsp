<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<form class="form-horizontal" method="post" action="updateUser">

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
			<label for="password1" class="cols-sm-3 control-label">Mot de
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
			<label for="password2" class="cols-sm-3 control-label">Confirmation
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
			<label for="nom" class="cols-sm-3 control-label">Nom
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
			<label for="adresse" class="cols-sm-3 control-label">Adresse</label>
			<div class="cols-sm-10">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-users fa"
						aria-hidden="true"></i></span> <input type="text" class="form-control"
						name="adresse" id="adresse" placeholder="Saisissez votre adresse"
						value="${newTrajet.depuisAdresse}" />
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<label for="latitude" class="cols-sm-3 control-label">Latitude</label>
			<div class="cols-sm-10">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-users fa"
						aria-hidden="true"></i></span> <input type="text" class="form-control"
						name="latitude" id="latitude" placeholder="Saisissez votre latitude"
						value="${newTrajet.latDepart}" />
				</div>
			<label for="longitude" class="cols-sm-3 control-label">Longitude</label>
			<div class="cols-sm-10">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-users fa"
						aria-hidden="true"></i></span> <input type="text" class="form-control"
						name="longitude" id="longitude" placeholder="Saisissez votre longitude"
						value="${newTrajet.longDepart}" />
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<label for="pwd1" class="cols-sm-3 control-label">Type 
				d'usager</label>
			<div class="cols-sm-10">
				<div class="radio">
						<label><input type="radio"
						class=".radio-inline" name="typeusager" id="typeusager" value="Conducteur" checked
						> Conducteur </label>
						<label><input type="radio"
						class=".radio-inline" name="typeusager" id="typeusager" value="Passager"
						> Passager </label>
						<label><input type="radio"
						class=".radio-inline" name="typeusager" id="typeusager" value="LesDeux"
						> Les deux </label>
						
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