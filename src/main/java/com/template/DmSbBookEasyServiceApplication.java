package com.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ="com.template")
public class DmSbBookEasyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmSbBookEasyServiceApplication.class, args);
	}

}
