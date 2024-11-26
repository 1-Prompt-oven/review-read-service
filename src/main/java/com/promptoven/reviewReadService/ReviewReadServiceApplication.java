package com.promptoven.reviewReadService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class ReviewReadServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewReadServiceApplication.class, args);
	}

}
