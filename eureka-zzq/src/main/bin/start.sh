#!/bin/bash
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf
LOGS_DIR=$DEPLOY_DIR/logs
SERVER_NAME=`grep name $CONF_DIR/application.yml |awk -F ':' '{print $2}'| sed -n 1p`
SERVER_PORT=`grep port $CONF_DIR/application.yml |awk -F ':' '{print $2}' | sed -n 1p`

if [ -z "$SERVER_NAME" ]; then
    SERVER_NAME=`hostname`
fi

if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi

PIDS=`ps -f | grep java |grep "$CONF_DIR" |awk '{print $2}'`
if [ -n "$PIDS" ]; then
    echo "ERROR: $SERVER_NAME already started!"
    echo "PID: $PIDS"
    exit 1
fi

if [ -n "SERVER_PORT" ]; then
    SERVER_PORT_COUNT=`netstat -antlp |grep $SERVER_PORT | wc -l`
    if [ $SERVER_PORT_COUNT -gt 0 ]; then
        echo "ERROR: $SERVER_NAME port $SERVER_PORT already used!"
        exit 1
    fi
fi

STDOUT_FILE=$LOGS_DIR/eureka.log
LIB_DIR=$DEPLOY_DIR/lib
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

JAVA_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "
JAVA_DEBUG_OPTS=""
if [ "$1" = "debug" ]; then
    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "
fi

JAVA_MEN_OPTS=""

COMMON_OPTS=" -Dfile.encoding=UTF-8"

BITS=`java -version 2>&1 | grep -i 64-bit`
if [ -n "$BITS" ]; then
    JAVA_MEM_OPTS=" -server -Xmx256m -Xms256m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "
else
    JAVA_MEM_OPTS=" -server -Xms1g -Xmx1g -XX:SurvivorRatio=2 -XX:+UseParallelGC "
fi

echo -e "Starting the Server [ $SERVER_NAME ]...\c"
nohup java $COMMON_OPTS $JAVA_DEBUG_OPTS -classpath $CONF_DIR:$LIB_JARS com.scnu.zzq.eureka.Main > $STDOUT_FILE 2>&1 &

COUNT=0
while [ $COUNT -lt 1 ]; do
  echo -e ".\c"
  sleep 1
  COUNT=`ps -f |grep java |grep "$DEPLOY_DIR" | awk '{print $2}' | wc -l`
  if [ $COUNT -gt 0 ]; then
      break
  fi
done


echo "$SERVER_NAME start success!"

PIDS=`ps -f | grep java |grep "$DEPLOY_DIR" |awk '{print $2}'`
echo "PID: $PIDS"
echo "STDOUT: $S:TDOUT_FILE"
