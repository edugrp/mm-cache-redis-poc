package org.example.msapicustomers.controller;

import org.example.business.model.Customer;
import org.example.msapicustomers.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/{id}")
    public Optional<Customer> getById(@PathVariable("id") long id) throws Exception {
        return this.customerService.findById(id);
    }

    @PostMapping("/customer/{id}/income")
    public Customer updateIncome(@PathVariable("id") long id, @RequestBody Customer customer) throws Exception {
        return this.customerService.updateIncome(id, customer.getIncome());
    }
}
