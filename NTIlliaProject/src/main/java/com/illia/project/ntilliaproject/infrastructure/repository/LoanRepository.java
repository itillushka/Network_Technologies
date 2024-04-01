package com.illia.project.ntilliaproject.infrastructure.repository;

import com.illia.project.ntilliaproject.infrastructure.entity.BookEntity;
import com.illia.project.ntilliaproject.infrastructure.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    Optional<LoanEntity> findByloanID(long loanID);
}
