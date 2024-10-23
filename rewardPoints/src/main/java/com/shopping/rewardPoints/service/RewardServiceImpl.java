package com.shopping.rewardPoints.service;

import com.shopping.rewardPoints.dto.CustomerRewardPoints;
import com.shopping.rewardPoints.helper.RewardCalculator;
import com.shopping.rewardPoints.model.Invoice;
import com.shopping.rewardPoints.repository.InvoiceDAO;
import com.shopping.rewardPoints.utility.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    private InvoiceDAO invoiceDAO;

    @Autowired
    private RewardCalculator rewardCalculator;

    @Override
    public List<CustomerRewardPoints> getCustomerRewardPoints(String from, String to) {
        LocalDate fromDate = Converter.getLocalDateFromString(from);
        LocalDate toDate = Converter.getLocalDateFromString(to);
        List<Invoice> invoices = (fromDate == null || toDate == null)
                ? invoiceDAO.getByInvoiceDateBetween(LocalDate.now().minusMonths(3).minusDays(1), LocalDate.now().plusDays(1)) //Last 3 months data is considered if no date range is provided
                : invoiceDAO.getByInvoiceDateBetween(fromDate.minusDays(1), toDate.plusDays(1)); //1 day is subtracted and added to the date range as between excludes the from and to date
        return rewardCalculator.calculate(invoices);
    }
}
