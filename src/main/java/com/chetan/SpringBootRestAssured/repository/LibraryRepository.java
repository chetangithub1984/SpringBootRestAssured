package com.chetan.SpringBootRestAssured.repository;

import com.chetan.SpringBootRestAssured.Controller.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, String>, LibraryRepositoryCustom {
}
