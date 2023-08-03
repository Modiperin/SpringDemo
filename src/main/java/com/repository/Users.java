package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.UserEntity;

public interface Users extends  JpaRepository<UserEntity, Integer>{

	Optional<UserEntity> findByEmail(String email);

	List<UserEntity> findByAgeBetween(int i, int j);

	
}
