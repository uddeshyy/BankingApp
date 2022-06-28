package com.banking.workflow.dto;

import java.time.LocalDate;

public class IssueDTO {
	private long issueNo;
	private TransactionDTO transaction;
	private LocalDate dueDate;
	private static int counter = 101;
	public IssueDTO(TransactionDTO transaction, LocalDate dueDate) {
		super();
		this.issueNo = counter++;
		this.transaction = transaction;
		this.dueDate = dueDate;
	}
	public long getIssueNo() {
		return issueNo;
	}
	public void setIssueNo(long issueNo) {
		this.issueNo = issueNo;
	}
	public TransactionDTO getTransaction() {
		return transaction;
	}
	public void setTransaction(TransactionDTO transaction) {
		this.transaction = transaction;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	@Override
	public String toString() {
		return "Issue [issueNo=" + issueNo + ", transaction=" + transaction + ", dueDate=" + dueDate + "]";
	}
	
	
}
