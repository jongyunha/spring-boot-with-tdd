package com.tdd.SpringBootRestService.controller;

import com.tdd.SpringBootRestService.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {

  private final LibraryRepository libraryRepository;

  @Autowired
  public LibraryController(LibraryRepository libraryRepository) {
    this.libraryRepository = libraryRepository;
  }

  @PostMapping("/addBook")
  public void addBookImplementation(@RequestBody Library library) {
    library.setId(library.getIsbn() + library.getAisle());
    libraryRepository.save(library);
    // add book details into database
  }
}
