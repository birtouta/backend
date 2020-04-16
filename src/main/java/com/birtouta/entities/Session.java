package com.birtouta.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name="sessions")
@Data
public class Session implements Serializable{


	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	private String token; 
	private String platform; 
	private int build; 
	private String fcm_token; 
	
	@Column(columnDefinition = "TEXT")
	private String smartphone;
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
	private Timestamp createdAt;  
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "upadated_at")
	private Timestamp updatedAt; 
	
//	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp loginAt; 
	
//	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp logoutAt; 
	
	
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
