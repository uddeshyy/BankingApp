package com.banking.workflow;

import java.time.LocalDate;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.banking.workflow.dto.AccountDTO;
import com.banking.workflow.dto.IssueDTO;
import com.banking.workflow.dto.TransactionDTO;
import com.banking.workflow.entity.Transaction;
import com.banking.workflow.repository.TransactionRepository;
import com.banking.workflow.service.ProcessService;

@Controller
public class RegisterIssue implements JavaDelegate {

	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	ProcessService processService;
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String transactionId = (String) execution.getVariable("transactionId");
		Transaction transaction =  transactionRepository.findById(transactionId).orElseThrow(() -> new BpmnError("issue_error", "Wrong Transaction ID"));
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setAmount(transaction.getAmount());
		transactionDTO.setDateTime(transaction.getDateTime());
		AccountDTO fromAcc = new AccountDTO();
		fromAcc.setAccountNo(transaction.getFromAcc().getAccountNo());
		fromAcc.setAge(transaction.getFromAcc().getAge());
		fromAcc.setBalance(transaction.getFromAcc().getBalance());
		fromAcc.setGender(transaction.getFromAcc().getGender());
		fromAcc.setName(transaction.getFromAcc().getName());
		fromAcc.setPassword(transaction.getFromAcc().getPassword());
		fromAcc.setUsername(transaction.getFromAcc().getUsername());
		transactionDTO.setFromAcc(fromAcc);
		
		AccountDTO toAcc = new AccountDTO();
		toAcc.setAccountNo(transaction.getToAcc().getAccountNo());
		toAcc.setAge(transaction.getToAcc().getAge());
		toAcc.setBalance(transaction.getToAcc().getBalance());
		toAcc.setGender(transaction.getToAcc().getGender());
		toAcc.setName(transaction.getToAcc().getName());
		toAcc.setPassword(transaction.getToAcc().getPassword());
		toAcc.setUsername(transaction.getToAcc().getUsername());
		transactionDTO.setToAcc(toAcc);
		
		transactionDTO.setTransactionId(transaction.getTransactionId());
		transactionDTO.setRemarks(transaction.getRemarks());
		IssueDTO issue = new IssueDTO(transactionDTO, LocalDate.now().plusDays(7));
		processService.addIssue(issue);
		
		execution.setVariable("issueNo", issue.getIssueNo());
	}

}
