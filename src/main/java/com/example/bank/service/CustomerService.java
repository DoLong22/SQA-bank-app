package com.example.bank.service;

import com.example.bank.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    Customer regisCustomer(Customer customer) ;
    public List<Customer> getCustomerList();
    public Customer getCustomerById(int id);
    public List<Customer> searchCustomers(String keyword);
}
