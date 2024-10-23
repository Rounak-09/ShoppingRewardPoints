package com.shopping.rewardPoints.model;

import lombok.Data;

@Data
public class Item {

    private String itemNumber;
    private String itemName;
    private Integer quantity;
    private Double rate;
    private Double amount;

}
