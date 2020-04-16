package com.birtouta.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Table(name="orders")
@JsonIgnoreProperties({"store", "user", "delivery"})
@Data
public class Order  implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	@CreationTimestamp
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;
	
	private int validated; 
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "validated_at")
	private Date validatedAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "received_at")
	private Date receivedAt;

	private int prepared ;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "prepared_at")
	private Date preparedAt;

	private int delivered ;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delivered_at")
	private Date deliveredAt;

	private int approved ;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "approved_at")
	private Date approvedAt;

	private int paid ;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "paid_at")
	private Date paidAt;

	private int canceled ;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "canceled_at")
	private Date canceledAt;

	@Column(name = "type_delivers")
	private int typeDelivery ;

	@ManyToOne
	@JoinColumn(name="id_store")
	@JsonBackReference(value="store")
	private Store store;

	@ManyToOne
	@JoinColumn(name="id_user")
	@JsonBackReference(value="user")
	private User user;

	@OneToMany(mappedBy="order" , fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value="orderProducts")
	private List<OrderProduct> orderProducts;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "order")
	@JsonManagedReference(value="delivery")
	private Delivery delivery;

}