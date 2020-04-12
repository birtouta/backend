package com.birtouta.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Entity
@Table(name="deliveries")
@JsonIgnoreProperties({"userTarget","userDeliver", "store", "order"})

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
	
	public Delivery() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUserTarget() {
		return userTarget;
	}

	public void setUserTarget(User userTarget) {
		this.userTarget = userTarget;
	}

	public User getUseDeliver() {
		return userDeliver;
	}

	public void setUseDeliver(User userDeliver) {
		this.userDeliver = userDeliver;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}	
	
}
