package com.chetan.SpringBootRestAssured.service;

import com.chetan.SpringBootRestAssured.Controller.Library;
import com.chetan.SpringBootRestAssured.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    LibraryRepository libraryRepository;
    public boolean checkBookAlreadyExist(String id){
        Optional<Library> lib = libraryRepository.findById(id);
        if(lib.isPresent()){
            return true;
        }
        return false;
    }

    public String buildId(String isbn, int aisle){
        return isbn+aisle;
    }

}
