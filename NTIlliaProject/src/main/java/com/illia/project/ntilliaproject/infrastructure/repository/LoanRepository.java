package com.illia.project.ntilliaproject.infrastructure.repository;

import com.illia.project.ntilliaproject.infrastructure.entity.BookEntity;
import com.illia.project.ntilliaproject.infrastructure.entity.LoanEntity;
import com.illia.project.ntilliaproject.infrastructure.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    Optional<LoanEntity> findByloanID(long loanID);
    boolean existsByUserAndBook(UserEntity user, BookEntity book);
    List<LoanEntity> findByUser_userID(Integer userID);
    Optional<LoanEntity> findByUser_userIDAndBook_bookID(Integer userID, Integer bookID);
}
