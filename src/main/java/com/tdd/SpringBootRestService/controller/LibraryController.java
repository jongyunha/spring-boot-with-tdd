package com.tdd.SpringBootRestService.controller;

import com.tdd.SpringBootRestService.repository.LibraryRepository;
import com.tdd.SpringBootRestService.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LibraryController {

  private final LibraryRepository libraryRepository;
  private final LibraryService libraryService;

  @PostMapping("/addBook")
  public ResponseEntity<AddResponse> addBookImplementation(@RequestBody Library library) {
    String bookId = libraryService.buildId(library.getIsbn(), library.getAisle());
    AddResponse addRes = new AddResponse();

    if (libraryService.checkBookAlreadyExist(bookId)) {
      addRes.setMsg("Book Already Exist");
      return new ResponseEntity<>(addRes, HttpStatus.ACCEPTED);
    }
    library.setId(bookId);
    libraryRepository.save(library);

    addRes.setId(library.getId());
    addRes.setMsg("success");
    return new ResponseEntity<>(addRes, HttpStatus.CREATED);
  }
}
