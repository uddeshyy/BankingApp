package com.banking.workflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.banking.workflow.entity.Transaction;


public interface TransactionRepository extends CrudRepository<Transaction, String>{
	@Query("SELECT t FROM Transaction t WHERE t.fromAcc.accountNo = :account OR t.toAcc.accountNo = :account")
	public List<Transaction> getAllTransactions(@Param("account") long accountNo);
}
