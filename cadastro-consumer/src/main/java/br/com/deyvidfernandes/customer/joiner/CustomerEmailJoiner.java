package br.com.deyvidfernandes.customer.joiner;

import br.com.deyvidfernandes.customer.producer.dto.Customer;
import br.com.deyvidfernandes.customer.producer.dto.Email;
import org.apache.kafka.streams.kstream.ValueJoiner;

public class CustomerEmailJoiner implements ValueJoiner<Customer, Email, Customer> {
    @Override
    public Customer apply(Customer customer, Email email) {
        customer.setEmail(email);
        return customer;
    }
}
