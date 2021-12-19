package com.tdd.SpringBootRestService.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibraryServiceTest {

  @Autowired LibraryService libraryService;

  @Test
  public void checkBuildIdLogic() {
    String bookId = libraryService.buildId("ZMAN", 24);
    assertEquals("OLD_ZMAN24", bookId);
  }
}
