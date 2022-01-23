package com.bookstore.app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching(proxyTargetClass = true)
@OpenAPIDefinition(info = @Info(title = "bookstoreapi",version = "2.0"))
public class BookStoreBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreBackEndApplication.class, args);
	System.out.println("BookStore is Running...!");
	}

}
