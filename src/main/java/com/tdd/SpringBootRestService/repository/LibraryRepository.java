package com.tdd.SpringBootRestService.repository;

import com.tdd.SpringBootRestService.controller.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository
    extends JpaRepository<Library, String>, LibraryRepositoryCustom {}
