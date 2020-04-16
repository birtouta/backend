package com.birtouta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.birtouta.dao.SessionRepository;
import com.birtouta.dao.UserRepository;
import com.birtouta.entities.Session;
import com.birtouta.entities.User;
import com.birtouta.services.Metier;
import com.birtouta.services.Response;

@RestController
@RequestMapping(path="/auth",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE) 
public class AuthRestController {

	@Autowired
	private SessionRepository sessionRepository; 
	
	@Autowired
	private UserRepository userRepository; 
	
	@PostMapping(path="/login")
	public ResponseEntity<?>  logIn(@RequestParam String phone, @RequestParam String password, @RequestHeader("Token") String token) {
		
		Session session = sessionRepository.findByToken(token);
		if(session == null) {
			// check the token if it exist
			return new ResponseEntity<Response>(new Response("Unauthorized Token", null,  401, false), HttpStatus.UNAUTHORIZED);
		}else {
			
			// check the user phone number if it's registered 
			User user = userRepository.findByPhone(phone);
			
			if (user == null) {
				return new ResponseEntity<Response>(new Response("Phone number does not exist", null, 404,false), HttpStatus.NOT_FOUND);

			}else {
				if (user.getPassword().equals(password)) {

					session.setLoginAt(Metier.getCurrentTimestamp());
					session.setUpdatedAt(Metier.getCurrentTimestamp());

					sessionRepository.save(session);
					return new ResponseEntity<Response>(new Response("Login Successfully !", user, 200,true), HttpStatus.OK);

				}else {
					return new ResponseEntity<Response>(new Response("User and password does not match", null, 404,false), HttpStatus.UNAUTHORIZED);
				}
			}
			
		}
	}
	
	@PostMapping(path="/logout" ,consumes = {MediaType.ALL_VALUE})
	public ResponseEntity<?>  logOut(@RequestHeader("Token") String token) {
		Session session = sessionRepository.findByToken(token);
		
		if(session == null) {
			// check if the token exists
			
			 return new ResponseEntity<Response>(new Response("Unauthorized Token", null,  401, false), HttpStatus.UNAUTHORIZED);
		}else {
			// update updateAt and logoutAt attributes and return logout status ok 
			
			session.setUpdatedAt(Metier.getCurrentTimestamp());
			session.setLogoutAt(Metier.getCurrentTimestamp());
			 return new ResponseEntity<Response>(new Response("Logout successfully !", null,  200, true), HttpStatus.OK);
		}
	
	}
	
	
	@PostMapping(path="/signup")
	public ResponseEntity<?>  signIn( User user ,@RequestHeader("Token") String token) {
		Session session = sessionRepository.findByToken(token);
		
		if(session == null) {
			// check if the token exists
			 return new ResponseEntity<Response>(new Response("Unauthorized Token", null,  401, false), HttpStatus.UNAUTHORIZED);
		}else {
			//check if the user exists already!
			if (userRepository.findByPhone(user.getPhone()) == null) {
				User u = new User();
				u = userRepository.save(user); 
				session.setUser(u);
				 return new ResponseEntity<Response>(new Response("Registred successfully !", u,  200, true), HttpStatus.OK);

			}else {
				 return new ResponseEntity<Response>(new Response("User's phone number exists in the system, try another phone number !", null,  401, false), HttpStatus.UNAUTHORIZED);
			}
		}
	}
}
