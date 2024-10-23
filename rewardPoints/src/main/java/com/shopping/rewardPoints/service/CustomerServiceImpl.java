package com.shopping.rewardPoints.service;

import com.shopping.rewardPoints.exception.UserException;
import com.shopping.rewardPoints.model.Customer;
import com.shopping.rewardPoints.repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public List<Customer> saveCustomers(List<Customer> customers) {
        if (customers == null || customers.isEmpty()) throw new UserException("Please provide customer details!");
        return customerDAO.saveAll(customers);
    }
}
