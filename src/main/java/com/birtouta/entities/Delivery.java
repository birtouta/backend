package com.birtouta.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Table(name="deliveries")
@JsonIgnoreProperties({"userTarget","userDeliver", "store", "order"})
@Data
public class Delivery implements Serializable{
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="id_target",referencedColumnName = "id", nullable=false, updatable=false)
	@JsonBackReference
    private User userTarget;
	
	@ManyToOne
    @JoinColumn(name="id_deliver", referencedColumnName = "id", nullable=false, updatable=false)
	@JsonBackReference
    private User userDeliver;
	
	@ManyToOne
    @JoinColumn(name="id_store", nullable=false, updatable=false)
	@JsonBackReference
    private Store store;
	
	@OneToOne
    @JoinColumn(name="id_order", nullable=false, updatable=false)
	@JsonBackReference
    private Order order;
	
}
