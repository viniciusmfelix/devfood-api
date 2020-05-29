package com.devfood.devfoodapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.devfood.devfoodapi.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class DevfoodApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DevfoodApiApplication.class, args);
	}

}
