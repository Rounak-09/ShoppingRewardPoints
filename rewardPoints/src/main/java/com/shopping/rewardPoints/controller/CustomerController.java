package com.shopping.rewardPoints.controller;

import com.shopping.rewardPoints.model.Customer;
import com.shopping.rewardPoints.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shopping/customer")
public class CustomerController {

    private CustomerService customerService;

    public ResponseEntity<List<Customer>> saveCustomerHandler(@RequestBody List<Customer> customers) {
        List<Customer> customerList = customerService.saveCustomers(customers);
        return new ResponseEntity<>(customerList, HttpStatus.CREATED);
    }
}
