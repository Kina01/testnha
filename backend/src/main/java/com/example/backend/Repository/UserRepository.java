package com.example.backend.Repository;

import java.util.Optional;

import com.example.backend.Model.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    
    Optional<UserEntity> findById(Long id);
    Optional<UserEntity> findByFullname(String fullname);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByPassword(String password);
    Optional<UserEntity> findByRole(String role);
}