# Create your views here.
from django.http import HttpResponse
from django.shortcuts import render_to_response

from headquarter.models import storage
from it.unibo.droneMission.prototypes.messages import Utils
from it.unibo.droneMission.interfaces.messages import TypesSensor

def format_sensors(sensors):
    formatted_gauges = []
    lat = 0
    lon = 0
    for gauge in sensors.getGauges():
        g = {}
        type = Utils.getGaugeType(gauge)
        g['name'] = Utils.getGaugeName(type)
        g['value'] = gauge.getCurValRepDisplayed()
        if type == TypesSensor.LOCTRACKER:
            lat = gauge.getLat().valAsDouble()
            lon = gauge.getLon().valAsDouble()
        formatted_gauges.append(g)
    
    result = {}
    result["gauges"] = formatted_gauges
    result["latitude"] = lat
    result["longitude"] = lon
    result["time"] = sensors.getTime()
    
    return result

def index(request):
       
    return render_to_response('index.html')

def latest_sensors(request):
    
    sensors = storage.getLatestSensorsData()
    f_s = format_sensors(sensors)
    
    return render_to_response('ajax/sensors_latest.html', f_s)
