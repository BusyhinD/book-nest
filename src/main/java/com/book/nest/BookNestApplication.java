package com.book.nest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "The Best Wine Shop",
                version = "1.0.0",
                description = "Documentation for API Best World Wines shop.",
                contact = @Contact(
                        name = "Dmytro Busyhin",
                        email = "busyhin.d@gmail.com",
                        url = "https://github.com/BusyhinD/book-nest"),
                license = @License(name = "License @2023", url = "")
        )
)
public class BookNestApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookNestApplication.class, args);
    }
}
