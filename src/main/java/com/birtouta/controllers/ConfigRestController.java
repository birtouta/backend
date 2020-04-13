package com.birtouta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.birtouta.dao.ConfigurationRepository;
import com.birtouta.dao.SessionRepository;
import com.birtouta.entities.Configuration;
import com.birtouta.entities.Session;
import com.birtouta.entities.User;

@RestController
@RequestMapping(path="/config")
public class ConfigRestController {
	@Autowired
	ConfigurationRepository configurationRepository; 
	
	@Autowired
	SessionRepository sessionRepository ;
	
	@PostMapping(path="/add")
	public ResponseEntity<?> addConfig (@RequestBody Configuration configuration){
		return new ResponseEntity<Configuration>(configurationRepository.save(configuration), HttpStatus.OK); 
	}
	
	@PostMapping(path="/get")
	public ResponseEntity<?> getConfig (@RequestHeader("Token") String token){
		Session session = sessionRepository.findByToken(token);
		if(session == null) {
			return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
		}else {
			
			User user = session.getUser() ;
			Configuration  configuration = user.getConfiguration(); 
			
			return new ResponseEntity<Configuration>(configuration, HttpStatus.OK);
		}
	}
	
}
