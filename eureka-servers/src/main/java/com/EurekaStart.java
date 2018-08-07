package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaStart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SpringApplicationBuilder(EurekaStart.class).web(true).run(args);
	}

}
