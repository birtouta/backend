package com.birtouta.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="orders")
public class Order  implements Serializable{
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
}
