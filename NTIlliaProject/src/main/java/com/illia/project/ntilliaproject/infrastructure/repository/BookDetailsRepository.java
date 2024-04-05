package com.illia.project.ntilliaproject.infrastructure.repository;

import com.illia.project.ntilliaproject.infrastructure.entity.BookDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetailsEntity, Long> {
    Optional<BookDetailsEntity> findBybook_bookID(int bookId);
}
