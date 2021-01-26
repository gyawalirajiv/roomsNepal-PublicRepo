function initMap() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition)
    }
}

function showPosition(position) {
    var longitutde = position.coords.longitude;
    var latitude = position.coords.latitude;

    var markerPosition = {lat: latitude, lng: longitutde}
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15,
        center: markerPosition
    });

    var marker = new google.maps.Marker({
        position: markerPosition,
        map: map
    });

    google.maps.event.addListener(map, 'click', function (event) {
        marker.setPosition(event.latLng);
        document.getElementById("lat").value = marker.getPosition().lat();
        document.getElementById("lng").value = marker.getPosition().lng();
    });

}
