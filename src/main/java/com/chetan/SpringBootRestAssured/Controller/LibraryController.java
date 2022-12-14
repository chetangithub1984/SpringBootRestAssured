package com.chetan.SpringBootRestAssured.Controller;

import com.chetan.SpringBootRestAssured.repository.LibraryRepository;
import com.chetan.SpringBootRestAssured.repository.LibraryRespositoryCustomImpl;
import com.chetan.SpringBootRestAssured.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    LibraryRepository libraryRepository;
    @Autowired
    LibraryService libraryService;

    @Autowired
    LibraryRespositoryCustomImpl libraryRespositoryCustom;

    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @PostMapping("/addBook")
    public ResponseEntity addBookImplementation(@RequestBody Library library) {
        String id = libraryService.buildId(library.getIsbn(), library.getAisle());
        AddResponse addResponse = new AddResponse();
        if (!libraryService.checkBookAlreadyExist(id)) {
            logger.info("Book do not exist so creating new one");
            library.setId(library.getIsbn() + library.getAisle());
            libraryRepository.save(library);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("unique", library.getIsbn() + library.getAisle());
            addResponse.setMsg("Success Book is Added");
            addResponse.setId(library.getIsbn() + library.getAisle());
            return new ResponseEntity<AddResponse>(addResponse, httpHeaders, HttpStatus.CREATED);
        } else {
            logger.info("Book exist so skipping creation");
            addResponse.setMsg("Book already exist");
            addResponse.setId(id);
            return new ResponseEntity<AddResponse>(addResponse, HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/getBooks/{id}")
    public Library getBookById(@PathVariable(value = "id") String id) {
        try {
            Library lib = libraryRepository.findById(id).get();
            return lib;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getBooks/author")
    public List<Library> getBookByAuthorName(@RequestParam(value = "authorname") String authorname) {
        return libraryRespositoryCustom.findAllByAuthor(authorname);
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Library> updateBook(@PathVariable(value = "id") String id, @RequestBody Library library) {
        Library existingBook = libraryRepository.findById(id).get();
        existingBook.setAisle(library.getAisle());
        existingBook.setBook_name(library.getBook_name());
        existingBook.setAuthor(library.getAuthor());
        libraryRepository.save(existingBook);
        return new ResponseEntity<Library>(existingBook,HttpStatus.OK);
    }

    @DeleteMapping("deleteBook")
    public ResponseEntity<String> deleteBookById(@RequestBody Library library){
        Library libdelete = libraryRepository.findById(library.getId()).get();
        libraryRepository.delete(libdelete);
        return new ResponseEntity<>("Book is deleted", HttpStatus.CREATED);

    }

}
