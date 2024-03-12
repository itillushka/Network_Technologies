package com.illia.project.ntilliaproject.service;

import com.illia.project.ntilliaproject.infrastructure.entity.BookDetailsEntity;
import com.illia.project.ntilliaproject.infrastructure.repository.BookDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDetailsService {

    private final BookDetailsRepository bookDetailsRepository;

    // dependency injection
    @Autowired
    public BookDetailsService(BookDetailsRepository bookDetailsRepository) {
        this.bookDetailsRepository = bookDetailsRepository;
    }
    public List<BookDetailsEntity> getAll(){
        return bookDetailsRepository.findAll();
    }
}
