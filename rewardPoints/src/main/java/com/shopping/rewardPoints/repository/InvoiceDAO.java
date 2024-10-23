package com.shopping.rewardPoints.repository;

import com.shopping.rewardPoints.model.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceDAO extends MongoRepository<Invoice, String> {

//    List<Invoice> getByInvoiceDateGreaterThanEqualToAndInvoiceDateLessThanEqualTo(LocalDate from, LocalDate to);
}
