package com.banking.workflow;

import java.time.LocalDateTime;
import java.util.Optional;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.banking.workflow.dto.AccountDTO;
import com.banking.workflow.dto.TransactionDTO;
import com.banking.workflow.entity.Account;
import com.banking.workflow.repository.AccountRepository;
import com.banking.workflow.service.ProcessService;

@Controller
public class SaveTransaction implements JavaDelegate{
	
	@Autowired
	ProcessService processService;
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		long toAccno = Integer.parseInt((String)execution.getVariable("toAccNo"));
		double toSendAmount = Double.parseDouble(execution.getVariable("toSendAmount").toString());
		long accNo = Long.parseLong(execution.getVariable("accountNo").toString());
		double balance = Double.parseDouble(execution.getVariable("balance").toString());
		
		Optional<Account> optional = accountRepository.findById(toAccno);
		
		Account toAccount = optional.orElseThrow(() -> new BpmnError("send_money_error", "Invalid Account number"));
		
		if (balance < toSendAmount) {
			throw new BpmnError("send_money_error", "Insufficient Balance");
		}
		
		processService.updateBalance(accNo, -toSendAmount);
		processService.updateBalance(toAccno, toSendAmount);
		execution.setVariable("balance", balance-toSendAmount);
		
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setAmount(toSendAmount);
		transactionDTO.setRemarks((String) execution.getVariable("remarks"));
		transactionDTO.setDateTime(LocalDateTime.now());
		
		Account fromAcc = accountRepository.findById(accNo).get();
		AccountDTO fromAccountDTO = new AccountDTO();
		fromAccountDTO.setAccountNo(fromAcc.getAccountNo());
		fromAccountDTO.setAge(fromAcc.getAge());
		fromAccountDTO.setBalance(fromAcc.getBalance());
		fromAccountDTO.setGender(fromAcc.getGender());
		fromAccountDTO.setName(fromAcc.getName());
		fromAccountDTO.setPassword(fromAcc.getPassword());
		fromAccountDTO.setUsername(fromAcc.getUsername());
		transactionDTO.setFromAcc(fromAccountDTO);
		
		AccountDTO toAccountDTO = new AccountDTO();
		toAccountDTO.setAccountNo(toAccount.getAccountNo());
		toAccountDTO.setAge(toAccount.getAge());
		toAccountDTO.setBalance(toAccount.getBalance());
		toAccountDTO.setGender(toAccount.getGender());
		toAccountDTO.setName(toAccount.getName());
		toAccountDTO.setPassword(toAccount.getPassword());
		toAccountDTO.setUsername(toAccount.getUsername());
		transactionDTO.setToAcc(toAccountDTO);
		
		transactionDTO.generateId();
		processService.addTransaction(transactionDTO);
		execution.setVariable("transaction", transactionDTO.getTransactionId());
	}
	
	

}
