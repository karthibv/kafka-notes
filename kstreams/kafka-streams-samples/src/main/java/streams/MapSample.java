package streams;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import javax.xml.ws.spi.ServiceDelegate;
import java.util.Properties;

public class MapSample {
    public static void main(String[] args) {
        System.out.println("*** Starting Map Sample Application ***");

        // configuration
        Properties settings = new Properties();
        settings.put(StreamsConfig.APPLICATION_ID_CONFIG, "map-sample-v0.1.0");
        settings.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");

        //kafka stream
        final Serde<String> stringSerde = Serdes.String();
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String,String> lines = builder.stream("lines-topic", Consumed.with(stringSerde,stringSerde));
        KStream<String,String> transformed = lines.mapValues(value -> value.toLowerCase());
        transformed.to("lines-lower-topic", Produced.with(stringSerde,stringSerde));
        Topology topology = builder.build();

        //create and start the stream
        KafkaStreams streams = new KafkaStreams(topology,settings);
        streams.start();


        //shutdown hook

        Runtime.getRuntime().addShutdownHook(new Thread( () -> {
           System.out.println(" ### Stopping Map Sample Application ####");
           streams.close();
        }));
    }
}
