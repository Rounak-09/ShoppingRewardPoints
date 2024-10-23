package com.shopping.rewardPoints.service;

import com.shopping.rewardPoints.exception.UserException;
import com.shopping.rewardPoints.model.Invoice;
import com.shopping.rewardPoints.repository.InvoiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceDAO invoiceDAO;

    @Override
    public List<Invoice> saveInvoices(List<Invoice> invoices) {
        if (invoices == null || invoices.isEmpty()) throw new UserException("Please provide invoice details!");
        return invoiceDAO.saveAll(invoices);
    }
}
