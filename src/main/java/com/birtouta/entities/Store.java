package com.birtouta.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Table(name="stores")
@JsonIgnoreProperties({"user","deliveries", "storeWorkers","orders"})
@Data
public class Store implements Serializable {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	private String name; 
	private String photo; 
	
	private String qr_code; 
	private Double latitude;
	private Double longitude; 
	
	private String type;
	private int deleted;
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
	private Timestamp createdAt; 
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at") 
	private Timestamp updatedAt; 
	
	
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
	
}
