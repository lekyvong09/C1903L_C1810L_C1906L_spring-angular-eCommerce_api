package com.ray.ecommerce.dto;

import com.ray.ecommerce.domain.User;
import com.ray.ecommerce.entity.Address;
import com.ray.ecommerce.entity.Order;
import com.ray.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class Purchase {
    private User user;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private List<OrderItem> orderItems;
}
