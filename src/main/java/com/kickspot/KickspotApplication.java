package com.kickspot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kickspot.model.Role;
import com.kickspot.repository.RoleRepository;

@SpringBootApplication
//@EnableConfigServer
public class KickspotApplication {

	@Autowired
	private RoleRepository roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(KickspotApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return runner -> {
			Role role1 = new Role("ROLE_ADMIN");

			roleRepo.save(role1);

			Role role2 = new Role("ROLE_USER");

			roleRepo.save(role2);

			Role role3 = new Role("ROLE_OWNER");

			roleRepo.save(role3);
		};
	}

}
