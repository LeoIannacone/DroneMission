#!/bin/bash


export CLASSPATH="$CLASSPATH\
:`pwd`/../../libs/jars/mysql-connector-java-5.1.16.jar\
:`pwd`/../../libs/jars/gson-2.2.4.jar\
:`pwd`/../../libs/jars/it.unibo.interfaces_1.6.12.jar\
:`pwd`/../../libs/jars/it.unibo.tuprolog_1.0.1.jar\
:`pwd`/../../libs/it.unibo.dronemission.headquarter.server.jar"

export JYTHONPATH="$JYTHONPATH:`pwd`/serverweb"
export JAVA_MEM="-Xmx2000m $JAVA_MEM"
export DJANGO_SETTINGS_MODULE="serverweb.settings"
source venv/bin/activate

django-admin.py runserver
