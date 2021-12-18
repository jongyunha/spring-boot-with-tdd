package com.tdd.SpringBootRestService.service;

import com.tdd.SpringBootRestService.controller.Library;
import com.tdd.SpringBootRestService.repository.LibraryRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LibraryService {

  LibraryRepository libraryRepository;

  @Autowired
  public LibraryService(LibraryRepository libraryRepository) {
    this.libraryRepository = libraryRepository;
  }

  public String buildId(String isbn, int aisle) {
    return isbn + aisle;
  }

  public String save(Library library) {
    Library saveBook = libraryRepository.save(library);
    return saveBook.getId();
  }

  @Transactional(readOnly = true)
  public boolean checkBookAlreadyExist(String bookId) {
    Optional<Library> book = libraryRepository.findById(bookId);
    return book.isPresent();
  }
}
