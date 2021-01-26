function initMap() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition)
    }
}

function showPosition(position) {
    var longitute = $("#map-longitute").val();
    var latitute = $("#map-latitute").val();
    var mapOptions = {
        center: new google.maps.LatLng(longitute, latitute),
        zoom: 14,
    }
    var map = new google.maps.Map(document.getElementById('map'), mapOptions);
    var myCenter = new google.maps.LatLng(latitute, longitute);
    var marker = new google.maps.Marker({position: new google.maps.LatLng(longitute, latitute)});
    marker.setMap(map);
}
