# Create your views here.
from django.http import HttpResponse
from django.shortcuts import render_to_response

from headquarter.models import storage
from it.unibo.droneMission.prototypes.messages import Utils
from it.unibo.droneMission.interfaces.messages import TypesSensor

def format_gauges(gauges):
    formatted_gauges = []
    for gauge in gauges:
        g = {}
        type = Utils.getGaugeType(gauge)
        g['name'] = Utils.getGaugeName(type)
        if type == TypesSensor.LOCTRACKER:
            g['value'] = gauge.toString()
        else:
            g['value'] = gauge.getVal().valAsString()
        formatted_gauges.append(g)
    return formatted_gauges

def index(request):
    
    c = storage.getCommandToSend()
    
    if c is None:
        print("NO COMMAND")
    #print("AA %s" % c.getType())
    values = {}
    values['latest_command'] = c
   
    return render_to_response('index.html', values)

def latest_sensors(request):
    
    sensors = storage.getLatestSensorsData()
    gauges = format_gauges(sensors.getGauges())
    
    return render_to_response('ajax/sensors_latest.html', {'gauges': gauges})
