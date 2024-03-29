package com.illia.project.ntilliaproject.infrastructure.repository;

import com.illia.project.ntilliaproject.infrastructure.entity.AuthEntity;
import com.illia.project.ntilliaproject.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByuserID(Integer userID);
}