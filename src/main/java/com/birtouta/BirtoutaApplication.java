package com.birtouta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.birtouta.BirtoutaApplication;
import com.birtouta.dao.ConfigurationRepository;
import com.birtouta.dao.SessionRepository;
import com.birtouta.dao.UserRepository;
import com.birtouta.entities.Configuration;
import com.birtouta.entities.Session;
import com.birtouta.entities.User;
import com.birtouta.services.*;
@SpringBootApplication
public class BirtoutaApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SessionRepository sessionRepository; 
	
	@Autowired
	private ConfigurationRepository configurationRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BirtoutaApplication.class, args);
	}
	
	@Override 
	public void run(String... arg0) throws Exception{
				
		User oussama = userRepository.save(new User("0540520325", "rootinfo", "Birtouta", "Alger", "boumaadoussama@gmail.com", 
				"Oussama", "Boumaad", "Rue flan ben flan", "35.6.25.252525", "26.26.25362.2"));
		userRepository.save(new User("0556063697", "rootinfo", "Birtouta", "Alger", "boumaadoussama@gmail.com", 
				"Abderrahmene", "Bendaoued", "Rue flan ben flan", "35.6.25.252525", "26.26.25362.2"));
		userRepository.save(new User("0550452604", "rootinfo", "Birtouta", "Alger", "boumaadoussama@gmail.com", 
				"Bilal", "Boumaad", "Rue flan ben flan", "35.6.25.252525", "26.26.25362.2"));

		
		Session s = new Session();
		s.setToken(Metier.generateTokenHash());
		s.setBuild("2.0135");
		s.setPlatform("android");
		s.setSmartphone("Redmi note 7");
		s.setFcm_token("qlkdsjfmqlkjfmqlkdfqs5d1f3q1f6q5dsf");
		s.setUser(oussama);
		
		sessionRepository.save(s);
		
		Configuration  configuration= new Configuration(); 
		configuration.setLastVersion("2.1.1");
		configuration.setUser(oussama);
		
		configurationRepository.save(configuration); 
		
		
		
		
		userRepository.findAll().forEach(user -> {
			System.out.println(user.getPhone() +" => " + user.getFirstName() +" => " + user.getLastName() );
		});
	}

}
