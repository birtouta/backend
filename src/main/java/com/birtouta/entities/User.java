package com.birtouta.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="users")
public class User implements Serializable {
	

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id; 
	
	private String phone; 
	private String password; 
	private String city; 
	private String state; 
	private String email; 
	private String first_name; 
	private String last_name; 
	private String address; 
	private String latitude;
	private String longitude; 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at; 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_at; 
	
	private String pin; 
	private int deteled ;
	
	protected User () {};
	
	public User(String phone, String password, String city, String state, String email, String first_name,
			String last_name, String address, String latitude, String longitude, Date created_at) {
		super();
		this.phone = phone;
		this.password = password;
		this.city = city;
		this.state = state;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.created_at = created_at;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhne() {
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
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
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
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
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
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Configuration configuration;
	
	
	
	
	
}
