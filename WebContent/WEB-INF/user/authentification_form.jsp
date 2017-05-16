<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<!-- Top content -->
	<div class="top-content">
		<div class="inner-bg">
			<div class="row">
				<c:import url="/WEB-INF/menu/menu.jsp" />
				<div class="col-md-10">
					<p></p>
					<div class="panel-heading">
						<div class="panel-title text-center">
							<h2 class="title" style="color: #FFFFFF;">Entrez vos identifiants</h2>
							<hr />
						</div>
					</div>
					<div class="container">
						<div class="row">
							<div class="col-md-12">
									<form class="form-horizontal" method="post" action="authentification">
										<label for="inputEmail" class="cols-sm-3 control-label">Adresse email</label>
        									<input type="email" id="inputEmail" class="form-control" placeholder="Adresse mail" name=email required autofocus>
										<label for="inputPassword" class="sr-only">Mot de passe</label>
        									<input type="password" id="inputPassword" class="form-control" placeholder="Mot de passe" name=password autocomplete="off" required>
										<button class="btn btn-lg btn-primary btn-block" type="submit">S'authentifier</button>
									</form>
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
</div>
