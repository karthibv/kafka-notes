package streams;

import io.confluent.kafka.serializers.KafkaJsonDeserializer;
import io.confluent.kafka.serializers.KafkaJsonSerializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class JsonSample {
    final static String APPLICATION_ID = "json-sample-v0.1.0";
    final static String APPLICATION_NAME = "JSON Sample";



    public static void main(String[] args) {
        System.out.printf("### Starting %s Application ###%n", APPLICATION_NAME);

        //configurations
        Properties settings = getConfig();

        //streams topology
        Topology topology = getTopology();

        //start app
        KafkaStreams streams = startApp(settings,topology);

        //shutdown hook
        setupShutdownHook(streams);


    }




    private static Properties getConfig(){
        Properties settings = new Properties();
        settings.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_ID);
        settings.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        return settings;
    }


    private static Serde<TempReading> getJsonSerde(){
        Map<String, Object> serdeProps = new HashMap<>();
        serdeProps.put("json.value.type", TempReading.class);

        final Serializer<TempReading> tempReadingSerializer = new KafkaJsonSerializer<>();
        tempReadingSerializer.configure(serdeProps,false);

        final Deserializer<TempReading> tempReadingDeserializer = new KafkaJsonDeserializer<>();
        tempReadingDeserializer.configure(serdeProps, false);

        return Serdes.serdeFrom(tempReadingSerializer,tempReadingDeserializer);
    }


    private static Topology getTopology(){
        StreamsBuilder builder = new StreamsBuilder();

        final Serde<String> stringSerde = Serdes.String();
        final Serde<TempReading> temperatureSerde = getJsonSerde();

        builder.stream("temperatures-topic", Consumed.with(stringSerde,temperatureSerde))
                .filter((key,value) -> value.temperature > 25)
                .to("high-temperatures-topic", Produced.with(stringSerde,temperatureSerde));

        return builder.build();

    }

    private static KafkaStreams startApp(Properties config, Topology topology){
        KafkaStreams streams = new KafkaStreams(topology, config);
        streams.start();
        return streams;
    }

    private static void setupShutdownHook(KafkaStreams streams){
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.printf("### Stopping %s Application ###%n", APPLICATION_NAME);
            streams.close();
        }));
    }


}
