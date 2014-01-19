# Create your views here.
from django.shortcuts import render_to_response

from headquarter.models import server, storage
from it.unibo.droneMission.messages import Utils, Command, Reply
from it.unibo.droneMission.interfaces.messages import TypesSensor, TypesNotify,\
    TypesReply, TypesCommand
import time

# time / 1000.0:
#     java takes in account milliseconds, python uses
#     float for them
def get_time(java_time):
    date = java_time / 1000.0
    #return time.strftime("%a, %d %b %Y %H:%M:%S", time.gmtime(date))
    return time.strftime("%d %b %Y %H:%M:%S", time.gmtime(date))    

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
        if type == TypesSensor.FUELOMETER:
            fuel = gauge.getVal().valAsDouble()
        if type == TypesSensor.SPEEDOMETER:
            speed = gauge.getVal().valAsDouble()
        formatted_gauges.append(g)
    
    result = {}
    result["gauges"] = formatted_gauges
    result["latitude"] = lat
    result["longitude"] = lon
    result["fuel"] = fuel
    result["speed"] = speed
    result["time"] = get_time(sensors.getTime())
    
    return result

def format_picture(picture, mission_id):
    formatted_picture = {}
    formatted_picture["sensors"] = format_sensors(picture.getSensorsData())
    #formatted_picture["image"] = picture.getFile().getDataAsBase64()
    formatted_picture["image_url"] = "/pictures/%s/%s" % (mission_id, picture.getFile().getName())
    formatted_picture["name"] = picture.getFile().getName()
    return formatted_picture

def format_reply(reply):
    formatted = {}
    formatted["name"] = Utils.getReplyName(reply)
    formatted["type"] = reply.getType()
    formatted["value"] = reply.getValue()
    
    if reply.getType() == TypesReply.REPLY_OK:
        formatted["class"] = "alert-success"
    if reply.getType() == TypesReply.REPLY_NO:
        formatted["class"] = "alert-alert"
    if reply.getType() == TypesReply.REPLY_FAIL:
        formatted["class"] = "alert-warning"
    
    return formatted

def format_command(command, reply):
    formatted = {}
    
    formatted["command"] = {}
    formatted["command"]["name"] = Utils.getCommandName(command)
    formatted["command"]["type"] = command.getType()
    formatted["command"]["value"] = command.getValue()
    
    formatted["reply"] = format_reply(reply)
    
    formatted["time"] = get_time(command.getTime())
    
    return formatted

def latest_picture(request):
    picture = storage.getLatestPicturePackage()
    f_p = {}
    if picture is not None:
        f_p = format_picture(picture, storage.getCurrentMissionID())
    return render_to_response('ajax/picture_latest.html', f_p)

def latest_sensors(request):
    sensors = storage.getLatestSensorsData()
    f_s = {}
    if sensors is not None:
        f_s = format_sensors(sensors)
    return render_to_response('ajax/sensors_latest.html', f_s)

def index(request):
    missions = storage.getPastMissions()
    info = {}
    info["missions"] = []
    for m in missions:
        formatted = {}
        formatted["start"] = get_time(m.getStartTime())
        formatted["end"] = get_time(m.getEndTime())
        formatted["id"] = m.getId()
        info["missions"].insert(0, formatted)
    return render_to_response('index.html', info)

def get_mission(request, id):
    
    mission = storage.getMission(int(id))
    info = {}
    
    info["id"] = id
    
    info["time"] = {}
    info["time"]["start"] = get_time(mission.getStartTime())
    info["time"]["end"] = get_time(mission.getEndTime())
    
    info["sensors"] = []
    for g in mission.getSensorsDatas():
        info["sensors"].insert(0, format_sensors(g))
    
    info["pictures"] = []
    for p in mission.getPicturePackages():
        info["pictures"].insert(0, format_picture(p, id))

    info["commands"] = []
    commands = mission.getCommands()
    for c in commands:
        info["commands"].insert(0, format_command(c, commands.get(c)))
    
    return render_to_response('mission.html',info)

def new_mission(request):
    info = {}
    
    info["commands"] = {}
    
    info["commands"]["start"] = {}
    info["commands"]["start"]["type"] = TypesCommand.START_MISSION
    info["commands"]["start"]["value"] = 0
    
    info["commands"]["speed_set"] = {}
    info["commands"]["speed_set"]["type"] = TypesCommand.SPEED_SET
    info["commands"]["speed_set"]["value"] = 0
    
    info["commands"]["speed_increase"] = {}
    info["commands"]["speed_increase"]["type"] = TypesCommand.SPEED_INCREASE
    info["commands"]["speed_increase"]["value"] = 0
    
    info["commands"]["speed_decrease"] = {}
    info["commands"]["speed_decrease"]["type"] = TypesCommand.SPEED_DECREASE
    info["commands"]["speed_decrease"]["value"] = 0
    
    storage.resetCurrentMissionID()
    
    return render_to_response('new-mission.html', info)

def send_command(request, type, value):
    
    c = Command(int(type))
    c.setValue(int(value))
    
    reply = server.forwardCommand(c)
    
    info = format_reply(reply)
    
    return render_to_response('ajax/send-command.html', info)
