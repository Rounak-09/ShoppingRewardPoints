package com.shopping.rewardPoints.service;

import com.shopping.rewardPoints.dto.CustomerRewardPoints;

import java.util.List;

public interface RewardService {
    List<CustomerRewardPoints> getCustomerRewardPoints(String from, String to);
}
