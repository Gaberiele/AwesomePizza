package com.awesomepizza.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.awesomepizza.model.Order;
import com.awesomepizza.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    
    /**
     * Test CreateOrder
     * 
     * Testa la creazione di un ordine
     * 
     * @throws Exception
     */
    @Test    
    public void testCreateOrder() throws Exception {
        Order order = new Order("Pizza 4 Formaggi");

        when(orderService.createOrder(any(Order.class))).thenReturn(order);

        mockMvc.perform(post("/orders/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ordine").value("Pizza 4 Formaggi"));
    }

    /**
     * Test GetOrderById
     * 
     * Testa la ricerca di un ordine tramite id
     * 
     * @throws Exception
     */
    @Test
    public void testGetOrderById() throws Exception {
        Order order = new Order("Pizza Vesuvio");
        when(orderService.getOrderById(1L)).thenReturn(java.util.Optional.of(order));

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ordine").value("Pizza Vesuvio"));
    }

    /**
     * Test UpdateOrderStatus
     * 
     * Testa l'aggiornamento dello stato di un ordine     * 
     * 
     * @throws Exception
     */
    @Test
    public void testUpdateOrderStatus() throws Exception {
        when(orderService.updateOrderStatus(1L, "IN_CONSEGNA")).thenReturn(true);

        mockMvc.perform(put("/orders/1/update?status=IN_CONSEGNA"))
                .andExpect(status().isOk())
                .andExpect(content().string("Stato aggiornato"));
    }

    /**
     * Test UpdateOrderStatus_NotFound
     * 
     * Testa la non individuazione di un ordine
     * 
     * @throws Exception
     */
    @Test
    public void testUpdateOrderStatus_NotFound() throws Exception {
        when(orderService.updateOrderStatus(99L, "DA_FARE")).thenReturn(false);

        mockMvc.perform(put("/orders/99/update?status=DA_FARE"))
                .andExpect(status().isOk())
                .andExpect(content().string("Ordine non trovato"));
    }
}
