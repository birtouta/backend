package com.birtouta.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("serial")
@Entity
@Table(name="orders")
@JsonIgnoreProperties({"user", "orderProducts", "delivery"})
public class Order  implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
//	@JsonBackReference
	private Store store;

	@ManyToOne
	@JoinColumn(name="id_user")
	@JsonBackReference
	private User user;

	@OneToMany(mappedBy="order" , fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<OrderProduct> orderProducts;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "order")
	@JsonManagedReference
	private Delivery delivery;

	public Order() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}

	public int getPrepared() {
		return prepared;
	}

	public void setPrepared(int prepared) {
		this.prepared = prepared;
	}

	public Date getPreparedAt() {
		return preparedAt;
	}

	public void setPreparedAt(Date preparedAt) {
		this.preparedAt = preparedAt;
	}

	public int getDelivered() {
		return delivered;
	}

	public void setDelivered(int delivered) {
		this.delivered = delivered;
	}

	public Date getDeliveredAt() {
		return deliveredAt;
	}

	public void setDeliveredAt(Date deliveredAt) {
		this.deliveredAt = deliveredAt;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getApprovedAt() {
		return approvedAt;
	}

	public void setApprovedAt(Date approvedAt) {
		this.approvedAt = approvedAt;
	}

	public int getPaid() {
		return paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}

	public Date getPaidAt() {
		return paidAt;
	}

	public void setPaidAt(Date paidAt) {
		this.paidAt = paidAt;
	}

	public int getCanceled() {
		return canceled;
	}

	public void setCanceled(int canceled) {
		this.canceled = canceled;
	}

	public Date getCanceledAt() {
		return canceledAt;
	}

	public void setCanceledAt(Date canceledAt) {
		this.canceledAt = canceledAt;
	}

	public int getTypeDelivery() {
		return typeDelivery;
	}

	public void setTypeDelivery(int typeDelivery) {
		this.typeDelivery = typeDelivery;
	}

	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Order order = (Order) o;
		return id.equals(order.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}