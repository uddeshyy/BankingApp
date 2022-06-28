package com.banking.workflow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.workflow.dto.AccountDTO;
import com.banking.workflow.dto.IssueDTO;
import com.banking.workflow.dto.TransactionDTO;
import com.banking.workflow.entity.Account;
import com.banking.workflow.entity.Issue;
import com.banking.workflow.entity.Transaction;
import com.banking.workflow.repository.AccountRepository;
import com.banking.workflow.repository.IssueRepository;
import com.banking.workflow.repository.TransactionRepository;


@Service(value = "processService")
public class ProcessServiceImpl implements ProcessService{

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	IssueRepository issueRepository;
	
	@Override
	public long addAccount(AccountDTO account) throws BpmnError {
		Optional<Account> optional = accountRepository.findAccountByUsername(account.getUsername());
		if (optional.isPresent()) {
			throw new BpmnError("create_account_error","Username Already Exists");
		}
		Account accountEntity = new Account();
		accountEntity.setAccountNo(account.getAccountNo());
		accountEntity.setAge(account.getAge());
		accountEntity.setBalance(account.getBalance());
		accountEntity.setGender(account.getGender());
		accountEntity.setName(account.getName());
		accountEntity.setPassword(account.getPassword());
		accountEntity.setUsername(account.getUsername());
		accountRepository.save(accountEntity);
		return accountEntity
				.getAccountNo();
	}

	@Override
	public List<TransactionDTO> getAllTransactions(long accountNo) {
		List<Transaction> traList = transactionRepository.getAllTransactions(accountNo);
		List<TransactionDTO> trDtos = new ArrayList<>();
		for(Transaction tra: traList) {
			TransactionDTO tDto = new TransactionDTO();
			tDto.setAmount(tra.getAmount());
			tDto.setDateTime(tra.getDateTime());
			AccountDTO fromAcc = new AccountDTO();
			fromAcc.setAccountNo(tra.getFromAcc().getAccountNo());
			fromAcc.setAge(tra.getFromAcc().getAge());
			fromAcc.setBalance(tra.getFromAcc().getBalance());
			fromAcc.setGender(tra.getFromAcc().getGender());
			fromAcc.setName(tra.getFromAcc().getName());
			fromAcc.setPassword(tra.getFromAcc().getPassword());
			fromAcc.setUsername(tra.getFromAcc().getUsername());
			tDto.setFromAcc(fromAcc);
			
			AccountDTO toAcc = new AccountDTO();
			toAcc.setAccountNo(tra.getToAcc().getAccountNo());
			toAcc.setAge(tra.getToAcc().getAge());
			toAcc.setBalance(tra.getToAcc().getBalance());
			toAcc.setGender(tra.getToAcc().getGender());
			toAcc.setName(tra.getToAcc().getName());
			toAcc.setPassword(tra.getToAcc().getPassword());
			toAcc.setUsername(tra.getToAcc().getUsername());
			tDto.setToAcc(toAcc);
			
			tDto.setTransactionId(tra.getTransactionId());
			tDto.setRemarks(tra.getRemarks());
			trDtos.add(tDto);
		}
		return trDtos;
	}

	@Override
	public long addIssue(IssueDTO issue) {
		Issue issueEntity = new Issue();
		
		issueEntity.setDueDate(issue.getDueDate());
		issueEntity.setIssueNo(issue.getIssueNo());
		Transaction transaction = new Transaction();
		transaction.setAmount(issue.getTransaction().getAmount());
		transaction.setDateTime(issue.getTransaction().getDateTime());
		transaction.setTransactionId(issue.getTransaction().getTransactionId());
		transaction.setRemarks(issue.getTransaction().getRemarks());
		transaction.setFromAcc(accountRepository.findById(issue.getTransaction().getFromAcc().getAccountNo()).get());
		transaction.setToAcc(accountRepository.findById(issue.getTransaction().getToAcc().getAccountNo()).get());
		
		issueEntity.setTransaction(transaction);
		issueRepository.save(issueEntity);
		
		return issueEntity.getIssueNo();
	}

	@Override
	public AccountDTO authenticate(String username, String password) throws BpmnError {
		Optional<Account> optional = accountRepository.findAccountByUsername(username);
		Account account = optional.orElseThrow(() -> new BpmnError("login_error", "Username doesn't exists"));
		if (!account.getPassword().equals(password)) {
			throw new BpmnError("login_error", "Wrong Password");
		}
		
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setAccountNo(account.getAccountNo());
		accountDTO.setAge(account.getAge());
		accountDTO.setBalance(account.getBalance());
		accountDTO.setGender(account.getGender());
		accountDTO.setName(account.getName());
		accountDTO.setPassword(account.getPassword());
		accountDTO.setUsername(account.getUsername());
		
		return accountDTO;
	}

	@Override
	public String addTransaction(TransactionDTO transaction) {
		Transaction transactionEntity = new Transaction();
		transactionEntity.setAmount(transaction.getAmount());
		transactionEntity.setDateTime(transaction.getDateTime());
		
//		Account fromAcc = new Account();
//		fromAcc.setAccountNo(transaction.getFromAcc().getAccountNo());
//		fromAcc.setAge(transaction.getFromAcc().getAge());
//		fromAcc.setBalance(transaction.getFromAcc().getBalance());
//		fromAcc.setGender(transaction.getFromAcc().getGender());
//		fromAcc.setName(transaction.getFromAcc().getName());
//		fromAcc.setPassword(transaction.getFromAcc().getPassword());
//		fromAcc.setUsername(transaction.getFromAcc().getUsername());
		transactionEntity.setFromAcc(accountRepository.findById(transaction.getFromAcc().getAccountNo()).get());
		
//		Account toAcc = new Account();
//		toAcc.setAccountNo(transaction.getToAcc().getAccountNo());
//		toAcc.setAge(transaction.getToAcc().getAge());
//		toAcc.setBalance(transaction.getToAcc().getBalance());
//		toAcc.setGender(transaction.getToAcc().getGender());
//		toAcc.setName(transaction.getToAcc().getName());
//		toAcc.setPassword(transaction.getToAcc().getPassword());
//		toAcc.setUsername(transaction.getToAcc().getUsername());
		transactionEntity.setToAcc(accountRepository.findById(transaction.getToAcc().getAccountNo()).get());
		
		transactionEntity.setTransactionId(transaction.getTransactionId());
		transactionEntity.setRemarks(transaction.getRemarks());
		transactionRepository.save(transactionEntity);
		
		return transaction.getTransactionId();
	}

	@Transactional
	@Override
	public void updateBalance(long accountNo, double amount) {
		Account account = accountRepository.findById(accountNo).get();
		account.setBalance(account.getBalance() + amount);
	}

}
