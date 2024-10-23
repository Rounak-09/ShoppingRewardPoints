package com.shopping.rewardPoints.service;

import com.shopping.rewardPoints.model.Invoice;

import java.util.List;

public interface InvoiceService {

    void saveInvoices(List<Invoice> invoices);
}
