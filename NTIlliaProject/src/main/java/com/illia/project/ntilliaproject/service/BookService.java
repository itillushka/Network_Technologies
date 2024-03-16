package com.illia.project.ntilliaproject.service;


import com.illia.project.ntilliaproject.controller.dto.book.CreateBookDto;
import com.illia.project.ntilliaproject.controller.dto.book.CreateBookResponseDto;
import com.illia.project.ntilliaproject.controller.dto.book.GetBookDto;
import com.illia.project.ntilliaproject.infrastructure.entity.BookEntity;
import com.illia.project.ntilliaproject.infrastructure.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    // dependency injection
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public GetBookDto getOne(long id){
        var bookEntity = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
        return new GetBookDto(
                bookEntity.getBookID(),
                bookEntity.getISBN(),
                bookEntity.getTitle(),
                bookEntity.getAuthor(),
                bookEntity.getPublisher(),
                bookEntity.getYearPublished(),
                bookEntity.getAvailableCopies()>0);

    }
    public List<GetBookDto> getAll(){
        var books = bookRepository.findAll();
        return books.stream().map((book)-> new GetBookDto(
                book.getBookID(),
                book.getISBN(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getYearPublished(),
                book.getAvailableCopies()>0)).collect(Collectors.toList());
    }

    public CreateBookResponseDto create(CreateBookDto book){
        var bookEntity = new BookEntity();
        bookEntity.setISBN(book.getISBN());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setPublisher(book.getPublisher());
        bookEntity.setYearPublished(book.getYearPublished());
        bookEntity.setAvailableCopies(book.getAvailableCopies());
        var newBook= bookRepository.save(bookEntity);
        return new CreateBookResponseDto(newBook.getBookID(),
                newBook.getISBN(),
                newBook.getTitle(),
                newBook.getAuthor(),
                newBook.getPublisher(),
                newBook.getYearPublished(),
                newBook.getAvailableCopies());
    }

    public void delete(long id){
        if(!bookRepository.existsById(id)){
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(id);
    }

}
