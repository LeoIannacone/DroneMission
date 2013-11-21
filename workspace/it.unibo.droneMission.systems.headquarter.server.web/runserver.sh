#!/bin/bash


export CLASSPATH="$CLASSPATH\
:`pwd`/../../libs/jars/mysql-connector-java-5.1.16.jar\
:`pwd`/../../libs/jars/gson-2.2.4.jar\
:`pwd`/../it.unibo.tmp.jar"

export JYTHONPATH="$JYTHONPATH:`pwd`/webclient"
export DJANGO_SETTINGS_MODULE="webclient.settings"
source venv/bin/activate

django-admin.py runserver
