package com.example.bank.controller;

import com.example.bank.model.Customer;
import com.example.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;
@RequestMapping("/customer")
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("")
    public Customer addListCustomer(@RequestBody Customer customer){
        List<Customer> customers = customerService.getCustomerList();
        customers.add(customer);
        return customer;
    }

    @GetMapping("")
    public ResponseEntity<?> getListCustomer(){
        List<Customer> customers = customerService.getCustomerList();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable int id){
        Customer result = customerService.getCustomerById(id);
        System.out.println(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCustomer(@RequestParam(name= "keyword", required = false,defaultValue = "") String keyword){
        List<Customer> customers = customerService.searchCustomers(keyword);

        return ResponseEntity.ok(customers);
    }

    @DeleteMapping()
    public void delete(@RequestParam(name = "id") int id) {
        List<Customer> customers = customerService.getCustomerList();
        for (Customer customer : customers){
            if (customer.getId()==id) {
                customers.remove(customer);
                break;
            }
        }
    }

    @PostMapping("/customer")
    public ResponseEntity<?> creatCustomer(){
        return null;
    }
    @PutMapping("/customer/{id}")
    public ResponseEntity<?> updateCustomer(){
        return null;
    }
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(){
        return null;
    }


}
