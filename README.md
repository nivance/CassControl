CassControl
===========

It' a tool for controlling Cassandra Cluster to start or stop.
CassControl��һ������Cassandra��Ⱥ�ڵ������������ֹͣ�Ĺ��ߡ�ʹ��JSCHʵ�֡�
��ʵ�ֵĲ����ǻ���Cassandra1.2.0��

Getting Started
---------------

###1������CASS_HOME��
		JAVA_HOME=/home/jdk1.6.0_30
		JRE_HOME=/home/jdk1.6.0_30/jre
		CASS_HOME=/home/cass/apache-cassandra-1.2.0
		PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin:$CASS_HOME/bin
		PATH=$PATH:$HOME/bin
		export JAVA_HOME JRE_HOME CASS_HOME PATH CLASSPATH

###2��stop-server
��$CASS_HOME/bin/stop-server�ļ�������е�ע��ȥ��������ֱ��ִ������ű�ֹͣCassandra.
		user=`whoami`
		pgrep -u $user -f cassandra | xargs kill -9

###3��CassControl.jar(jar�����ϴ���github)
������Դ�������eclipse(����������)export��Runable jar file(�����õ�jarҲ�����jar)���ļ���ΪCassControl.jar(��Ϊ�ű���д���ˣ���Ҳ���ԸĽű���O(��_��)O~)��

###4���ű�˵��
script/ctrl-cass.sh������ֹͣ�������Ľű�(���ڴ��޸�CassControl.jar��·��)��
script/node.properties����Cassandra��Ⱥ��������Ϣ��ÿ������Ϊ��host port user password�����ո�ָ���

###5���ϴ��ű���������
��script/ctrl-cass.sh��script/node.properties��CassControl.jar���ϴ�$CASS_HOME/bin�¡�

###6��ִ��˵��
ִ��ctrl-cass.sh�ű�Ҫ��1������������Ϊstart-all����stop-all��
```		./ctrl-cass.sh start-all
		./ctrl-cass.sh stop-all


