package br.com.deyvidfernandes.customer;

import br.com.deyvidfernandes.customer.joiner.CustomerEmailJoiner;
import br.com.deyvidfernandes.customer.producer.dto.Customer;
import br.com.deyvidfernandes.customer.producer.dto.Email;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.apache.kafka.streams.kstream.*;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class KStreamApplication {


	public static void main(String[] args) {

		Properties properties = new Properties();
		properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "new-user-streams-teste3");
		properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
		properties.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG, LogAndContinueExceptionHandler.class);

		// When you want to override serdes explicitly/selectively
		final Map<String, String> serdeConfig = Collections.singletonMap(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

		// 'Customer' is a Java classes generated from Avro schemas
		final Serde<Customer> customerSerde = new SpecificAvroSerde();
		customerSerde.configure(serdeConfig, false); // `true` for record keys

		final Serde<Email> emailSerde = new SpecificAvroSerde();
		emailSerde.configure(serdeConfig, false); // `true` for record keys

		StreamsBuilder streamsBuilder = new StreamsBuilder();

		KStream<String, Email> kStreamEmail = streamsBuilder.stream("STREAMS.EMAIL2", Consumed.with(Serdes.String(), emailSerde));
		KStream<String, Customer> kStreamCustomer = streamsBuilder.stream("STREAMS.CUSTOMER2", Consumed.with(Serdes.String(), customerSerde));

//		kStreamCustomer.foreach((k,v)-> System.out.println("Customer= "+v));
//		kStreamEmail.foreach((k,v)-> System.out.println("Email= "+v));


		final KStream<String, Customer> streamOneNewKey = kStreamCustomer.selectKey((k, v) -> v.getId()+"");
		final KStream<String, Email> streamTwoNewKey = kStreamEmail.selectKey((k, v) -> v.getClienteId()+"");

		CustomerEmailJoiner joiner = new CustomerEmailJoiner();

		streamOneNewKey.join(streamTwoNewKey,
				((Customer v1, Email v2) -> {
					v1.getEmail().setClienteId(v2.getClienteId());
					v1.getEmail().setEmail(v2.getEmail());
					return v1;
				}),
				JoinWindows.of(Duration.ofMinutes(5000)))
//				.foreach((k,v)-> System.out.println(v));
				.to("STREAMS.CUSTOMER.EMAIL", Produced.with(Serdes.String(), customerSerde));

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
