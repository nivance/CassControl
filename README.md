CassControl
===========

It' a tool for controlling Cassandra Cluster to start or stop.
CassControl是一个控制Cassandra集群节点服务器启动和停止的工具。使用JSCH实现。
此实现的测试是基于Cassandra1.2.0。

Getting Started
---------------

###1、配置CASS_HOME。
		JAVA_HOME=/home/jdk1.6.0_30
		JRE_HOME=/home/jdk1.6.0_30/jre
		CASS_HOME=/home/cass/apache-cassandra-1.2.0
		PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin:$CASS_HOME/bin
		PATH=$PATH:$HOME/bin
		export JAVA_HOME JRE_HOME CASS_HOME PATH CLASSPATH

###2、stop-server
将$CASS_HOME/bin/stop-server文件最后两行的注释去掉，可以直接执行这个脚本停止Cassandra.
		user=`whoami`
		pgrep -u $user -f cassandra | xargs kill -9

###3、CassControl.jar(jar不能上传到github)
下载完源代码后，用eclipse(或其他工具)export成Runable jar file(将引用的jar也打到这个jar)，文件名为CassControl.jar(因为脚本里写死了，你也可以改脚本，O(∩_∩)O~)。

###4、脚本说明
script/ctrl-cass.sh启动和停止服务器的脚本(可在此修改CassControl.jar包路径)。
script/node.properties配置Cassandra集群服务器信息，每行数据为“host port user password”，空格分隔。

###5、上传脚本到服务器
将script/ctrl-cass.sh、script/node.properties和CassControl.jar和上传$CASS_HOME/bin下。

###6、执行说明
执行ctrl-cass.sh脚本要带1个参数，可以为start-all或者stop-all。
```		./ctrl-cass.sh start-all
		./ctrl-cass.sh stop-all


