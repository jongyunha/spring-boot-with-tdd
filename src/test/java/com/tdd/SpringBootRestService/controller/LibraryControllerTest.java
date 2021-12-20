package com.tdd.SpringBootRestService.controller;

import static org.mockito.Mockito.when;

import com.tdd.SpringBootRestService.repository.LibraryRepository;
import com.tdd.SpringBootRestService.service.LibraryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class LibraryControllerTest {

  @Autowired LibraryController libraryController;
  @MockBean LibraryRepository libraryRepository;
  @MockBean LibraryService libraryService;

  private Library library;

  @BeforeEach
  public void buildLibrary() {
    library = new Library();
    library.setAisle(322);
    library.setBook_name("Spring");
    library.setIsbn("sfe");
    library.setAuthor("jongyunha");
    library.setId("sfe322");
  }

  @Test
  public void addBookTest() {
    when(libraryService.buildId(library.getIsbn(), library.getAisle())).thenReturn(library.getId());
    when(libraryService.checkBookAlreadyExist(library.getId())).thenReturn(true);

    ResponseEntity<AddResponse> res = libraryController.addBookImplementation(library);
    System.out.println(res.getBody());
    Assertions.assertEquals(res.getStatusCode(), HttpStatus.ACCEPTED);

    AddResponse body = res.getBody();
    assert body != null;
    Assertions.assertEquals(library.getId(), body.getId());
    Assertions.assertEquals("Book Already Exist", body.getMsg());
  }
}
