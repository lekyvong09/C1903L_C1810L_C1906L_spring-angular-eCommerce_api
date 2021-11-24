package com.ray.ecommerce.service;

import com.ray.ecommerce.dto.Purchase;
import com.ray.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
