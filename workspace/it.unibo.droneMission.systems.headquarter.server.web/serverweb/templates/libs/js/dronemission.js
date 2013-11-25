var CHECKTIME = 300; // check new data every x millisec

var map;
var droneIcon;

function updateMap(latitude, longitude) {
  //console.info("LAT: " + latitude + " - LONG: " + longitude);
  myLatLng = new google.maps.LatLng(latitude,longitude);
  map.setCenter(myLatLng);
  droneIcon.setPosition(myLatLng);
}

function updateSensors() {
    var URL = "/ajax/sensors/latest";
    $.ajax({ 
        type: 'GET', 
        url: URL, 
        dataType: 'html',
        success: function (data) {
            $("#sensors .gauges .content").html(data);
        }
    });
}

function updateNotifies() {
    var URL = "/ajax/notifies/latest/4";
    $.ajax({ 
        type: 'GET', 
        url: URL, 
        dataType: 'html',
        success: function (data) {
            $("#notifies .content").html(data);
        }
    });
}

function initMap() {
    google.maps.visualRefresh = true;

    function initialize() {
      var mapOptions = {
        zoom: 8,
        center: new google.maps.LatLng(0,0),
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        streetViewControl: false,
        draggable: false,
      };
      map = new google.maps.Map(document.getElementById('map'),
          mapOptions);
      
      var image = '/libs/img/drone-icon.png';
      var myLatLng = new google.maps.LatLng(0,0);
      droneIcon = new google.maps.Marker({
          position: myLatLng,
          map: map,
          icon: image
      });  
    }
    google.maps.event.addDomListener(window, 'load', initialize);

}

$(document).ready(function(){

    // init map
    /*
    initMap();
    setInterval(function(){updateSensors();}, CHECKTIME);
    setInterval(function(){updateNotifies();}, CHECKTIME);
    */
})
