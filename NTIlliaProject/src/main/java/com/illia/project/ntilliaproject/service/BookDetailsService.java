package com.illia.project.ntilliaproject.service;

import com.illia.project.ntilliaproject.controller.dto.bookDetails.CreateBookDetailsDto;
import com.illia.project.ntilliaproject.controller.dto.bookDetails.CreateBookDetailsResponseDto;
import com.illia.project.ntilliaproject.controller.dto.bookDetails.GetBookDetailsDto;
import com.illia.project.ntilliaproject.infrastructure.entity.BookDetailsEntity;
import com.illia.project.ntilliaproject.infrastructure.repository.BookDetailsRepository;
import com.illia.project.ntilliaproject.infrastructure.repository.BookRepository;
import com.illia.project.ntilliaproject.infrastructure.suplementary.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDetailsService {

    private final BookDetailsRepository bookDetailsRepository;

    private final BookRepository bookRepository;

    private final Checkers checkers;

    // dependency injection
    @Autowired
    public BookDetailsService(BookDetailsRepository bookDetailsRepository, BookRepository bookRepository, Checkers checkers) {
        this.bookDetailsRepository = bookDetailsRepository;
        this.bookRepository = bookRepository;
        this.checkers = checkers;
    }
    public GetBookDetailsDto getOne(long id){
        var bookDetailsEntity = bookDetailsRepository.findById(id).orElseThrow(() -> new RuntimeException("BookDetails not found"));
        return new GetBookDetailsDto(
                bookDetailsEntity.getId(),
                bookDetailsEntity.getGenre(),
                bookDetailsEntity.getSummary(),
                bookDetailsEntity.getCoverImageURL());

    }
    public List<GetBookDetailsDto> getAll(){

        var bookDetails = bookDetailsRepository.findAll();
        return bookDetails.stream().map((bookDetailsEntity)-> new GetBookDetailsDto(
                bookDetailsEntity.getId(),
                bookDetailsEntity.getGenre(),
                bookDetailsEntity.getSummary(),
                bookDetailsEntity.getCoverImageURL())).collect(java.util.stream.Collectors.toList());
    }



    public CreateBookDetailsResponseDto create(CreateBookDetailsDto bookDetails, int bookId){

        var bookEntity = checkers.checkIfBookExists(bookId);
        checkers.checkIfBookDetailsExists(bookId);


        var bookDetailsEntity = new BookDetailsEntity();
        bookDetailsEntity.setBook(bookEntity);
        bookDetailsEntity.setGenre(bookDetails.getGenre());
        bookDetailsEntity.setSummary(bookDetails.getSummary());
        bookDetailsEntity.setCoverImageURL(bookDetails.getCoverImageURL());
        var newBookDetails = bookDetailsRepository.save(bookDetailsEntity);
        return new CreateBookDetailsResponseDto(newBookDetails.getId(),
                newBookDetails.getGenre(),
                newBookDetails.getSummary(),
                newBookDetails.getCoverImageURL());

    }

    public void delete(long id){
        if(!bookDetailsRepository.existsById(id)){
            throw new RuntimeException("BookDetails not found");
        }
        bookDetailsRepository.deleteById(id);
    }
}
