package com.awesomepizza.service;

import com.awesomepizza.model.Order;
import com.awesomepizza.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository; // Mockeremo il repository

    @InjectMocks
    private OrderService orderService; // La classe che testiamo

    private Order order;

    @BeforeEach
    public void setUp() {
        order = new Order("Pizza Capricciosa");
    }

    /**
     * Test CreateOrder
     * 
     * Testa la creazione di un ordine
     */
    @Test
    public void testCreateOrder() {
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);
        assertNotNull(createdOrder);
        assertEquals(order.getOrdine(), createdOrder.getOrdine());

        verify(orderRepository, times(1)).save(any(Order.class));
    }

    /**
     * Test GetOrders
     * 
     * Testa la ricerca di tutti gli ordini
     */
    @Test
    public void testGetOrders() {

        Order order1 = new Order("Pizza Margherita");
        Order order2 = new Order("Pizza Diavola");
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));
        
        var orders = orderService.getOrders();
        assertEquals(2, orders.size());
        assertEquals("Pizza Margherita", orders.get(0).getOrdine());
        assertEquals("DA_FARE", orders.get(0).getStato());
    }

    /**
     * Test GetOrderById
     * 
     * Testa la ricerca di un ordine tramite id
     */
    @Test
    public void testGetOrderById() {

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> foundOrder = orderService.getOrderById(1L);
        assertTrue(foundOrder.isPresent());
        assertEquals("Pizza Capricciosa", foundOrder.get().getOrdine());
    }

    /**
     * Test GetOrderByStatus
     * 
     * Testa la ricerca di un ordine tramite lo stato
     */
    @Test
    public void testGetOrdersByStatus() {
        Order order1 = new Order("Pizza Margherita");
        order1.setStato("CONSEGNATO");
        Order order2 = new Order("Pizza Pepperoni");
        order2.setStato("DA_FARE");
        when(orderRepository.findByStato("CONSEGNATO")).thenReturn(Arrays.asList(order1));

        var orders = orderService.getOrdersByStatus("CONSEGNATO");
        assertEquals(1, orders.size());
        assertEquals("CONSEGNATO", orders.get(0).getStato());
    }

    /**
     * Test UpdateOrderStatus
     * 
     * Testa l'aggiornamento dello stato di un ordine
     */
    @Test
    public void testUpdateOrderStatus() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        boolean isUpdated = orderService.updateOrderStatus(1L, "ANNULLATO");
        assertTrue(isUpdated);
        assertEquals("ANNULLATO", order.getStato());

        verify(orderRepository, times(1)).save(order);
    }

    /**
     * Test UpdateOrderStatusOrderNotFound
     * 
     * Testa il mancato ritrovamento di un ordine
     */
    @Test
    public void testUpdateOrderStatusOrderNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        boolean isUpdated = orderService.updateOrderStatus(1L, "ANNULLATO");
        assertFalse(isUpdated);
    }
}
