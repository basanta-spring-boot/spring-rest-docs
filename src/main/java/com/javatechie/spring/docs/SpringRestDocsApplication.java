package com.javatechie.spring.docs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
public class SpringRestDocsApplication {


    @GetMapping("/")
    public String getMessage() {
        return "Welcome to java techie";
    }

    @GetMapping(value = "/books",produces = {"application/json"})
    public ResponseEntity<?> getBooks() {
        return ResponseEntity.ok(Stream.of(new Book(101, "core java", 500), new Book(102, "spring boot", 800)).collect(Collectors.toList()));
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringRestDocsApplication.class, args);
    }

}
