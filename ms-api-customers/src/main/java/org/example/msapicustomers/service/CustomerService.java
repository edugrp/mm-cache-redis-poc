package org.example.msapicustomers.service;

import org.example.business.model.Customer;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final List<Customer> sample = new ArrayList<>(Arrays.asList(new Customer(1, "John", "Smith", 0),
            new Customer(2, "James", "Miller", 0),
            new Customer(3, "Maria", "Campbell", 0)));

    public CustomerService() {}

    @Cacheable(value = "customer-by-id", key = "#id")
    public Optional<Customer> findById(long id) throws Exception {
        Thread.sleep(1000);
        return sample.stream().filter(x -> x.getId() == id).findFirst();
    }

    @CacheEvict(value = "customer-by-id", key = "#id")
    public Customer updateIncome(long id, float newIncome) throws Exception {
        Optional<Customer> customer = sample.stream().filter( x -> x.getId() == id).findFirst();
        if (customer.isPresent()) {
            customer.get().setIncome(newIncome);
            return customer.get();
        } else {
            throw new Exception("Customer not found");
        }
    }

}