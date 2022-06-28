package com.banking.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.banking.workflow.dto.AccountDTO;
import com.banking.workflow.service.ProcessService;

@Controller
public class CreateAccount implements JavaDelegate {

	@Autowired
	ProcessService processService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setName((String)execution.getVariable("name"));
		accountDTO.setAge((int)execution.getVariable("age"));
		accountDTO.setGender((String)execution.getVariable("gender"));
		accountDTO.setUsername((String)execution.getVariable("username"));
		accountDTO.setPassword((String)execution.getVariable("password"));
		accountDTO.setBalance((int)execution.getVariable("balance"));
		processService.addAccount(accountDTO);
		execution.removeVariable("name");
		execution.removeVariable("age");
		execution.removeVariable("gender");
		execution.removeVariable("username");
		execution.removeVariable("password");
		execution.removeVariable("balance");
		execution.removeVariable("accountExists");
	}

}
