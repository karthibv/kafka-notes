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

