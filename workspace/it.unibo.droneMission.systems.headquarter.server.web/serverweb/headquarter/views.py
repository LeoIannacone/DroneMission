# Create your views here.
from django.http import HttpResponse
from django.shortcuts import render_to_response

from headquarter.models import server, storage
from it.unibo.droneMission.messages import Utils
from it.unibo.droneMission.interfaces.messages import TypesSensor, TypesNotify
from datetime import datetime

def get_time(java_time):
    return datetime.fromtimestamp(java_time / 1000.0) 

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
    # time / 1000.0:
    #     java takes in account milliseconds, python uses
    #     float for them
    result["time"] = get_time(sensors.getTime())
    
    return result

def format_notifies(notifies):
    formatted_notifies = []
#     if not isinstance(notifies, list):
#         notifies = [notifies]
    
    for notify in notifies:
        n = {}
        n['name'] = ''
        if notify.getType() == TypesNotify.START_MISSION: 
            n['name'] = 'Start'
            n['class'] = 'start'
        elif notify.getType() == TypesNotify.END_MISSION: 
            n['name'] = 'End'
            n['class'] = 'end'
        n['value'] = notify.getValue()
        n['time'] = get_time(notify.getTime())
        formatted_notifies.append(n)
    return formatted_notifies 

def index(request):
       
    return render_to_response('index.html')

def latest_sensors(request):
    
    sensors = storage.getLatestSensorsData()
    f_s = format_sensors(sensors)
    
    return render_to_response('ajax/sensors_latest.html', f_s)

def get_mission(request, id):
    mission = storage.getMission(int(id))
    f_s = format_sensors(mission.getSensorsDatas()[0])
    return render_to_response('mission.html',f_s)
