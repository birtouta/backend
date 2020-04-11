package com.birtouta.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="orders")
public class Order  implements Serializable{
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="id_store")
	@JsonBackReference
    private Store store;
	
	@ManyToOne
	@JoinColumn(name="id_user")
	@JsonBackReference
	private User user;
}
