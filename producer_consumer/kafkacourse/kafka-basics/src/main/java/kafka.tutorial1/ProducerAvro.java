package kafka.tutorial1;

import clients.avro.PositionValue;
import com.sun.tools.javac.util.List;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import io.confluent.monitoring.clients.interceptor.MonitoringProducerInterceptor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerAvro {

    static final String KAFKA_TOPIC = "driver-positions-avro";


    public static void main(String[] args) {

        System.out.println("Starting Java Avro producer.");

        // Load a driver id from an environment variable
        // if it isn't present use "driver-1"
        String driverId  = System.getenv("DRIVER_ID");
        driverId = (driverId != null) ? driverId : "driver-1";

        final Properties settings = new Properties();
        settings.put(ProducerConfig.CLIENT_ID_CONFIG, driverId);
        settings.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        settings.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        settings.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        settings.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        settings.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,
                List.of(MonitoringProducerInterceptor.class));


        final KafkaProducer<String, PositionValue> producer = new KafkaProducer<>(settings);

        // Adding a shutdown hook to clean up when the application exits
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Closing producer.");
            producer.close();
        }));

        while (true) {
            final String key = driverId;
            final Double latitude1 = 45.00;
            final Double longitude1 = 49.00;
            final PositionValue value = new PositionValue(latitude1, longitude1);
            final ProducerRecord<String, PositionValue> record = new ProducerRecord<>(
                    KAFKA_TOPIC, key, value);
            producer.send(record, (md, e) -> {
                System.out.println(String.format("Sent Key:%s Latitude:%s Longitude:%s",
                        key, value.getLatitude(), value.getLongitude()));
            });
        }

         /*
    Confirm the topic is being written to with kafka-avro-console-consumer

    kafka-avro-console-consumer --bootstrap-server localhost:9092 \
    --property schema.registry.url=http://localhost:8081 \
    --topic driver-positions-avro --property print.key=true \
    --key-deserializer=org.apache.kafka.common.serialization.StringDeserializer \
    --from-beginning
    curl localhost:8081/subjects/driver-positions-avro-value/versions/1
    */


    }
}
