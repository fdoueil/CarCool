<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
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
                            <c:if test="${findUser=='true'}">
								<div class="panel-title text-center">
									<span style="color: #4d94ff;">Bienvenue ${authUser.getNom()}</span><br />
								</div>
							</c:if>	
                        </div>
                        <div class="container">
                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-8">
                                	Votre adresse : 
							        <input id="address" type="textbox" value="">
							        Rayon de recherche (kms) :
							        <input id="rayon" type="textbox" value="">
							        <input id="submit" type="button" value="Localiser" onClick="geocodeAddress();">
                                   <div id="map" style="width: 720px; height: 720px;"></div>
                                </div>
                                <div class="col-md-2">
                                    <p><img src="assets/img/conducteur.png" alt="Conducteurs" height="16" width="16">   Conducteurs</p>
                                    <p><img src="assets/img/passager.png" alt="Passagers" height="16" width="16">   Passagers</p>
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
        
        <c:import url="/WEB-INF/footer/footer.html"/>
        
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDjCW_E-FQsQOcbgaiNYj7PSZ_JTzICvZQ&callback=initMap"
        async defer></script>
        
        <script type="text/javascript">
            
            var locationsDrivers = [
              ['Cugnaux', 43.537373, 1.344962, 'François Hollande'],
              ['Balma', 43.606163, 1.500060, 'Jérome Cahuzac'],
              ['Roques', 43.506803, 1.351713, 'Manuel Vals']
            ];
            var locationsRiders = [
              ['Quint-Fonsegrives', 43.585884, 1.544735, 'Christine Lagarde'],
              ['Escalquens', 43.518855, 1.553071, 'Lolo Aibo']
            ];
            var map;
            var geocoder;
            function initialize() {
                var blLatLng = {lat: 43.541252, lng: 1.511911};
                var mapOptions = {
                  zoom: 11,
                  center: blLatLng
                }
                
                map = new google.maps.Map(document.getElementById('map'), mapOptions);
                var blMarker = new google.maps.Marker({
                      position: blLatLng,
                      map: map,
                      icon: 'assets/img/logo_bergerlevrault.png',
                      title: 'Berger-Levrault'
                 });
                
                //codeAddress();
                plotDriversMarkers();
                plotRidersMarkers();
                geocoder = new google.maps.Geocoder();
            }
    
            function plotDriversMarkers() {
                var i;
                for (i = 0; i < locationsDrivers.length; i++) {  
                    codeThisMarker(locationsDrivers[i][1], locationsDrivers[i][2],'assets/img/conducteur.png', locationsDrivers[i][3]);
                }
            }
            
            function plotRidersMarkers() {
                var i;
                for (i = 0; i < locationsRiders.length; i++) {  
                    codeThisMarker(locationsRiders[i][1], locationsRiders[i][2],'assets/img/passager.png', locationsRiders[i][3]);
                }
            }

            function codeThisMarker(latitude, longitude, icone, nom) {
                var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(latitude, longitude),
                    icon: icone,
                    map: map
                });
                
//                =======================
//                |Ajout des info-bulles|
//                =======================
//
//                 // process multiple info windows
//                 (function(marker) {
//                     // add click event
//                     google.maps.event.addListener(marker, 'mouseover', function() {
//                         infowindow = new google.maps.InfoWindow({
//                             content: nom                                         
//                         });
//                         infowindow.open(map, marker);
//                     });
//                 })(marker);
            }
 
	        function geocodeAddress() {
	        	 var resultsMap = map;
	         	 var address = document.getElementById('address').value;
	         	 var rayonEnMetres = (document.getElementById('rayon').value)*1000;
	        	 geocoder.geocode({'address': address}, function(results, status) {
	             if (status === 'OK') {
	             resultsMap.setCenter(results[0].geometry.location);
	             var marker = new google.maps.Marker({
	             map: resultsMap,
	             position: results[0].geometry.location
	             });
	             var cityCircle = new google.maps.Circle({
	            	 //strokeColor: '#FF0000',
	                 //strokeOpacity: 0.8,
	                 //strokeWeight: 2,
	                 //fillColor: '#FF0000',
	                 //fillOpacity: 0.35,
	            	 map: resultsMap,
	                 center: results[0].geometry.location,
	                 radius: rayonEnMetres
	               });
	        } else {
	             alert('Google Map ne peut pas géolocaliser cette adresse: ' + status);
	              }
	            });
          }
    
      </script>

    </body>

</html>