package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PoliceServer {

	public static void main(String[] args) {
		new SpringApplicationBuilder(PoliceServer.class).web(true).run(args);
	}

}
