package com.shopping.rewardPoints;

import com.shopping.rewardPoints.dto.CustomerRewardPoints;
import com.shopping.rewardPoints.dto.MonthlyRewardPoints;
import com.shopping.rewardPoints.model.Customer;
import com.shopping.rewardPoints.model.Invoice;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
    void saveCustomersTest() {
        List<Customer> customers = Stream.of(
                new Customer("001", "Rounak", "7978881737", "Bhubaneswar"),
                new Customer("002", "Ravi", "7978881738", "Delhi"),
                new Customer("003", "Murali", "7978881739", "Bangalore")
        ).collect(Collectors.toList());
        when(customerDAO.saveAll(customers)).thenReturn(customers);
        assertEquals(customers, customerService.saveCustomers(customers));
    }

    @Test
    void saveInvoicesTest() {
        List<Invoice> invoices = Stream.of(
                new Invoice("001", LocalDate.parse("2024-06-10"), "001", "Rounak", new ArrayList<>(), 120.00),
                new Invoice("002", LocalDate.parse("2024-07-10"), "001", "Rounak", new ArrayList<>(), 150.00),
                new Invoice("003", LocalDate.parse("2024-08-10"), "001", "Rounak", new ArrayList<>(), 130.00)
        ).collect(Collectors.toList());
        when(invoiceDAO.saveAll(invoices)).thenReturn(invoices);
        assertEquals(invoices, invoiceService.saveInvoices(invoices));
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
        when(invoiceDAO.getByInvoiceDateBetween(LocalDate.parse("2024-06-01"), LocalDate.parse("2024-08-31"))).thenReturn(invoices);
        assertEquals(customerRewardPointsList, rewardService.getCustomerRewardPoints("01-06-2024", "31-08-2024"));
    }

}
