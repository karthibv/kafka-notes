# Basic commands

### 1. Console producer   

./kafka-console-producer --broker-list localhost:9092 --topic testing

### 2. Console consumer

./kafka-console-consumer --bootstrap-server localhost:9092 --from-beginning --topic testing


### 3. Console producer with key seperator

./kafka-console-producer --broker-list localhost:9092 --topic testing --property parse.key=true --property key.separator=,


### 4. Console consumer with key seperator

/kafka-console-consumer --bootstrap-server localhost:9092 --from-beginning --topic testing --property print.key=true