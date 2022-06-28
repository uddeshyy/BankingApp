package com.banking.workflow.dto;

import java.time.LocalDateTime;

public class TransactionDTO {
	private String transactionId;
	private LocalDateTime dateTime;
	private AccountDTO fromAcc;
	private AccountDTO toAcc;
	private double amount;
	private String remarks;
	private int counter = 1;
	
	
	public TransactionDTO() {
		super();
	}


	public TransactionDTO(String transactionId, LocalDateTime dateTime, AccountDTO fromAcc, AccountDTO toAcc,
			double amount, String remarks) {
		super();
		this.dateTime = dateTime;
		this.fromAcc = fromAcc;
		this.toAcc = toAcc;
		this.amount = amount;
		this.remarks = remarks;
		this.transactionId = counter++ +""+ dateTime.getSecond() +""+ dateTime.getMinute() +""+ dateTime.getHour() 
										+""+ dateTime.getDayOfYear();
	}
	

	public String getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	public LocalDateTime getDateTime() {
		return dateTime;
	}


	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}


	public AccountDTO getFromAcc() {
		return fromAcc;
	}


	public void setFromAcc(AccountDTO fromAcc) {
		this.fromAcc = fromAcc;
	}


	public AccountDTO getToAcc() {
		return toAcc;
	}


	public void setToAcc(AccountDTO toAcc) {
		this.toAcc = toAcc;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void generateId() {
		this.transactionId = counter++ +""+ dateTime.getSecond() +""+ dateTime.getMinute() +""+ dateTime.getHour() 
		+""+ dateTime.getDayOfYear();
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", dateTime=" + dateTime + ", fromAcc=" + fromAcc.getAccountNo()+ 
				", toAcc=" + toAcc.getAccountNo() + ", amount=" + amount + ", remarks=" + remarks + "]";
	}


	
	
	
}
