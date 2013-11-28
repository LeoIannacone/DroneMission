var CHECKTIME = 300; // check new data every x millisec
var UPDATER;
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

function updateOnMission(fuel) {
    if (parseFloat(fuel) > 0.5) {
        $("#start").html("On Mission");
        $("#start").removeClass("btn-danger");
        $("#start").removeClass("btn-success");
        $("#start").addClass("btn-warning");
    }
    else {
        $("#start").html("Mission ended");
        $("#start").addClass("btn-danger");
        $("#start").removeClass("btn-success");
        $("#start").removeClass("btn-warning");
        $("#commands *").addClass("disabled");
        window.clearInterval(UPDATER);
    }
  
}

function updateSpeed(speed) {
   $("#commands .set").val(parseFloat(speed));
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
        center: new google.maps.LatLng(44.435505,10.976787),
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
    //initMap();
    
    
    
    if (document.location.pathname == '/missions/new') {
        initMap();
        UPDATER = setInterval(function(){updateSensors();}, CHECKTIME);    
        $("#start").click( function() {
            var URL = "/ajax/commands/send/type/3/value/60";
            $.ajax({ 
                type: 'GET', 
                url: URL, 
                dataType: 'html',
                success: function (data) {
                    $("#commands *").removeClass("disabled");
                    $("#start").addClass("disabled");
                    $("#start").unbind( "click" );
                }
            });
        });
    }


})
