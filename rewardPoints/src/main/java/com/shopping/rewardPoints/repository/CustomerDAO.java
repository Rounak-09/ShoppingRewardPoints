package com.shopping.rewardPoints.repository;

import com.shopping.rewardPoints.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerDAO extends MongoRepository<Customer, String> {

}
