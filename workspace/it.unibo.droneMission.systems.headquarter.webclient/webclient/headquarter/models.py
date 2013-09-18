#from django.db import models
from it.unibo.droneMission.systems.headquarter import FactoryStorage, MySQL

storage = FactoryStorage.getInstance("mysql")

# class Commands(models.Model):
#     id = models.IntegerField(primary_key=True)
#     type = models.IntegerField()
#     value = models.IntegerField(null=True, blank=True)
#     status = models.CharField(max_length=30, blank=True)
#     time = models.TextField(blank=True)
#     class Meta:
#         db_table = u'commands'
# 
# class Notifies(models.Model):
#     id = models.IntegerField(primary_key=True)
#     type = models.IntegerField()
#     value = models.CharField(max_length=768, blank=True)
#     time = models.TextField(blank=True)
#     class Meta:
#         db_table = u'notifies'
# 
# class Sensors(models.Model):
#     id = models.IntegerField(primary_key=True)
#     data = models.CharField(max_length=12288, blank=True)
#     time = models.TextField(blank=True)
#     class Meta:
#         db_table = u'sensors'
# 
# class Pictures(models.Model):
#     id = models.IntegerField(primary_key=True)
#     name = models.CharField(max_length=768, blank=True)
#     data_base64 = models.CharField(max_length=12288, blank=True)
#     sensors = models.ForeignKey(Sensors)
#     time_creation = models.TextField(blank=True)
#     class Meta:
#         db_table = u'pictures'
# 
# class Replies(models.Model):
#     id = models.IntegerField(primary_key=True)
#     type = models.IntegerField()
#     value = models.CharField(max_length=768, blank=True)
#     command = models.ForeignKey(Commands)
#     time = models.TextField(blank=True)
#     class Meta:
#         db_table = u'replies'

    
    