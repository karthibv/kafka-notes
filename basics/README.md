# Kafaka Basics

1. Role of Zookeeper      
    zookeeper maintains the list of brokers, brokers info is stored in zookeeper under /brokers/ids    
2. Ensemble   
    A Zookeeper cluster is called an ensemble.    
    contain an odd number of servers (e.g., 3, 5, etc.)   
    three-node ensemble, you can run with one node missing     
    five-node ensemble, you can run with two nodes missing.    
3. Ensemble configurations      
Example:    
tickTime=2000    
dataDir=/var/lib/zookeeper    
clientPort=2181     
initLimit=20    
syncLimit=5     
server.1=zoo1.example.com:2888:3888    
server.2=zoo2.example.com:2888:3888    
server.3=zoo3.example.com:2888:3888    
initLimit   is the amount of time to allow followers to connect with a leader.     
SyncLimit   The syncLimit value limits how out-of-sync followers can be with the leader     
clientPort  2181     
The servers are specified in the format server.X=hostname:peerPort:leaderPort, with    
the following parameters:     
    X           The ID number of the server. This must be an integer, but it does not need to be zero-based or sequential.   
    hostname    The hostname or IP address of the server.     
    peerPort    The TCP port over which servers in the ensemble communicate with each other. (default port: 2888)     
    leaderPort  The TCP port over which leader election is performed. (default port: 3888)      



# Basic commands

### 1. Console producer   

./kafka-console-producer --broker-list localhost:9092 --topic testing

### 2. Console consumer

./kafka-console-consumer --bootstrap-server localhost:9092 --from-beginning --topic testing


### 3. Console producer with key seperator

./kafka-console-producer --broker-list localhost:9092 --topic testing --property parse.key=true --property key.separator=,


### 4. Console consumer with key seperator

/kafka-console-consumer --bootstrap-server localhost:9092 --from-beginning --topic testing --property print.key=true


### 5. Kafka topic creation 

zookeeper  option   
./kafka-topics --zookeeper localhost:2181 --create --topic topic_1 --partitions 2 --replication-factor 1

broker  option   
./kafka-topics --bootstrap-server localhost:9092 --create --topic topic11 --partitions 1 --replication-factor 1

### 6. List topics

./kafka-topics --bootstrap-server localhost:9092 --list   

### 7. Consumer group - list offset 

List the topics to which the group is subscribed    
kafka-consumer-groups --bootstrap-server <kafkahost:port> --group <group_id> --describe    

### 8. Consumer group - reset offset 

Reset the consumer offset for a topic (preview)    
kafka-consumer-groups --bootstrap-server <kafkahost:port> --group <group_id> --topic <topic_name> --reset-offsets --to-earliest    

Reset the consumer offset for a topic (execute)    
kafka-consumer-groups --bootstrap-server <kafkahost:port> --group <group_id> --topic <topic_name> --reset-offsets --to-earliest --execute     

There are many other resetting options, run kafka-consumer-groups for details      
--shift-by <positive_or_negative_integer>     
--to-current     
--to-latest     
--to-offset <offset_integer>     
--to-datetime <datetime_string>     
--by-duration <duration_string>     
The command also provides an option to reset offsets for all topics the consumer group subscribes to: --all-topics     

