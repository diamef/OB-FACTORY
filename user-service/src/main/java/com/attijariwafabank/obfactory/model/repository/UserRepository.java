package com.attijariwafabank.obfactory.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attijariwafabank.obfactory.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
