package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

}
