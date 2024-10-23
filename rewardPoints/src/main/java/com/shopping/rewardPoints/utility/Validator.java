package com.shopping.rewardPoints.utility;

import com.shopping.rewardPoints.exception.UserException;
import com.shopping.rewardPoints.model.Invoice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class Validator {

    private Validator() {
    }

    private static void checkIfValidCustomerId(String customerId) {
        if (customerId == null || customerId.trim().equals("")) throw new UserException("Invalid customerId entered");
    }

    private static void checkIfValidMonth(LocalDate date) {
        if (date == null || date.getMonth() == null) throw new UserException("Invalid date entered");
    }

    private static void checkIfValidInvoiceAmount(Double invoiceAmount) {
        if (invoiceAmount == null) throw new UserException("Invalid invoice amount entered");
    }

    public static void checkIfValidPurchaseRecord(Invoice purchaseRecord) {
        if (purchaseRecord == null) throw new UserException("Purchase record can't be null");
        checkIfValidCustomerId(purchaseRecord.getCustomerId());
        checkIfValidMonth(purchaseRecord.getInvoiceDate());
        checkIfValidInvoiceAmount(purchaseRecord.getInvoiceAmount());
    }

}
