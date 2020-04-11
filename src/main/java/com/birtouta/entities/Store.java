package com.birtouta.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@SuppressWarnings("serial")
@Entity
@Table(name="stores")
public class Store implements Serializable {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	private String name; 
	private String photo; 
	
	private String qr_code; 
	private String latitude;
	private String longitude; 
	
	private String type;
	private int deleted;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
	private Date createdAt; 
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at") 
	private Date updatedAt; 
	
	
	@OneToOne
    @JoinColumn(name="id_owner")
	@JsonBackReference
    private User user;
	
	
	@OneToMany(mappedBy="store", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Delivery> deliveries; 
	
	@OneToMany(mappedBy="store", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<StoreWorker> storeWorkers; 
	
	@OneToMany(mappedBy="store", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Order> orders; 
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getQr_code() {
		return qr_code;
	}

	public void setQr_code(String qr_code) {
		this.qr_code = qr_code;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public List<StoreWorker> getStoreWorkers() {
		return storeWorkers;
	}

	public void setStoreWorkers(List<StoreWorker> storeWorkers) {
		this.storeWorkers = storeWorkers;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	
}
