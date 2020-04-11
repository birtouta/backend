package com.birtouta.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@SuppressWarnings("serial")
@Entity
@Table(name="users")

public class User implements Serializable {
	

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id; 
	
	@Column(name="phone", nullable = false)	
	private String phone; 
	
	@Column(name="password", nullable = false)
	private String password; 
	
	// editable after login ... profile
	private String city; 
	private String state; 
	private String email; 
	private String firstName; 
	private String lastName; 
	private String address; 
	private String latitude;
	private String longitude; 
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
	private Date createdAt; 
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at") 
	private Date updatedAt; 
	
	private String pin; 
	private int deteled ;
	
	
	
	@OneToOne(mappedBy="user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Configuration configuration; 
    

	@OneToMany(mappedBy="user" ,cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Session> sessions;

	
	public User () {};
	
	public User(String phone, String password, String city, String state, String email, String first_name,
			String last_name, String address, String latitude, String longitude) {
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
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreated_at(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdated_at(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public int getDeteled() {
		return deteled;
	}
	public void setDeteled(int deteled) {
		this.deteled = deteled;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public Set<Session> getSessions() {
		return sessions;
	}

	public void setSessions(Set<Session> sessions) {
		this.sessions = sessions;
	}
	

		
}
