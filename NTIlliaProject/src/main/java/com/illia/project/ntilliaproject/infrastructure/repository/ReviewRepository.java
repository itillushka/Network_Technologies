package com.illia.project.ntilliaproject.infrastructure.repository;

import com.illia.project.ntilliaproject.infrastructure.entity.BookEntity;
import com.illia.project.ntilliaproject.infrastructure.entity.ReviewEntity;
import com.illia.project.ntilliaproject.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    boolean existsByUserAndBook(UserEntity user, BookEntity book);
}