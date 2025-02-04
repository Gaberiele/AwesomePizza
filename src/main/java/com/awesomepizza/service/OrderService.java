package com.awesomepizza.service;

import com.awesomepizza.model.Order;
import com.awesomepizza.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        Order newOrder = new Order(order.getOrdine());
        return orderRepository.save(newOrder);
    }
    
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByStatus(String stato) {
        return orderRepository.findByStato(stato);
    }

    public boolean updateOrderStatus(Long id, String stato) {
        Optional<Order> orderOpt = orderRepository.findById(id);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setStato(stato);
            orderRepository.save(order);
            return true;
        }
        return false;
    }
}
