package com.banking.workflow.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Issue {
	@Id
	private long issueNo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "transaction_id", unique = true)
	private Transaction transaction;
	
	private LocalDate dueDate;
	
	public long getIssueNo() {
		return issueNo;
	}
	public void setIssueNo(long issueNo) {
		this.issueNo = issueNo;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(issueNo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Issue other = (Issue) obj;
		return issueNo == other.issueNo;
	}
	@Override
	public String toString() {
		return "Issue [issueNo=" + issueNo + ", transaction=" + transaction + ", dueDate=" + dueDate + "]";
	}
}
