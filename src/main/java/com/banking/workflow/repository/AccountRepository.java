package com.banking.workflow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.banking.workflow.entity.Account;


public interface AccountRepository extends CrudRepository<Account, Long> {
	@Query("SELECT a FROM Account a WHERE a.username = :username")
	Optional<Account> findAccountByUsername(@Param("username") String username);
}
