package br.com.deyvidfernandes.customer;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

public class KafkaStreamApplicationSimple {

//	public static void main(final String[] args) throws Exception {
//		Properties props = new Properties();
//		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "new-user-streams-teste");
//		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//
//		StreamsBuilder builder = new StreamsBuilder();
//		KStream<String, String> textLines = builder.stream("TextLinesTopic");
//		KTable<String, Long> wordCounts = textLines
//				.flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
//				.groupBy((key, word) -> word)
//				.count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"));
//		wordCounts.toStream().to("WordsWithCountsTopic", Produced.with(Serdes.String(), Serdes.Long()));
//
//		KafkaStreams streams = new KafkaStreams(builder.build(), props);
//		streams.start();
//	}


	public static void main(String[] args) {

		Properties properties = new Properties();
		properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "new-user-streams-teste");
		properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

		StreamsBuilder streamsBuilder = new StreamsBuilder();
		KStream<String, String> kStream = streamsBuilder.stream("STREAMS.CUSTOMER.TEST1");
		kStream.foreach((k, v) -> System.out.println("Key="+k+" V="+v));

		Topology topology = streamsBuilder.build();
		KafkaStreams streams = new KafkaStreams(topology, properties);
		System.out.println("Starting stream.");
		streams.start();

		Runtime.getRuntime().addShutdownHook(new Thread(()->{
			System.out.println("Shutting dowm stream.");
			streams.close();
		}));
	}
}
