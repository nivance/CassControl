#!/bin/bash
bdidstr=$(oadmin osgi lb |grep '(' |awk -F'|' '{print $1}')
bdids=($bdidstr)
#declare -p bdids
bdsstr=$(oadmin osgi lb |grep '(' |awk -F'|' '{print $2}')
bds=($bdsstr)
#declare -p bds
bdstatus="Active"
bdlen=${#bdids[@]}
for ((i = 0; i < "${bdlen}"; i++));
        do
                if [ "${bds[i]}" != "$bdstatus" ]; then
                        echo ${bdids[i]} "is" ${bds[i]} ", Start it."
                        oadmin osgi start ${bdids[i]}
                else
                        echo ${bdids[i]} "is Active"    
                fi
        done;