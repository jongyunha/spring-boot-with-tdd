package com.tdd.SpringBootRestService.repository;

import com.tdd.SpringBootRestService.controller.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, String> {}
