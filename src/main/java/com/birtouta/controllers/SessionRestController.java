package com.birtouta.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.birtouta.dao.SessionRepository;
import com.birtouta.entities.Session;
import com.birtouta.services.Metier;
import com.birtouta.services.Response;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



@RestController
@RequestMapping(path="/session", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE) 
public class SessionRestController {
	
	@Autowired
	private SessionRepository sessionRepository; 	
	
	@PostMapping(path="/add")
	public ResponseEntity<?> addSession(Session session) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		String hash  = Metier.generateTokenHash();		
		session.setToken(hash);
		 return new ResponseEntity<Response>(new Response("Session created successfully !", session,  200, true), HttpStatus.OK);
	}
	
	@PostMapping(path="/update")
	public ResponseEntity<?>  updateSession(Session session,  @RequestHeader("Token") String token) {
		
		Session s = sessionRepository.findByToken(token);
		if(s == null) {
			 return new ResponseEntity<Response>(new Response("Unauthorized Token", null,  401, false), HttpStatus.UNAUTHORIZED);
		}else {		
			s.setBuild(session.getBuild());
			s.setSmartphone(session.getSmartphone());	
			s.setUpdatedAt(Metier.getCurrentTimestamp());			
			sessionRepository.save(s);
			
			return new ResponseEntity<Response>(new Response("Session successfully  updated !", s,  200, true), HttpStatus.OK);
		}
	}
	
	@PostMapping(path="/delete", consumes = {MediaType.ALL_VALUE})
	public ResponseEntity<?>  deleteSession(@RequestHeader("Token") String token) {
		
		Session s = sessionRepository.findByToken(token);
		if(s == null) {
			 return new ResponseEntity<Response>(new Response("Unauthorized Token", null,  401, false), HttpStatus.UNAUTHORIZED);
		}else {
			sessionRepository.delete(s);
			return new ResponseEntity<Response>(new Response("Session successfully destroyed !", null,  200, true), HttpStatus.OK);
		}
	}
	
	@PostMapping(path="/all",consumes = {MediaType.ALL_VALUE})
	public ResponseEntity<?> GetAllSession(@RequestHeader("Token") String token){
		Session s = sessionRepository.findByToken(token);
		if(s == null) {
			 return new ResponseEntity<Response>(new Response("Unauthorized Token", null,  401, false), HttpStatus.UNAUTHORIZED);
		}else {
			return new ResponseEntity<Response> (new Response("Sessions successfully retreived !", s.getUser().getSessions(),  200, false), HttpStatus.OK );
		}
	}
	
}
