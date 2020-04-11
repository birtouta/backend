package com.birtouta.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="sessions")
public class Session implements Serializable{


	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	private String token; 
	private String platform; 
	private String build; 
	private String fcm_token; 
	private String smartphone;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
	private Date createdAt;  
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "upadated_at")
	private Date updatedAt; 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginAt; 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date logoutAt; 
	
	
	@ManyToOne
    @JoinColumn(name="id_user")
	@JsonBackReference
    private User user;

	public Session() {}
	


	public Session(String platform, String build, String fcm_token, String smartphone) {
		super();
		this.platform = platform;
		this.build = build;
		this.fcm_token = fcm_token;
		this.smartphone = smartphone;
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getPlatform() {
		return platform;
	}


	public void setPlatform(String platform) {
		this.platform = platform;
	}


	public String getBuild() {
		return build;
	}


	public void setBuild(String build) {
		this.build = build;
	}


	public String getFcm_token() {
		return fcm_token;
	}


	public void setFcm_token(String fcm_token) {
		this.fcm_token = fcm_token;
	}


	public String getSmartphone() {
		return smartphone;
	}


	public void setSmartphone(String smartphone) {
		this.smartphone = smartphone;
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



	public Date getLogoutAt() {
		return logoutAt;
	}



	public void setLogoutAt(Date logoutAt) {
		this.logoutAt = logoutAt;
	}



	public Date getLoginAt() {
		return loginAt;
	}


	public void setLoginAt(Date loginAt) {
		this.loginAt = loginAt;
	}


	public Date getLougoutAt() {
		return logoutAt;
	}


	public void setLougoutAt(Date logoutAt) {
		this.logoutAt = logoutAt;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
}
