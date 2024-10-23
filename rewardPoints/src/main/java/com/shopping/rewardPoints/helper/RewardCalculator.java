package com.shopping.rewardPoints.helper;

import com.shopping.rewardPoints.dto.CustomerRewardPoints;
import com.shopping.rewardPoints.dto.MonthlyRewardPoints;
import com.shopping.rewardPoints.model.Customer;
import com.shopping.rewardPoints.model.Invoice;
import com.shopping.rewardPoints.utility.Validator;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RewardCalculator {

    private int calculateRewardPoints(Double amount) {
        if (amount == null || amount <= 0) {
            return 0;
        }

        int points = 0;
        if (amount > 50) {
            points += Math.min(amount.intValue() - 50, 50);
        }
        if (amount > 100) {
            points += (amount.intValue() - 100) * 2;
        }
        return points;
    }

    private Map<Customer, Map<Month, Integer>> getCustomerRewardPointsMap(List<Invoice> invoices) {
        Map<Customer, Map<Month, Integer>> customerWiseRewardPoints = new HashMap<>();
        for (Invoice invoice : invoices) {
            Validator.checkIfValidPurchaseRecord(invoice);
            Customer customer = new Customer(invoice.getCustomerId(), invoice.getCustomerName());
            Month month = invoice.getInvoiceDate().getMonth();
            int rewardPoints = calculateRewardPoints(invoice.getInvoiceAmount());
            customerWiseRewardPoints.computeIfAbsent(customer, k -> new HashMap<>()).merge(month, rewardPoints, Integer::sum);
        }
        return customerWiseRewardPoints;
    }

    private List<CustomerRewardPoints> getCustomerRewardPointsList(Map<Customer, Map<Month, Integer>> customerWiseRewardPoints) {
        List<CustomerRewardPoints> customerRewardPointsList = new ArrayList<>();
        for (Map.Entry<Customer, Map<Month, Integer>> customerEntry : customerWiseRewardPoints.entrySet()) {
            CustomerRewardPoints customerRewardPoints = new CustomerRewardPoints();
            customerRewardPoints.setCustomerId(customerEntry.getKey().getCustomerId());
            customerRewardPoints.setCustomerName(customerEntry.getKey().getCustomerName());

            List<MonthlyRewardPoints> monthlyRewardPointsList = customerEntry.getValue().entrySet().stream()
                    .map(monthEntry -> new MonthlyRewardPoints(monthEntry.getKey(), monthEntry.getValue()))
                    .collect(Collectors.toList());

            int totalRewardPoints = monthlyRewardPointsList.stream().mapToInt(MonthlyRewardPoints::getRewardPoints).sum();

            customerRewardPoints.setMonthlyRewardPointsList(monthlyRewardPointsList);
            customerRewardPoints.setTotalRewardPoints(totalRewardPoints);
            customerRewardPointsList.add(customerRewardPoints);
        }
        return customerRewardPointsList;
    }

    public List<CustomerRewardPoints> calculate(List<Invoice> purchaseRecords) {
        return getCustomerRewardPointsList(getCustomerRewardPointsMap(purchaseRecords));
    }
}
