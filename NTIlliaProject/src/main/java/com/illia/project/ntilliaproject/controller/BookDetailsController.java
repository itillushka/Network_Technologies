package com.illia.project.ntilliaproject.controller;

import com.illia.project.ntilliaproject.controller.dto.bookDetails.CreateBookDetailsDto;
import com.illia.project.ntilliaproject.controller.dto.bookDetails.CreateBookDetailsResponseDto;
import com.illia.project.ntilliaproject.controller.dto.bookDetails.GetBookDetailsDto;
import com.illia.project.ntilliaproject.service.BookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/all")
    public List<GetBookDetailsDto> getAllDetails(){
        return bookDetailsService.getAll();
    }

    @GetMapping("/{id}")
    public GetBookDetailsDto getOne(@PathVariable long id){
        return bookDetailsService.getOne(id);
    }

    @PostMapping("/addDetails/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateBookDetailsResponseDto> create(@RequestBody CreateBookDetailsDto bookDetail, @PathVariable String bookId){
        var bookIdInt = Integer.parseInt(bookId.substring(1, bookId.length() - 1));
        var newBookDetail = bookDetailsService.create(bookDetail, bookIdInt);
        return new ResponseEntity<>(newBookDetail, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        var idLong = Long.parseLong(id.substring(1, id.length() - 1));
        bookDetailsService.delete(idLong);
        return ResponseEntity.noContent().build();
    }
}
