package br.com.deyvidfernandes.customer;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.GenericAvroSerde;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class KafkaStreamApplicationGenericAvroSerde {

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
		properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "new-user-streams-teste2");
		properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, GenericAvroSerde.class);

		// When you want to override serdes explicitly/selectively
		final Map<String, String> serdeConfig = Collections.singletonMap(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

//		// 'Customer' is a Java classes generated from Avro schemas
//		final Serde<String> valueSpecificAvroSerde = new SpecificAvroSerde();
//		valueSpecificAvroSerde.configure(serdeConfig, false); // `true` for record keys

		final Serde<GenericRecord> valueGenericAvroSerde = new GenericAvroSerde();
		valueGenericAvroSerde.configure(serdeConfig, false); // `false` for record values


		StreamsBuilder streamsBuilder = new StreamsBuilder();

		KStream<String, GenericRecord> kStream = streamsBuilder.stream("STREAMS.CUSTOMER2", Consumed.with(Serdes.String(), valueGenericAvroSerde));
		kStream.foreach((k, v) -> { System.out.println("CUSTOMER2 - Key="+k+" V="+v); });

		KStream<String, GenericRecord> kStream2 = streamsBuilder.stream("STREAMS.EMAIL2", Consumed.with(Serdes.String(), valueGenericAvroSerde));
		kStream2.foreach((k, v) -> { System.out.println("EMAIL2 - Key="+k+" V="+v); });

		KStream<String, GenericRecord> kStream3 = streamsBuilder.stream("STREAMS.CUSTOMER.EMAIL", Consumed.with(Serdes.String(), valueGenericAvroSerde));
		kStream3.foreach((k, v) -> { System.out.println("CUSTOMER.EMAIL - Key="+k+" V="+v); });

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
