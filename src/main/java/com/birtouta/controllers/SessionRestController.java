package com.birtouta.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.birtouta.dao.SessionRepository;
import com.birtouta.entities.Session;
import com.birtouta.services.Metier;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



@RestController
@RequestMapping(path="/session", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE) 
public class SessionRestController {
	
	@Autowired
	private SessionRepository sessionRepository; 
	
	
	@PostMapping(path="/add")
	public @ResponseBody Session addSession(@RequestBody Session session) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		String hash  = Metier.generateTokenHash();		
		session.setToken(hash);
		return sessionRepository.save(session);
	}
	
	@PostMapping(path="/update")
	public ResponseEntity<?>  updateSession(@RequestBody Session session,  @RequestHeader("Token") String token) {
		
		Session s = sessionRepository.findByToken(token);
		if(s == null) {
			return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
		}else {		
			s.setBuild(session.getBuild());
			s.setSmartphone(session.getSmartphone());	
			s.setUpdatedAt(Metier.getCurrentTimestamp());
			
			sessionRepository.save(s);
			return ResponseEntity.ok("The session has been successfully  updated");
		}
	}
	
	@PostMapping(path="/delete")
	public ResponseEntity<?>  deleteSession(@RequestHeader("Token") String token) {
		
		Session s = sessionRepository.findByToken(token);
		if(s == null) {
			
			return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
		}else {
			
			sessionRepository.delete(s);
			return ResponseEntity.ok("The session has been successfully  deleted");
		}
	}
	
	@PostMapping(path="/all")
	public Iterable<Session> GetAllSession(){
		return sessionRepository.findAll();
	}
	
}
