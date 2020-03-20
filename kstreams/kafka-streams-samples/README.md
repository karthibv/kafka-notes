# Sample Kafka Stream    


### 1. Streaming app with input and output topic     
Anatomy of kafka stream apps          

1. Create an input Topic called lines-topic        
2. Create an input Topic called lines-lower-topic        
3. start streaming app       
        [streams.MapSample.java](src/main/java/streams/MapSample.java)        
4. Publish messsage to the Topic       
./kafka-console-producer --broker-list localhost:9092 --property "parse.key=true" --property "key.seperator=:" --topic lines-topic      
    1:"Kafka powers the Confluent Streaming Platform"
    2:"Events are stored in Kafka"
    3:"Confluent contributes to Kafka"

5. consume message from the output topic    
/kafka-console-consumer --bootstrap-server localhost:9092 --from-beginning --topic lines-lower-topic    




### 2. Streaming app with JSON messages and Kafka Stream DSL    
Kafka Streams application that uses custom serializer and deserializer to work with data that is JSON formatted.       

1. Create an input Topic called temperatures-topic        
2. Create an input Topic called high-temperatures-topic        
3. start streaming app       
        [streams.MapSample.java](src/main/java/streams/JsonSample.java)        
4. Publish messsage to the Topic       
./kafka-console-producer  --broker-list localhost:9092  --property "parse.key=true"  --property "key.separator=:"  --topic temperatures-topic
     "S1":{"station":"S1", "temperature": 10.2, "timestamp": 1}
     "S1":{"station":"S1", "temperature": 11.2, "timestamp": 2}
     "S1":{"station":"S1", "temperature": 11.1, "timestamp": 3}
     "S1":{"station":"S1", "temperature": 12.5, "timestamp": 4}
     "S2":{"station":"S2", "temperature": 15.2, "timestamp": 1}
     "S2":{"station":"S2", "temperature": 21.7, "timestamp": 2}
     "S2":{"station":"S2", "temperature": 25.1, "timestamp": 3}
     "S2":{"station":"S2", "temperature": 27.8, "timestamp": 4}


5. consume message from the output topic    
./kafka-console-consumer --bootstrap-server localhost:9092 --key-deserializer=org.apache.kafka.common.serialization.StringDeserializer --from-beginning --topic high-temperatures-topic
