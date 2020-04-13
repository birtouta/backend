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
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository; 
	
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
		
		Store store1 = new Store();
		store1.setName("Superette Akacha");
		store1.setLatitude("36.647139");
		store1.setLongitude("2.994101");
		storeRepository.save(store1);
		
		Store store2 = new Store();
		store2.setName("Superette Ihamouine");
		store2.setLatitude("36.643592");
		store2.setLongitude("2.993837");
		storeRepository.save(store2);
		
		Store store3 = new Store();
		store3.setName("Superette Zaidi");
		store3.setLatitude("36.643592");
		store3.setLongitude("2.993837");
		storeRepository.save(store3);

		Store store4 = new Store();
		store4.setName("Superette DOUAA");
		store4.setLatitude("36.642288");
		store4.setLongitude("2.991084");
		storeRepository.save(store4);

		Store store5 = new Store();
		store5.setName("Superette Walid");
		store5.setLatitude("36.648629");
		store5.setLongitude("2.985279");
		storeRepository.save(store5);	
		
		ProductCategory pc = new ProductCategory(); 
		pc.setName("pâtes alimentaires"); 
		productCategoryRepository.save(pc); 
		ProductCategory pc1 = new ProductCategory(); 
		pc1.setName("Les conserves"); 
		productCategoryRepository.save(pc1);


		ProductCategory pc2 = new ProductCategory(); 
		pc2.setName("Jus et Boissons Gazeuses"); 
		productCategoryRepository.save(pc2);


		ProductCategory pc3 = new ProductCategory(); 
		pc3.setName("Biscui et gâteaux"); 
		productCategoryRepository.save(pc3);

		ProductCategory pc4 = new ProductCategory(); 
		pc4.setName("Détergents"); 
		productCategoryRepository.save(pc4);

		ProductCategory pc5 = new ProductCategory(); 
		pc5.setName("Fruits et Légumes"); 
		productCategoryRepository.save(pc5);

		ProductCategory pc6 = new ProductCategory(); 
		pc6.setName("Autres"); 
		productCategoryRepository.save(pc6);	
		
		
		
		userRepository.findAll().forEach(user -> {
			System.out.println(user.getPhone() +" => " + user.getFirstName() +" => " + user.getLastName() );
		});
	}

}
