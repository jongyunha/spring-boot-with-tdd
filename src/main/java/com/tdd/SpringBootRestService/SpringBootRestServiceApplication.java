package com.tdd.SpringBootRestService;

import com.tdd.SpringBootRestService.controller.Library;
import com.tdd.SpringBootRestService.repository.LibraryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRestServiceApplication implements CommandLineRunner {

  @Autowired public LibraryRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(SpringBootRestServiceApplication.class, args);
  }

  @Override
  public void run(String[] args) {
    Library lib = repository.findById("fdsefr343").get();
    System.out.println(lib.getAuthor());
  }
}
