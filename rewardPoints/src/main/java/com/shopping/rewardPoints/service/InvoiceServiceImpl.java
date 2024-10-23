package com.shopping.rewardPoints.service;

import com.shopping.rewardPoints.exception.UserException;
import com.shopping.rewardPoints.model.Invoice;
import com.shopping.rewardPoints.repository.InvoiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    private InvoiceDAO invoiceDAO;

    @Override
    public void saveInvoices(List<Invoice> invoices) {
        if (invoices==null || invoices.isEmpty()) throw new UserException("No invoice provided");
        invoiceDAO.saveAll(invoices);
    }
}
