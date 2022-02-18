package com.ray.ecommerce.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PurchaseResponse {
    private String orderTrackingNumber;
    private Date dateCreated;

    public PurchaseResponse(String orderTrackingNumber, Date dateCreated) {
        this.orderTrackingNumber = orderTrackingNumber;
        this.dateCreated = dateCreated;
    }
}
