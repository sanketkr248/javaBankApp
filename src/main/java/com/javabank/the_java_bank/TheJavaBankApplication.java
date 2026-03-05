package com.javabank.the_java_bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "The Java Bank App",
				description = "Backend Rest APIs for Java Bank",
				version = "v1.0",
				contact = @Contact(
						name = "Sanket Kumar",
						email = "sanketkr248@gmail.com",
						url = "https://github.com/sanketkr248/javaBankApp"
				),
				license = @License(
						name = "The Java Bank",
						url = "https://github.com/sanketkr248/javaBankApp" 
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "The Java Bank App Documentation",
				url = "https://github.com/sanketkr248/javaBankApp"
				)
		)

public class TheJavaBankApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(TheJavaBankApplication.class, args);
		
		System.out.println("Started...!");
	}

}
