package com.birtouta.entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.*;

import lombok.Data;


@SuppressWarnings("serial")
@Entity
@Table(name="users")
@JsonIgnoreProperties({"configuration","sessions", "store", "orders", "delivery", "deliveries", "storeWorker"})
@Data
public class User implements Serializable {
	
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id; 
	
	@Column(name="phone", nullable = false)	
	private String phone; 
	
	@Column(name="password", nullable = false)
	private String password; 
	
	// editable after login ... profile
	private int city; 
	private int state; 
	private String email; 
	private String firstName; 
	private String lastName; 
	private String address; 
	private Double latitude;
	private Double longitude; 
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
	private Date createdAt; 
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at") 
	private Date updatedAt; 
	
	private String pin; 
	private int deleted ;
	
	@OneToOne(mappedBy="user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Configuration configuration;     

	@OneToMany(mappedBy="user" ,fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Session> sessions;
	
	@OneToOne(mappedBy="user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Store store; 
	
	@OneToMany(mappedBy="user" ,fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Order> orders;
	
	@OneToMany(mappedBy="userDeliver", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Delivery> delivery; 
	
	@OneToMany(mappedBy="userTarget" ,fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Delivery> deliveries;
	
	@OneToOne(mappedBy="user" ,cascade = CascadeType.ALL)
	@JsonManagedReference
	private StoreWorker storeWorker;
	
	
	public User () {};
	
	public User(String phone, String password, int city, int state, String email, String first_name,
			String last_name, String address, Double latitude, Double longitude) {
		super();
		this.phone = phone;
		this.password = password;
		this.city = city;
		this.state = state;
		this.email = email;
		this.firstName = first_name;
		this.lastName= last_name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}		
}
