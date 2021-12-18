package com.tdd.SpringBootRestService.controller;

import com.tdd.SpringBootRestService.service.LibraryService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class LibraryController {

  private final LibraryService libraryService;

  @GetMapping("/getBook/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public Library getBookById(@PathVariable(value = "id") String id) {
    Optional<Library> book = libraryService.findById(id);
    if (book.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return book.get();
  }

  @GetMapping("getBooks/author")
  @ResponseStatus(code = HttpStatus.OK)
  public List<Library> getBookByAuthorName(@RequestParam(value = "authorname") String authorName) {
    return libraryService.findAllByAuthor(authorName);
  }

  @PostMapping("/addBook")
  public ResponseEntity<AddResponse> addBookImplementation(@RequestBody Library library) {
    String bookId = libraryService.buildId(library.getIsbn(), library.getAisle());
    AddResponse addRes = new AddResponse();

    if (libraryService.checkBookAlreadyExist(bookId)) {
      addRes.setMsg("Book Already Exist");
      return new ResponseEntity<>(addRes, HttpStatus.ACCEPTED);
    }

    library.setId(bookId);
    String saveId = libraryService.save(library);

    addRes.setId(saveId);
    addRes.setMsg("success");
    return new ResponseEntity<>(addRes, HttpStatus.CREATED);
  }

  @PutMapping("updateBook/{id}")
  public ResponseEntity<Library> updateBook(
      @PathVariable(value = "id") String id, @RequestBody Library library) {
    Optional<Library> byId = libraryService.findById(id);
    if (byId.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    Library book = byId.get();
    book.setAisle(library.getAisle());
    book.setAuthor(library.getAuthor());
    book.setBook_name(library.getBook_name());

    libraryService.save(book);
    return new ResponseEntity<Library>(book, HttpStatus.OK);
  }
}
