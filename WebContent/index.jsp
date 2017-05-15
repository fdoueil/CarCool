<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
			<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDjCW_E-FQsQOcbgaiNYj7PSZ_JTzICvZQ&callback=initMap" async defer></script>
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
	<body onload="initialize()">
		<!-- Top content -->
		<div class="top-content">
	    	<div class="inner-bg">
	    		<div class="row">
					<c:import url="/WEB-INF/menu/menu.jsp"/>
					<div class="col-md-10">
						<p></p>
						<div class="panel-heading">
							<div class="panel-title text-center">
								<h2 class="title" style="color: #4d94ff;">Liste des
									utilisateurs de l'application Covoiturage</h2>
								<hr />
							</div>
						</div>
						<div class="container">
							<div class="row">
								<div class="col-md-2"></div>
								<div class="col-md-8">
									<div id="map" style="width: 720px; height: 720px;"></div>
									<div>
										<input id="address" type="textbox" value="Labège, FR, Rue Edmond Rostand">
									</div>
								</div>
								<div class="col-md-2"></div>
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
	    
		<c:import url="/WEB-INF/footer/footer.html"/>
		
		<script type="text/javascript">
			var geocoder;
			var locations = ["Saint-Pierre de Lages", "Fonsorbes","Portet-sur-Garonne"];
			var map;
			
			function initialize() {
				geocoder = new google.maps.Geocoder();
				var blLatLng = {lat: 43.541252, lng: 1.511911};
				var mapOptions = {
				  zoom: 10,
				  center: blLatLng
				}
				map = new google.maps.Map(document.getElementById('map'), mapOptions);
				
				var blMarker = new google.maps.Marker({
			          position: blLatLng,
			          map: map,
			          title: 'Berger-Levrault'
			     });
				
				codeAddress();
				
				plotMarkers();
				
			}
	
			function plotMarkers() {
				var i;
	
				for (i = 0; i < locations.length; i++) {  
					codeThisAddress(locations[i]);
				}
			}
			
			function codeAddress() {
				var address = document.getElementById('address').value;
				codeThisAddress(address);
			}
			
			function codeThisAddress(thisadress) {
				
				geocoder.geocode( { 'address': thisadress}, function(results, status) {
				  if (status == 'OK') {
					map.setCenter(results[0].geometry.location);
					var marker = new google.maps.Marker({
						map: map,
						position: results[0].geometry.location,
						title: 'Click Me '
					});
					
					// process multiple info windows
					(function(marker) {
						// add click event
						google.maps.event.addListener(marker, 'click', function() {
							infowindow = new google.maps.InfoWindow({
								content: 'Hello, World!!'
							});
							infowindow.open(map, marker);
						});
					})(marker);
					
				  } else {
					alert('Geocode was not successful for the following reason: ' + status);
				  }
				});
			}
			
			/*function AutoCenter() {
			  //  Create a new viewpoint bound
			  var bounds = new google.maps.LatLngBounds();
			  //  Go through each...
			  $.each(markers, function (index, marker) {
			  bounds.extend(marker.position);
			  });
			  //  Fit these bounds to the map
			  map.fitBounds(bounds);
			}
			AutoCenter();*/
	
	  </script>
	</body>

</html>