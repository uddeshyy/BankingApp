package com.banking.workflow.service;

import java.util.List;

import org.camunda.bpm.engine.delegate.BpmnError;

import com.banking.workflow.dto.AccountDTO;
import com.banking.workflow.dto.IssueDTO;
import com.banking.workflow.dto.TransactionDTO;

public interface ProcessService {
	public long addAccount(AccountDTO account) throws BpmnError;
	public List<TransactionDTO> getAllTransactions(long accountNo);
	public long addIssue(IssueDTO issue);
	public String addTransaction(TransactionDTO transaction);
	public void updateBalance(long accountNo, double amount);
	public AccountDTO authenticate(String username, String password) throws BpmnError;
}
