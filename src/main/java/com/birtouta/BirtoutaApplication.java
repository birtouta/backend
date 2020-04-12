package com.birtouta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.birtouta.BirtoutaApplication;
import com.birtouta.dao.*;
import com.birtouta.entities.*;
import com.birtouta.services.*;
@SpringBootApplication
public class BirtoutaApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SessionRepository sessionRepository; 
	
	@Autowired
	private ConfigurationRepository configurationRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BirtoutaApplication.class, args);
	}
	
	@Override 
	public void run(String... arg0) throws Exception{
				
		User oussama = userRepository.save(new User("0540520325", "rootinfo", "Birtouta", "Alger", "boumaadoussama@gmail.com", 
				"Oussama", "Boumaad", "Rue flan ben flan", "35.6.25.252525", "26.26.25362.2"));
		User dahman = userRepository.save(new User("0556063697", "rootinfo", "Birtouta", "Alger", "boumaadoussama@gmail.com", 
				"Abderrahmene", "Bendaoued", "Rue flan ben flan", "35.6.25.252525", "26.26.25362.2"));
		userRepository.save(new User("0550452604", "rootinfo", "Birtouta", "Alger", "boumaadoussama@gmail.com", 
				"Bilal", "Boumaad", "Rue flan ben flan", "35.6.25.252525", "26.26.25362.2"));

		
		Session s = new Session();
		s.setBuild("2.0135");
		s.setPlatform("android");
		s.setSmartphone("Redmi note 7");
		s.setFcm_token("qlkdsjfmqlkjfmqlkdfqs5d1f3q1f6q5dsf");
		s.setToken("ED9CEFBCCB0D0CE41084009FAE7F2D363439F6224DDADBCDB0E509646E593F13");
		s.setUser(oussama);
		sessionRepository.save(s);
		
		Session s2 = new Session();
		s2.setBuild("2.0135");
		s2.setPlatform("android");
		s2.setSmartphone("Redmi note 7");
		s2.setFcm_token("qlkdsjfmqlkjfmqlkdfqs5d1f3q1f6q5dsf");
		s2.setToken(Metier.generateTokenHash());
		s2.setUser(oussama);
		sessionRepository.save(s2);
		
		Configuration  configuration= new Configuration(); 
		configuration.setLastVersion("2.1.1");
		configuration.setUser(oussama);
		configurationRepository.save(configuration); 		
		
		Store store = new Store();
		store.setUser(dahman);
		storeRepository.save(store);
		
		
		userRepository.findAll().forEach(user -> {
			System.out.println(user.getPhone() +" => " + user.getFirstName() +" => " + user.getLastName() );
		});
	}

}
