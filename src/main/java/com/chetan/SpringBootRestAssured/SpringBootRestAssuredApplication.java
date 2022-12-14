package com.chetan.SpringBootRestAssured;

import com.chetan.SpringBootRestAssured.Controller.Library;
import com.chetan.SpringBootRestAssured.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringBootRestAssuredApplication {

	@Autowired
	LibraryRepository repository;


	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestAssuredApplication.class, args);
	}
/*	@Override
	public void run(String args []){
		Library lib = repository.findById("fdsefr343").get();
		System.out.println(lib.getAuthor());
		Library library = new Library();
		library.setAuthor("Chetan");
		library.setAisle(123);
		library.setBook_name("Chetan Spring Boot");
		library.setIsbn("def");
		library.setId("def123");
		repository.save(library);
		List<Library> listOfLibrary = repository.findAll();
		for (Library li :listOfLibrary) {
			System.out.println(li.getBook_name());
		}
		repository.deleteById("def123");


	}*/

}
