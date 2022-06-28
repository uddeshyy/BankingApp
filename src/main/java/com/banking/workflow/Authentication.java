package com.banking.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.banking.workflow.dto.AccountDTO;
import com.banking.workflow.service.ProcessService;

@Controller
public class Authentication implements JavaDelegate {

	@Autowired
	ProcessService processService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		AccountDTO accountDTO = processService.authenticate((String) execution.getVariable("username"),
															(String) execution.getVariable("password"));
		
		execution.setVariable("accountNo", accountDTO.getAccountNo());
		execution.setVariable("age", accountDTO.getAge());
		execution.setVariable("balance", accountDTO.getBalance());
		execution.setVariable("gender", accountDTO.getGender());
		execution.setVariable("name", accountDTO.getName());
		execution.removeVariable("password");

	}

}
