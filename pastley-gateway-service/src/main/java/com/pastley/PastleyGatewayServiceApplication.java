package com.pastley;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @project Pastley-Sale.
 * @author Sergio Stives Barrios Buitrago.
 * @Github https://github.com/SerBuitrago.
 * @contributors soleimygomez, leynerjoseoa, jhonatanbeltran.
 * @version 1.0.0.
 */
@SpringBootApplication
@EnableEurekaClient
public class PastleyGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PastleyGatewayServiceApplication.class, args);
	}

}
