package com.awesomepizza.repository;

import com.awesomepizza.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStato(String stato); // Trova gli ordini con un certo stato
}
