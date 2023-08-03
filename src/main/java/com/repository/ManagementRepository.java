package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.ManagementEntity;

@Repository
public interface ManagementRepository extends JpaRepository<ManagementEntity, Integer>{
	
}
