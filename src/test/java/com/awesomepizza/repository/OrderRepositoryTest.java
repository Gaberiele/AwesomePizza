package com.awesomepizza.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.awesomepizza.model.Order;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    private Order order1;

    @BeforeEach
    public void setUp() {

        order1 = new Order("DA_FARE");        
        orderRepository.save(order1);
    }

    /**
     * Test FindByStato
     * 
     * Testa la ricerca di ordini in base allo stato
     */
    @Test
    public void testFindByStato() {
        List<Order> foundOrders = orderRepository.findByStato("DA_FARE");

        assertNotNull(foundOrders);
        assertTrue(foundOrders.size() > 0);

        for (Order order : foundOrders) {
            assertEquals("DA_FARE", order.getStato());
        }
    }

}