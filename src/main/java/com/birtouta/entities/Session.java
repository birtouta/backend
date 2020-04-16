package com.birtouta.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name="sessions")
@Data
public class Session implements Serializable{


	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private String token; 
	private String platform; 
	private int build; 
	private String fcm_token; 
	
	@Column(columnDefinition = "TEXT")
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

	public Session(String platform, int build, String fcm_token, String smartphone) {
		super();
		this.platform = platform;
		this.build = build;
		this.fcm_token = fcm_token;
		this.smartphone = smartphone;
	}

}
