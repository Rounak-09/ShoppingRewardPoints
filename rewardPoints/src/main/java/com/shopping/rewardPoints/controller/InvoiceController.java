package com.shopping.rewardPoints.controller;

import com.shopping.rewardPoints.model.Invoice;
import com.shopping.rewardPoints.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/shopping/invoices")
@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("")
    public ResponseEntity<String>saveInvoicesHandler(@RequestBody List<Invoice> invoices) {
        invoiceService.saveInvoices(invoices);
        return new ResponseEntity("Invoice saved successfully", HttpStatus.OK);
    }
}
