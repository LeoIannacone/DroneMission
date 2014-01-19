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

function updatePicture() {
    var URL = "/ajax/pictures/latest";
    $.ajax({ 
        type: 'GET', 
        url: URL, 
        dataType: 'html',
        success: function (data) {
            current = $("#pictures .content img").attr("src");
            new_image = $($(data)[2]).attr("src");
            if (new_image != current)
                $("#pictures .content").html(data);
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
        $("#commands *").unbind("click");
        window.clearInterval(UPDATER);
    }
  
}

function updateSpeed(speed) {
   $("#current-speed").html(speed);
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

function updateReply(data) {
    $("#reply").html(data);
}

$(document).ready(function(){

    if (document.location.pathname == '/missions/new') {
        initMap();
        UPDATER = setInterval(
            function(){
                updateSensors();
                updatePicture();
            }, 
            CHECKTIME
            );    
        $("#start").click( function() {
            var URL = "/ajax/commands/send/type/1/value/0";
            $.ajax({ 
                type: 'GET', 
                url: URL, 
                dataType: 'html',
                success: function (data) {
                    $("#commands *").removeClass("disabled");
                    $("#start").addClass("disabled");
                                       
                    $("#decrease").click( function() {
                        var URL = "/ajax/commands/send/type/5/value/0";
                        $.ajax({ 
                            type: 'GET', 
                            url: URL,
                            dataType: 'html',
                            success: function (data) {
                                updateReply(data);
                            }
                        });
                    });
                    
                    $("#increase").click( function() {
                        var URL = "/ajax/commands/send/type/4/value/0";
                        $.ajax({ 
                            type: 'GET', 
                            url: URL, 
                            dataType: 'html',
                            success: function (data) {
                                updateReply(data);
                            }
                        });
                    });
                    
                    $("#send").click( function() {
                        var val = $("#controls .set").val()
                        var URL = "/ajax/commands/send/type/3/value/" + val;
                        $.ajax({ 
                            type: 'GET', 
                            url: URL, 
                            dataType: 'html',
                            success: function (data) {
                                updateReply(data);
                            }
                        });
                    });
                    updateReply(data);
                    $("#start").unbind( "click" );
                }
            });
        });
    }
})
