package com.banking.workflow;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.banking.workflow.dto.TransactionDTO;
import com.banking.workflow.service.ProcessService;

@Controller
public class FetchTransactions implements JavaDelegate {

	@Autowired
	ProcessService processService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		List<TransactionDTO> trDtos = processService.getAllTransactions(Long.parseLong(execution.getVariable("accountNo").toString()));
		String transactions = "";
		
		for(TransactionDTO tr : trDtos) {
			transactions += (tr.toString() + "\n");
		}
		
		execution.setVariable("transactions", transactions);

	}

}
