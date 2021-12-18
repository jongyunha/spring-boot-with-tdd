package com.tdd.SpringBootRestService.repository;

import com.tdd.SpringBootRestService.controller.Library;
import java.util.List;

public interface LibraryRepositoryCustom {
  List<Library> findAllByAuthor(String authorName);
}
