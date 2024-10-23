package com.shopping.rewardPoints.service;

import com.shopping.rewardPoints.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> saveCustomers(List<Customer> customers);
}
