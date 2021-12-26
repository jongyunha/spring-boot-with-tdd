package com.tdd.SpringBootRestService.repository;

import com.tdd.SpringBootRestService.controller.Library;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LibraryRepositoryImpl implements LibraryRepositoryCustom {

  private final EntityManager em;

  @Override
  public List<Library> findAllByAuthor(String authorName) {
    return em.createQuery(
            "select lib from Library lib where lib.author = :authorName", Library.class)
        .setParameter("authorName", authorName)
        .getResultList();
  }
}
