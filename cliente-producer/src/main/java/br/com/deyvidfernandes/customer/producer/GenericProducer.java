package br.com.deyvidfernandes.customer.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.Future;

@Service
public class GenericProducer {
    @Autowired
    private KafkaProducer<String, Object> producer;

    public Future<RecordMetadata> producer(String topic, Object value) {
        ProducerRecord<String, Object> recordProducer = new ProducerRecord<String, Object>(topic, UUID.randomUUID().toString() ,value);
        return producer.send(recordProducer);
    }
}