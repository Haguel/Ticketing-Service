package com.example.ticketing_service.service;

import com.example.ticketing_service.dao.CustomerRepository;
import com.example.ticketing_service.dto.CustomerDTO;
import com.example.ticketing_service.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO.getName(), customerDTO.getSurname(), customerDTO.getEmail(), customerDTO.getPhone());

        customerRepository.save(customer);

        return customer;
    }

    public Customer findCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }
}
