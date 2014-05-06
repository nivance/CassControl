#!/bin/bash
if [ $# -eq 1 ]; then
   declare nexushost="http://"$1"/nexus/content/repositories/joyobr"
   declare ulwp2app="/usr/local/glassfish4/glassfish/ulwp2app"
   echo "nexushost is" $nexushost
   echo "ulwp2app_home is" $ulwp2app
   declare file="ulwp2.conf"
   while read -r line; do
     [[ "$line" =~ ^#.*$ ]] && continue
     module=`echo $line | cut -d ' ' -f 1`;
     version=`echo $line | cut -d ' ' -f 2`;
     startlevel=`echo $line | cut -d ' ' -f 3`
     bundlename=`echo $module | cut -d '/' -f 2`
     wget $nexushost/com/joyveb/$module/$version/$bundlename-$version.jar
     mv $bundlename-$version.jar $ulwp2app/$startlevel/
     done < "$file"
else
    echo "useage: ./getulwp2bundle nexushost"
fi