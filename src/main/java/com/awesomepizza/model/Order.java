package com.awesomepizza.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ordini")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String ordine;
    private String stato; // DA_FARE, IN_LAVORAZIONE, IN_CONSEGNA, CONSEGNATO, ANNULLATO
    
    private LocalDateTime dataOra;

    public Order() {}

    public Order(String ordine) {
        this.ordine = ordine;
        this.stato = "DA_FARE";
        this.dataOra = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getOrdine() { return ordine; }
    public String getStato() { return stato; }
    public LocalDateTime getDataOra() { return dataOra; }

    public void setStato(String stato) { this.stato = stato; }
}
