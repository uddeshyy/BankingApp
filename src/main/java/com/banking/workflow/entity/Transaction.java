package com.banking.workflow.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Transaction {
	@Id
	private String transactionId;
	private LocalDateTime dateTime;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "from_acc", unique = true)
	private Account fromAcc;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "to_acc", unique = true)
	private Account toAcc;
	private double amount;
	private String remarks;
	

	

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


	public Account getFromAcc() {
		return fromAcc;
	}


	public void setFromAcc(Account fromAcc) {
		this.fromAcc = fromAcc;
	}


	public Account getToAcc() {
		return toAcc;
	}


	public void setToAcc(Account toAcc) {
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

	
	@Override
	public int hashCode() {
		return Objects.hash(transactionId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(transactionId, other.transactionId);
	}


	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", dateTime=" + dateTime + ", toAcc=" + toAcc.getAccountNo()
				+ ", amount=" + amount + ", remarks=" + remarks + "]";
	}

}
