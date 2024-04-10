package com.rogih.propostabackend;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import static com.rogih.propostabackend.config.AppVersion.getAppVersion;

@Log4j2
@SpringBootApplication
@EnableScheduling
public class PropostaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaBackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(){
		return args -> {
			String appVersion = getAppVersion();
			if (appVersion != null) {
				log.info("proposta-backend v" + appVersion);
			}
			log.info("Serviço proposta-backend em execução!");
		};

	}

}
