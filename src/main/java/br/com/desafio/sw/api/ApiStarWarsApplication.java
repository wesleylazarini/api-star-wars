package br.com.desafio.sw.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableCaching
@EnableFeignClients
@EnableMongoRepositories
public class ApiStarWarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiStarWarsApplication.class, args);
	}

}
