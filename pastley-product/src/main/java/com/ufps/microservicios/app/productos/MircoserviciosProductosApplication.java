package com.ufps.microservicios.app.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class MircoserviciosProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MircoserviciosProductosApplication.class, args);
	}

}
