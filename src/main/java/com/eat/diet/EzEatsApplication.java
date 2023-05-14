package com.eat.diet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableMongoRepositories(basePackages = {"com.eat.diet.repo"})
@EntityScan(basePackages = {"com.eat.diet.repo.model"} )
//@EnableJpaRepositories(basePackages = {"com.eat.diet.repo"})
public class EzEatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EzEatsApplication.class, args);
	}

}
