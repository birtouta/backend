package com.birtouta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.birtouta.dao.SessionRepository;
import com.birtouta.dao.UserRepository;
import com.birtouta.entities.Session;
import com.birtouta.entities.User;
import com.birtouta.services.Metier;

@RestController
@RequestMapping(path="/auth") 
public class LogoutRestController {
	
	@Autowired
	private SessionRepository sessionRepository; 
	
	@PostMapping(path="/logout")
	public ResponseEntity<?>  logOut(@RequestHeader("Token") String token) {
		Session session = sessionRepository.findByToken(token);
		
		if(session == null) {
			// check if the token exists
			
			return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
		}else {
			// update updateAt and logoutAt attributes and return logout status ok 
			
			session.setUpdatedAt(Metier.getCurrentTimestamp());
			session.setLogoutAt(Metier.getCurrentTimestamp());
			return new ResponseEntity<String>("Logout successfully !", HttpStatus.OK);
		}
	
	}
}
