package com.pastley;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PastleyProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PastleyProductsApplication.class, args);
	}

}
