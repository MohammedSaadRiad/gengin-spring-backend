package com.marsamaroc.gengin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan(basePackages = {"com.marsamaroc.gengin.models"})
public class GestionDesEnginApplication {

	public static void main(String[] args) {

		SpringApplication.run(GestionDesEnginApplication.class, args);

	}}


