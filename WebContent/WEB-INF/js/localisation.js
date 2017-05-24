$(document).ready(function() {

	$('#adresse').blur(function() {
		var geocoder = new google.maps.Geocoder();
		var address = $("#adresse").val();
		geocoder.geocode({
			'address' : address
		}, function(results, status) {
			if (status === 'OK') {
				$('#latitude').val(results[0].geometry.location.lat());
				$('#longitude').val(results[0].geometry.location.lng());
			}
		});
	});
});
