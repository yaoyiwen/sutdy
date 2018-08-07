package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PersonServer {

	public static void main(String[] args) {
		new SpringApplicationBuilder(PersonServer.class).web(true).run(args);
	}

}
