package com.shopping.rewardPoints.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

@Data
public class Invoice {

    @Id
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private String customerId;
    private String customerName;
    private List<Item> itemList;
    private Double invoiceAmount;

}
