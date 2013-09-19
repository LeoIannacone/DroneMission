# Create your views here.
from django.http import HttpResponse
from django.shortcuts import render_to_response

from headquarter.models import storage
from it.unibo.droneMission.prototypes.messages import Utils

def format_gauges(gauges):
    formatted_gauges = []
    for gauge in gauges:
        g = {}
        type = Utils.getGaugeType(gauge)
        g['name'] = Utils.getGaugeName(type)
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
                

# def latest_command(request):
#     c = Commands.objects.latest('id')
#     cmd = Command(c.type, c.value)
#      
#     return HttpResponse(cmd.toJSON())
#  
# def latest_sensors(request):
#     s = Sensors.objects.latest('time')
#     return HttpResponse(s.data)
#  
# def latest_notify(request):
#     n = Notifies.objects.latest('id')
#     ntf = Notify(n.type, n.value)
#     return HttpResponse(ntf.toJSON())
#  
# def latest_picturepack(request):
#     # get last picture
#     p = Pictures.objects.latest('id')
#     f = File()
#     f.setCreationTime(p.time_creation)
#     f.setData(p.data_base64)
#     f.setName(p.name)
#      
#     # get sensors info
#     s = p.sensors.data
#     sns = Factory.createSensorsData(s)
#      
#     pic = PicturePackage(sns, f)
#     return HttpResponse(pic.toJSON())