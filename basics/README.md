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


