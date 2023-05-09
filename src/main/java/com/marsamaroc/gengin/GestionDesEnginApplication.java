package com.marsamaroc.gengin;

import com.marsamaroc.gengin.models.Utilisateur;
import com.marsamaroc.gengin.repositories.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.config.RepositoryConfiguration;
import org.springframework.stereotype.Repository;

@SpringBootApplication
public class GestionDesEnginApplication {

	public static void main(String[] args) {

		SpringApplication.run(GestionDesEnginApplication.class, args);
	}

}
