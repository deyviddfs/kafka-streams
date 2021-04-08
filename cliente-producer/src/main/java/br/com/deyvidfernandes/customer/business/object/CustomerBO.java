package br.com.deyvidfernandes.customer.business.object;

import br.com.deyvidfernandes.customer.controller.dto.CustomerDto;
import br.com.deyvidfernandes.customer.controller.dto.EmailDto;
import br.com.deyvidfernandes.customer.producer.GenericProducer;
import br.com.deyvidfernandes.customer.producer.dto.Customer;
import br.com.deyvidfernandes.customer.producer.dto.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomerBO {
    @Autowired
    GenericProducer genericProducer;
    @Value("${spring.kafka.topic.customer}")
    private String topicCustomer;
    @Value("${spring.kafka.topic.email}")
    private String topicEmail;

    public void create(CustomerDto customerDto) {
        Customer customer = Customer.newBuilder()
                .setId(customerDto.getId())
                .setNome(customerDto.getNome())
                .setIdade(customerDto.getIdade())
                .setEmail(Email.newBuilder().setClienteId(1).setEmail("").build())
                .build();
        producer(topicCustomer, customer);
    }

    public void create(EmailDto emailDto) {
        Email email = Email.newBuilder()
                .setClienteId(emailDto.getClienteId())
                .setEmail(emailDto.getEmail())
                .build();
        producer(topicEmail, email);
    }

    private void producer(String topic, Object customer) {
        genericProducer.producer(topic, customer);
    }
}
