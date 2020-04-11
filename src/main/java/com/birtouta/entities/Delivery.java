package com.birtouta.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="deliveries")
public class Delivery implements Serializable{
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_order")
	private Order order;
}
