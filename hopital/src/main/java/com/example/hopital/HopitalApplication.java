package com.example.hopital;

import com.example.hopital.entities.Patient;
import com.example.hopital.repository.PatientRepository;
import com.example.hopital.security.service.AccountService;
import com.example.hopital.security.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;


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
	//@Bean
	CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
		PasswordEncoder passwordEncoder=passwordEncoder();
		return args -> {
			UserDetails u1=jdbcUserDetailsManager.loadUserByUsername("user12");
			if(u1==null)
			jdbcUserDetailsManager.createUser(
					User.withUsername("user12").password(passwordEncoder.encode("1234")).roles("USER").build());
			UserDetails u2=jdbcUserDetailsManager.loadUserByUsername("zineb");
			if(u2==null)
			jdbcUserDetailsManager.createUser(
					User.withUsername("zineb").password(passwordEncoder.encode("1234")).roles("USER").build());
			UserDetails u3=jdbcUserDetailsManager.loadUserByUsername("admin2");
			if(u3==null)
			jdbcUserDetailsManager.createUser(
					User.withUsername("admin2").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build());
		};
	}
	@Bean
	CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
		return args -> {


			accountService.addNewRole("USER");
			accountService.addNewRole("ADMIN");

			accountService.addNewUser("user4","1234","user4@gmail.com","1234");
			accountService.addNewUser("amine","1234","amine@gmail.com","1234");
			accountService.addNewUser("admin","1234","admin@gmail.com","1234");
			accountService.addRoleToUser("user4","USER");
			accountService.addRoleToUser("admin","USER");
			accountService.addRoleToUser("amine","USER");
			accountService.addRoleToUser("admin","ADMIN");


		};
	}
 @Bean
PasswordEncoder passwordEncoder(){
	return new BCryptPasswordEncoder();
}
}
