package com.chetan.SpringBootRestAssured.repository;

import com.chetan.SpringBootRestAssured.Controller.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class LibraryRespositoryCustomImpl implements LibraryRepositoryCustom{
    @Autowired
    LibraryRepository libraryRepository;

    @Override
    public List<Library> findAllByAuthor(String authorName) {
        List<Library> bookwihauthor = new ArrayList<>();
        List<Library> books = libraryRepository.findAll();
        for (Library library: books) {
            if (library.getAuthor().equalsIgnoreCase(authorName)){
                bookwihauthor.add(library);
            }
        }
        return bookwihauthor;
    }
}
