# Kafka setup in local


### 
1. Download confluent local   
    https://www.confluent.io/download/

2. Extract in to folder    
        confluent-5.3.0   
                    ...lib.   
                    ...etc.    
                    ...src.    
                    ...README.     
                    ...bin.    
                    ...share.    
3. Start confluent  
        confluent-5.3.0/bin  
            ... start by using below command  
                ..1. confluent local start  
            ... check status by using below command    
                ..2. confluent local status  


   The local commands are intended for a single-node development environment   
   only, NOT for production usage. https://docs.confluent.io/current/cli/index.html   

    control-center is [UP]   
    ksql-server is [UP]   
    connect is [UP]   
    kafka-rest is [UP]   
    schema-registry is [UP]   
    kafka is [UP]   
    zookeeper is [UP]   


###
