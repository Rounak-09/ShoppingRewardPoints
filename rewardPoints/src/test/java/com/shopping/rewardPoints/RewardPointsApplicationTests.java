package com.shopping.rewardPoints;

import com.shopping.rewardPoints.dto.CustomerRewardPoints;
import com.shopping.rewardPoints.dto.MonthlyRewardPoints;
import com.shopping.rewardPoints.model.Customer;
import com.shopping.rewardPoints.model.Invoice;
import com.shopping.rewardPoints.model.Item;
import com.shopping.rewardPoints.repository.CustomerDAO;
import com.shopping.rewardPoints.repository.InvoiceDAO;
import com.shopping.rewardPoints.service.CustomerService;
import com.shopping.rewardPoints.service.InvoiceService;
import com.shopping.rewardPoints.service.RewardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class RewardPointsApplicationTests {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerDAO customerDAO;

    @Autowired
    private InvoiceService invoiceService;

    @MockBean
    private InvoiceDAO invoiceDAO;

    @Autowired
    private RewardService rewardService;

    @Test
    void saveCustomerTest() {
        Customer customer = new Customer("001", "Rounak", "7978881737", "Bhubaneswar");

        when(customerDAO.save(customer)).thenReturn(customer);
        assertEquals(customer, customerService.saveCustomer(customer));
    }

    @Test
    void getAllCustomersTest() {
        List<Customer> customers = Stream.of(
                new Customer("001", "Rounak", "7978881737", "Bhubaneswar"),
                new Customer("002", "Ravi", "7978881738", "Bangalore"),
                new Customer("003", "Murali", "7978881739", "Chennai")
        ).collect(Collectors.toList());

        when(customerDAO.findAll()).thenReturn(customers);
        assertEquals(customers, customerService.getAllCustomers());
    }

    @Test
    void getCustomerByIdTest() {
        String customerId = "001";
        Customer customer = new Customer("001", "Rounak", "7978881737", "Bhubaneswar");

        when(customerDAO.findById(customerId)).thenReturn(Optional.of(customer));
        assertEquals(customer, customerService.getCustomerById(customerId));
    }

    @Test
    void updateCustomerTest() {
        Customer customer = new Customer("001", "Rounak", "7978881737", "Bhubaneswar");

        when(customerDAO.existsById(customer.getCustomerId())).thenReturn(true);
        when(customerDAO.save(customer)).thenReturn(customer);
        assertEquals(customer, customerService.updateCustomer(customer));
    }

    @Test
    void deleteCustomerByIdTest() {
        String customerId = "001";

        when(customerDAO.existsById(customerId)).thenReturn(true);
        customerService.deleteCustomerById(customerId);
        verify(customerDAO, times(1)).deleteById(customerId);
    }

    @Test
    void saveInvoiceTest() {
        Invoice invoice = new Invoice("001", LocalDate.parse("2024-06-10"), "001", "Rounak",
                Stream.of(
                        new Item("001", "Chips", 1, 20.0, 20.0),
                        new Item("002", "Chocolate", 2, 50.0, 100.0))
                        .collect(Collectors.toList()), 120.00);

        when(invoiceDAO.save(invoice)).thenReturn(invoice);
        assertEquals(invoice, invoiceService.saveInvoice(invoice));
    }

    @Test
    void getAllInvoicesTest() {
        List<Invoice> invoices = Stream.of( new Invoice("001", LocalDate.parse("2024-06-10"), "001", "Rounak",
                Stream.of(
                                new Item("001", "Chips", 1, 20.0, 20.0),
                                new Item("002", "Chocolate", 2, 50.0, 100.0))
                        .collect(Collectors.toList()), 120.00),
                new Invoice("002", LocalDate.parse("2024-07-10"), "002", "Ravi",
                        Stream.of(
                                        new Item("001", "Chips", 1, 20.0, 20.0),
                                        new Item("002", "Chocolate", 2, 50.0, 100.0))
                                .collect(Collectors.toList()), 120.00),
                new Invoice("003", LocalDate.parse("2024-08-10"), "003", "Murali",
                        Stream.of(
                                        new Item("001", "Chips", 1, 20.0, 20.0),
                                        new Item("002", "Chocolate", 2, 50.0, 100.0))
                                .collect(Collectors.toList()), 120.00)).collect(Collectors.toList());

        when(invoiceDAO.findAll()).thenReturn(invoices);
        assertEquals(invoices, invoiceService.getAllInvoices());
    }

    @Test
    void getInvoiceByInvoiceNumberTest() {
        String invoiceNumber = "001";
        Invoice invoice = new Invoice("001", LocalDate.parse("2024-06-10"), "001", "Rounak",
                Stream.of(
                                new Item("001", "Chips", 1, 20.0, 20.0),
                                new Item("002", "Chocolate", 2, 50.0, 100.0))
                        .collect(Collectors.toList()), 120.00);

        when(invoiceDAO.findById(invoiceNumber)).thenReturn(Optional.of(invoice));
        assertEquals(invoice, invoiceService.getInvoiceByInvoiceNumber(invoiceNumber));
    }

    @Test
    void updateInvoiceTest() {
        Invoice invoice = new Invoice("001", LocalDate.parse("2024-06-10"), "001", "Rounak",
                Stream.of(
                                new Item("001", "Chips", 1, 20.0, 20.0),
                                new Item("002", "Chocolate", 2, 50.0, 100.0))
                        .collect(Collectors.toList()), 120.00);

        when(invoiceDAO.existsById(invoice.getInvoiceNumber())).thenReturn(true);
        when(invoiceDAO.save(invoice)).thenReturn(invoice);
        assertEquals(invoice, invoiceService.updateInvoice(invoice));
    }

    @Test
    void deleteInvoiceByInvoiceNumberTest() {
        String invoiceNumber = "001";

        when(invoiceDAO.existsById(invoiceNumber)).thenReturn(true);
        invoiceService.deleteInvoiceByInvoiceNumber(invoiceNumber);
        verify(invoiceDAO, times(1)).deleteById(invoiceNumber);
    }

    @Test
    void getCustomerRewardPointsTest() {
        List<Invoice> invoices = Stream.of(
                new Invoice("001", LocalDate.parse("2024-06-10"), "001", "Rounak", new ArrayList<>(), 120.00),
                new Invoice("002", LocalDate.parse("2024-06-12"), "001", "Rounak", new ArrayList<>(), 60.00),
                new Invoice("003", LocalDate.parse("2024-06-15"), "001", "Rounak", new ArrayList<>(), 40.00),
                new Invoice("004", LocalDate.parse("2024-07-10"), "001", "Rounak", new ArrayList<>(), 110.00),
                new Invoice("005", LocalDate.parse("2024-07-12"), "001", "Rounak", new ArrayList<>(), 90.00),
                new Invoice("006", LocalDate.parse("2024-06-10"), "002", "Ravi", new ArrayList<>(), 150.00),
                new Invoice("007", LocalDate.parse("2024-06-12"), "002", "Ravi", new ArrayList<>(), 30.00),
                new Invoice("008", LocalDate.parse("2024-06-15"), "002", "Ravi", new ArrayList<>(), 20.00),
                new Invoice("009", LocalDate.parse("2024-07-12"), "002", "Ravi", new ArrayList<>(), 190.00),
                new Invoice("010", LocalDate.parse("2024-07-12"), "002", "Ravi", new ArrayList<>(), 10.00),
                new Invoice("011", LocalDate.parse("2024-08-10"), "002", "Ravi", new ArrayList<>(), 5.00),
                new Invoice("012", LocalDate.parse("2024-08-12"), "002", "Ravi", new ArrayList<>(), 15.00)
        ).collect(Collectors.toList());
        List<CustomerRewardPoints> customerRewardPointsList = Stream.of(
                        new CustomerRewardPoints("001", "Rounak", Stream.of(
                                        new MonthlyRewardPoints(Month.JUNE, 100),
                                        new MonthlyRewardPoints(Month.JULY, 110))
                                .collect(Collectors.toList()), 210),
                        new CustomerRewardPoints("002", "Ravi", Stream.of(
                                        new MonthlyRewardPoints(Month.JUNE, 150),
                                        new MonthlyRewardPoints(Month.JULY, 230),
                                        new MonthlyRewardPoints(Month.AUGUST, 0))
                                .collect(Collectors.toList()), 380))
                .collect(Collectors.toList());
        when(invoiceDAO.getByInvoiceDateBetween(LocalDate.parse("2024-05-31"), LocalDate.parse("2024-09-01"))).thenReturn(invoices);
        assertEquals(customerRewardPointsList, rewardService.getCustomerRewardPoints("01-06-2024", "31-08-2024"));
    }

}
