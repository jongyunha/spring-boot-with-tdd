package com.tdd.SpringBootRestService.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.SpringBootRestService.repository.LibraryRepository;
import com.tdd.SpringBootRestService.service.LibraryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryControllerTest {

  @Autowired LibraryController libraryController;
  @MockBean LibraryRepository libraryRepository;
  @MockBean LibraryService libraryService;

  @Autowired private MockMvc mockMvc;

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

  @Test
  public void addBookControllerTest() throws Exception {
    when(libraryService.buildId(library.getIsbn(), library.getAisle())).thenReturn(library.getId());
    when(libraryService.checkBookAlreadyExist(library.getId())).thenReturn(false);
    when(libraryRepository.save(library)).thenReturn(library);

    ObjectMapper map = new ObjectMapper();
    String jsonString = map.writeValueAsString(library);

    mockMvc
        .perform(post("/addBook").contentType(MediaType.APPLICATION_JSON).content(jsonString))
        .andExpect(status().isCreated());
  }
}
