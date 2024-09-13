package edu.escuelaing.PayNStay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class PayNStayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayNStayApplication.class, args);
	}

}
