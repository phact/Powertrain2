Kafka Setup - Local Machine
==============================

Start Kafka, create the topics and test:

bin/zookeeper-server-start.sh config/zookeeper.properties

bin/kafka-server-start.sh config/server.properties

bin/kafka-create-topic.sh --zookeeper localhost:2181 --replica 1 --partition 1 --topic vehicle_events

bin/kafka-list-topic.sh --zookeeper localhost:2181

bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic vehicle_events --from-beginning

Kafka Server Setup - Remote Server Setup
===================================

Everything is run in the kafka root directory

nohup bin/zookeeper-server-start.sh config/zookeeper.properties  2>&1 1> zookeeper.log &

nohup bin/kafka-server-start.sh config/server.properties 2>&1 1> kafka.log &

The following are recommended configuration changes.

Setup the JMX properties so you can monitor Kafka with JConsole.  Change rmi.server.hostname to your IP address

export KAFKA_JMX_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false  -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=54.153.61.18 -Dcom.sun.management.jmxremote.port=5052"

If you are using JMX then every kafka command will require an open JMX Port so you will have to run this with a different port after every command.
export JMX_PORT=5050

For load testing kafka can quickly run out of disk space.  Modify the server config to delete messages in config/server.properties

log.retention.minutes=30
log.cleanup.policy=delete
log.cleaner.enable=true


#On your local machine / laptop run jconsole to monitor kafka.  Enter the IP and Port from above
jconsole &


#kafka 0.8.2
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 5 --topic vehicle_events

bin/kafka-topics.sh --list --zookeeper localhost:2181

bin/kafka-topics.sh --describe --topic vehicle_events --zookeeper localhost:2181


bin/kafka-topics.sh --create --zookeeper 192.168.99.100:2181 --replication-factor 1 --partitions 5 --topic test

bin/kafka-topics.sh --list --zookeeper localhost:2181

bin/kafka-topics.sh --describe --topic vehicle_events --zookeeper localhost:2181

bin/kafka-console-consumer.sh --zookeeper 172.31.14.225:2181 --topic vehicle_events --from-beginning


bin/kafka-topics.sh --zookeeper localhost:2181 --delete --topic vehicle_events
