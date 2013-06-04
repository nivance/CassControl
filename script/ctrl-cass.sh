#!/bin/sh

if [ -n "$JAVA_HOME" ]; then
	JAVA="$JAVA_HOME/bin/java"
else
   JAVA=java
fi

if [ -z "$CASS_HOME" ]; then
    echo "You must set the CASS_HOME" >&2
    exit 1
fi

execute(){
	cat node.properties | (while read line; 
			do 
			 HOST=`echo $line | cut -d ' ' -f 1`;
			 PORT=`echo $line | cut -d ' ' -f 2`;
			 USER=`echo $line | cut -d ' ' -f 3`;
			 PASSWORD=`echo $line | cut -d ' ' -f 4`;
			 $JAVA -jar CassControl.jar $HOST $PORT $USER $PASSWORD $CASS_HOME/bin/$1
			 #echo $HOST $PORT $USER $PASSWORD;
		      done)
}


case "$1" in
	'start-all')
		execute 'cassandra;exit;'
		;;
	'stop-all')
		execute 'stop-server;exit;'
		;;
	*)	

	echo "Usage: $0 { start-all | stop-all }"
	exit 1
esac

exit 0
