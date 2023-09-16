package com.book.nest;

import com.book.nest.model.Book;
import com.book.nest.service.BookService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookNestApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookNestApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Book thinkingInJava = new Book();
                thinkingInJava.setTitle("Thinking In Java");
                thinkingInJava.setAuthor("Bruce Eckel");
                thinkingInJava.setIsbn("9780130273635");
                thinkingInJava.setPrice(BigDecimal.valueOf(170));
                thinkingInJava.setDescription("Thinking in Java is a book about the Java");
                thinkingInJava.setCoverImage("Image");
                Book effectiveJava = new Book();
                effectiveJava.setTitle("Effective Java");
                effectiveJava.setAuthor("Joshua Bloch");
                effectiveJava.setIsbn("9780137150021");
                effectiveJava.setPrice(BigDecimal.valueOf(200));
                effectiveJava.setDescription("The Definitive Guide to Java Platform");
                effectiveJava.setCoverImage("Image");
                bookService.save(thinkingInJava);
                bookService.save(effectiveJava);
                System.out.println(bookService.findAll());
            }
        };
    }
}
