package com.birtouta.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

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
	private Date created_at; 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_at; 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date login_at; 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date logout_at; 
	
	
	@ManyToOne
    @JoinColumn(name="id_user", referencedColumnName = "id" ,nullable=false)
    private User user;
}
