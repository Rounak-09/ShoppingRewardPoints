package com.shopping.rewardPoints.service;

import com.shopping.rewardPoints.model.Invoice;

import java.util.List;

public interface InvoiceService {

    List<Invoice> saveInvoices(List<Invoice> invoices);
}
