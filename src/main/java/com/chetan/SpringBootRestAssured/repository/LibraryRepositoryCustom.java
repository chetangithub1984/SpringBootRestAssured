package com.chetan.SpringBootRestAssured.repository;

import com.chetan.SpringBootRestAssured.Controller.Library;

import java.util.List;

public interface LibraryRepositoryCustom {
    List<Library> findAllByAuthor(String authorName);
}
