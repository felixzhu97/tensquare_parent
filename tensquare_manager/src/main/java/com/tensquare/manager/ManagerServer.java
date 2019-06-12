package com.tensquare.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;
import org.springframework.context.annotation.Bean;
import util.JwtUtil;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ManagerServer {

	public static void main(String[] args) {

		SpringApplication.run(ManagerServer.class,args);
	}

	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil();
	}
}
