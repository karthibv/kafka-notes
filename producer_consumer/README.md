# Kafka Producer and consumer

## Kafka Producer
1. Kafka Sample Java Producer      
    [ProducerDemo.java](kafkacourse/kafka-basics/src/main/java/kafka.tutorial1/ProducerDemo.java)   
    1. Producer Properties  - setup bootstrap servers, key and serializer configuration
    2. Create Kafka Producer instance with Producer properties  
    3. Create Kafka producer record with topic name and records (only value in kafka record)
    4. Send records with kafka producer  
    5. Flush and Close the producer   

2. Kafka Sample Java Producer - Message Key and Sync call     
    [ProducerDemo.java](kafkacourse/kafka-basics/src/main/java/kafka.tutorial1/ProducerDemoKeys.java)   
    1. Producer Properties  - setup bootstrap servers, key and serializer configuration
    2. Create Kafka Producer instance with Producer properties  
    3. Create Kafka producer record with topic name and records (key and value)  
    4. Send records (key and value ) synchronously with Kafka Producer
    5. Flush and Close the producer   


3. Kafka Sample Java Producer with aync callback    
    [ProducerDemoWithCallback.java](kafkacourse/kafka-basics/src/main/java/kafka.tutorial1/ProducerDemoWithCallback.java)     
    1. Producer Properties  - setup bootstrap servers, key and serializer configuration    
    2. Create Kafka Producer instance with Producer properties     
    3. Create Kafka producer record with topic name and records (only value in kafka record)   
    4. Send records asynchronously with Kafka Producer    
    5. Flush and Close the producer    
    

## Kafka consumer    
1. Kafka Sample Java Consumer        
    [ProducerDemo.java](kafkacourse/kafka-basics/src/main/java/kafka.tutorial1/ConsumerDemo.java)     
    1. Consumer Properties  - setup bootstrap servers,  key and serializer configuration,group id, auto offset reset      
    2. Create Kafka consumer instance with Producer properties     
    3. Subscribe Kafka Consumer to the topics    
    4. Pool for new messages with Kafka Consumer    
