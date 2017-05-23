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
            <title>MonTrajet</title>

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
                                <h2 class="title" style="color: #4d94ff;">Votre trajet</h2>
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
                                

                       <div>
    <input id="submit" type="button" value="Tracer route" onClick="calculateAndDisplayRoute();">
    </div>
    
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
        

    <script>

      // variables concernant calculateAndDisplayRoute()
      var directionsService;
      var directionsDisplay;
      var depart = "27 Rue Castelvielh, 31605 Muret"; // adresse bouchonée, récupérer adresse user
      var arrivee = " 64 Rue Jean Rostand, 31670 Labège"; // adresse en dur (BL)

      // tableaux bouchonés pour les markers des conducteurs et des passagers
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

      function initialize() {

        directionsService = new google.maps.DirectionsService;
        directionsDisplay = new google.maps.DirectionsRenderer;
        var blLatLng = {lat: 43.541252, lng: 1.511911};
        map = new google.maps.Map(document.getElementById('map'), {
          zoom: 11,
          center: blLatLng
        });
        directionsDisplay.setMap(map);

        var blMarker = new google.maps.Marker({
                      position: blLatLng,
                      map: map,
                      icon: 'assets/img/logo_bergerlevrault.png',
                      title: 'Berger-Levrault'
                 });

        plotDriversMarkers();
        plotRidersMarkers();


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
            }


      function calculateAndDisplayRoute() {
        directionsService.route({
          origin: depart,
          destination: arrivee,
          travelMode: 'DRIVING'
        }, function(response, status) {
          if (status === 'OK') {
            directionsDisplay.setDirections(response);
          } else {
            window.alert('Directions request failed due to ' + status);
          }
        });
      }
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDjCW_E-FQsQOcbgaiNYj7PSZ_JTzICvZQ&callback=initMap"
        async defer></script>
  </body>
</html>