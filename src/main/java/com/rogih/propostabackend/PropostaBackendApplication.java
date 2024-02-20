package com.rogih.propostabackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PropostaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaBackendApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(){
//		return args -> {
//			System.out.println("Serviço proposta-backend em execução!");
//		};
//
//	}

}
