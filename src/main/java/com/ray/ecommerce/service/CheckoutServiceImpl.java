package com.ray.ecommerce.service;

import com.ray.ecommerce.dao.OrderRepository;
import com.ray.ecommerce.dao.UserRepository;
import com.ray.ecommerce.domain.User;
import com.ray.ecommerce.dto.Purchase;
import com.ray.ecommerce.dto.PurchaseResponse;
import com.ray.ecommerce.entity.Order;
import com.ray.ecommerce.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private UserRepository userRepository;
    private OrderRepository orderRepository;

    @Autowired
    public CheckoutServiceImpl(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from DTO
        Order order = purchase.getOrder();

        // generate tracking number using UUID (Universally Unique Identifier) ver 4
        String orderTrackingNumber = UUID.randomUUID().toString();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // get orderItems
        List<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));
//        for (OrderItem orderItem : orderItems) {
//            order.add(orderItem);
//        }

        // get addresses
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // get customer info
        User user = userRepository.findUserByEmail(purchase.getUser().getEmail());
        user.add(order);

        // save
//        userRepository.save(user);
        Order insertedOrder = orderRepository.save(order);

        return new PurchaseResponse(orderTrackingNumber, insertedOrder.getDateCreated());
    }
}
