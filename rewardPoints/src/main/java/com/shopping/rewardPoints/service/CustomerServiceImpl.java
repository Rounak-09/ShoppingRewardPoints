package com.shopping.rewardPoints.service;

import com.shopping.rewardPoints.exception.UserException;
import com.shopping.rewardPoints.model.Customer;
import com.shopping.rewardPoints.repository.CustomerDAO;
import com.shopping.rewardPoints.utility.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    @Override
    public void deleteCustomerById(String customerId) {
        if (customerId==null || customerId.trim().isEmpty()) {
            throw new UserException("Please provide customer details!");
        }

        if (customerDAO.existsById(customerId)) {
            customerDAO.deleteById(customerId);
        } else {
            throw new UserException("Customer not found!");
        }
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        Validator.validateCustomer(customer);

        if (customerDAO.existsById(customer.getCustomerId())) {
            throw new UserException("CustomerId is already present!");
        }
        return customerDAO.save(customer);
    }

    @Override
    public Customer getCustomerById(String customerId) {
        if (customerId==null || customerId.trim().isEmpty()) {
            throw new UserException("Please provide customerId!");
        }

        Optional<Customer> optionalCustomer = customerDAO.findById(customerId);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new UserException("Customer not found!");
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Validator.validateCustomer(customer);

        if (customerDAO.existsById(customer.getCustomerId())) {
            return customerDAO.save(customer);
        }
        throw new UserException("Customer not found!");
    }

}
