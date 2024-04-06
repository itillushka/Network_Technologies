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

/**
 * This is the controller class for book details.
 * It handles HTTP requests related to book details.
 */
@RestController
@RequestMapping("/api/bookdetails")
public class BookDetailsController {
    private final BookDetailsService bookDetailsService;


    /**
     * Constructor for the BookDetailsController class.
     * @param bookDetailsService the service that this controller will use to interact with the database
     */
    @Autowired
    public BookDetailsController(BookDetailsService bookDetailsService) {
        this.bookDetailsService = bookDetailsService;
    }

    /**
     * Method to get all book details.
     * @return List<GetBookDetailsDto> the list of DTOs containing the book details
     */
    @GetMapping("/all")
    public List<GetBookDetailsDto> getAllDetails(){
        return bookDetailsService.getAll();
    }

    /**
     * Method to get a single book detail by its ID.
     * @param id the ID of the book detail
     * @return GetBookDetailsDto the DTO containing the book detail
     */
    @GetMapping("/{id}")
    public GetBookDetailsDto getOne(@PathVariable long id){
        return bookDetailsService.getOne(id);
    }

    /**
     * Method to create a new book detail.
     * @param bookDetail the DTO containing the book detail
     * @return CreateBookDetailsResponseDto the DTO containing the created book detail
     */
    @PostMapping("/addDetails/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateBookDetailsResponseDto> create(@RequestBody CreateBookDetailsDto bookDetail, @PathVariable String bookId){
        var bookIdInt = Integer.parseInt(bookId.substring(1, bookId.length() - 1));
        var newBookDetail = bookDetailsService.create(bookDetail, bookIdInt);
        return new ResponseEntity<>(newBookDetail, HttpStatus.CREATED);
    }

    /**
     * Method to delete a book detail.
     * @param id the ID of the book detail
     * @return ResponseEntity<Void> the response entity
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        var idLong = Long.parseLong(id.substring(1, id.length() - 1));
        bookDetailsService.delete(idLong);
        return ResponseEntity.noContent().build();
    }
}
