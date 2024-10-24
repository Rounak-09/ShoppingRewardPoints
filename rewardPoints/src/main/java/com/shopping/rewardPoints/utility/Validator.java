package com.shopping.rewardPoints.utility;

import com.shopping.rewardPoints.exception.UserException;
import com.shopping.rewardPoints.model.Customer;
import com.shopping.rewardPoints.model.Invoice;
import com.shopping.rewardPoints.model.Item;

import java.time.LocalDate;
import java.util.List;

public final class Validator {

    private Validator() {
    }

    private static void checkIfValidCustomerId(String customerId) {
        if (customerId == null || customerId.trim().equals("")) {
            throw new UserException("Invalid customerId entered!");
        }
    }

    private static void checkIfValidCustomerName(String customerName) {
        if (customerName == null || customerName.trim().equals("")) {
            throw new UserException("Invalid customer name entered!");
        }
    }

    private static void checkIfValidAddress(String address) {
        if (address == null || address.trim().equals("")) {
            throw new UserException("Invalid customer address entered!");
        }
    }

    private static void checkIfValidPhoneNumber(String phone) {
        if (phone == null || phone.trim().equals("")) {
            throw new UserException("Invalid phone number entered!");
        }
    }

    public static void validateCustomer(Customer customer) {
        if (customer==null) throw new UserException("Please provide customer details!");
        checkIfValidCustomerId(customer.getCustomerId());
        checkIfValidCustomerName(customer.getCustomerName());
        checkIfValidAddress(customer.getAddress());
        checkIfValidPhoneNumber(customer.getPhoneNumber());
    }

    public static void validateCustomers(List<Customer> customers) {
        if (customers == null || customers.isEmpty()) {
            throw new UserException("Please provide customer details!");
        }

        for (Customer customer: customers) {
            validateCustomer(customer);
        }
    }

    private static void checkIfValidInvoiceNumber(String invoiceNumber) {
        if (invoiceNumber == null || invoiceNumber.trim().equals("")) {
            throw new UserException("Invalid invoice number entered!");
        }
    }

    private static void checkIfValidMonth(LocalDate date) {
        if (date == null || date.getMonth() == null) {
            throw new UserException("Invalid date entered!");
        }
    }

    private static void checkIfValidItemNumber(String itemNumber) {
        if (itemNumber == null || itemNumber.trim().equals("")) {
            throw new UserException("Invalid item number entered!");
        }
    }

    private static void checkIfValidItemName(String itemName) {
        if (itemName == null || itemName.trim().equals("")) {
            throw new UserException("Invalid item name entered!");
        }
    }

    private static void checkIfValidQuantity(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new UserException("Invalid quantity entered!");
        }
    }

    private static void checkIfValidRate(Double rate) {
        if (rate == null || rate <= 0) {
            throw new UserException("Invalid rate entered!");
        }
    }

    public static void validateInvoice(Invoice invoice) {
        if (invoice == null) {
            throw new UserException("Please provide invoice details!");
        }

        checkIfValidCustomerId(invoice.getCustomerId());
        checkIfValidCustomerName(invoice.getCustomerName());
        checkIfValidInvoiceNumber(invoice.getInvoiceNumber());
        checkIfValidMonth(invoice.getInvoiceDate());
        checkIfValidItems(invoice.getItemList());

        double invoiceAmount = invoice.getItemList().stream()
                .mapToDouble(Item::getAmount)
                .sum();

        invoice.setInvoiceAmount(Math.floor(invoiceAmount) * 100 / 100);
    }

    public static void validateInvoices(List<Invoice> invoices) {
        if (invoices == null || invoices.isEmpty()) {
            throw new UserException("Please provide invoice details!");
        }

        for (Invoice invoice: invoices) {
           validateInvoice(invoice);
        }
    }

    private static void checkIfValidItems(List<Item> items) {
        if (items==null || items.isEmpty()) throw new UserException("Please provide item details!");
        for (Item item: items) {
            if (item==null) throw new UserException("Please provide item details!");
            checkIfValidItemNumber(item.getItemNumber());
            checkIfValidItemName(item.getItemName());
            checkIfValidQuantity(item.getQuantity());
            checkIfValidRate(item.getRate());
            item.setAmount(Math.floor(item.getQuantity() * item.getRate()) * 100 / 100);
        }
    }

}
