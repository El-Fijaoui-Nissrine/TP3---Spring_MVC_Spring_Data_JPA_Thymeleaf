package com.example.hopital;

import com.example.hopital.entities.Patient;
import com.example.hopital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Date;

@SpringBootApplication(scanBasePackages = "com.example.hopital")

public class HopitalApplication  implements CommandLineRunner {
	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(HopitalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//patientRepository.save(new Patient(null,"ali",new Date(),false,22));
		//patientRepository.save(new Patient(null,"amine",new Date(),false,30));
        //patientRepository.save(new Patient(null,"najat",new Date(),true,10));

	}
 @Bean
PasswordEncoder passwordEncoder(){
	return new BCryptPasswordEncoder();
}
}
