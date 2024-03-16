package com.illia.project.ntilliaproject.controller;

import com.illia.project.ntilliaproject.controller.dto.bookDetails.CreateBookDetailsDto;
import com.illia.project.ntilliaproject.controller.dto.bookDetails.CreateBookDetailsResponseDto;
import com.illia.project.ntilliaproject.controller.dto.bookDetails.GetBookDetailsDto;
import com.illia.project.ntilliaproject.infrastructure.entity.BookDetailsEntity;
import com.illia.project.ntilliaproject.service.BookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookdetails")
public class BookDetailsController {
    private final BookDetailsService bookDetailsService;

    @Autowired
    public BookDetailsController(BookDetailsService bookDetailsService) {
        this.bookDetailsService = bookDetailsService;
    }

    @GetMapping
    public List<GetBookDetailsDto> getAllLoans(){
        return bookDetailsService.getAll();
    }

    @GetMapping("/{id}")
    public GetBookDetailsDto getOne(@PathVariable long id){
        return bookDetailsService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<CreateBookDetailsResponseDto> create(@RequestBody CreateBookDetailsDto bookDetail){
        var newBookDetail = bookDetailsService.create(bookDetail);
        return new ResponseEntity<>(newBookDetail, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        bookDetailsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
