package com.shopping.rewardPoints.service;

import com.shopping.rewardPoints.exception.UserException;
import com.shopping.rewardPoints.model.Invoice;
import com.shopping.rewardPoints.repository.InvoiceDAO;
import com.shopping.rewardPoints.utility.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceDAO invoiceDAO;

    @Override
    public Invoice saveInvoice(Invoice invoice) {
        Validator.validateInvoice(invoice);

        if (invoiceDAO.existsById(invoice.getCustomerId())) {
            throw new UserException("Invoice number is already present!");
        }
        return invoiceDAO.save(invoice);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceDAO.findAll();
    }

    @Override
    public Invoice getInvoiceByInvoiceNumber(String invoiceNumber) {
        if (invoiceNumber==null || invoiceNumber.trim().isEmpty()) {
            throw new UserException("Please provide invoice number!");
        }

        Optional<Invoice> optionalInvoice = invoiceDAO.findById(invoiceNumber);
        if (optionalInvoice.isPresent()) {
            return optionalInvoice.get();
        } else {
            throw new UserException("Invoice not found!");
        }
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Validator.validateInvoice(invoice);

        if (invoiceDAO.existsById(invoice.getInvoiceNumber())) {
            return invoiceDAO.save(invoice);
        }
        throw new UserException("Customer not found!");
    }

    @Override
    public void deleteInvoiceByInvoiceNumber(String invoiceNumber) {
        if (invoiceNumber==null || invoiceNumber.trim().isEmpty()) {
            throw new UserException("Please provide invoice number!");
        }

        if (invoiceDAO.existsById(invoiceNumber)) {
            invoiceDAO.deleteById(invoiceNumber);
        } else {
            throw new UserException("Invoice not found!");
        }
    }
}
