package com.banking.workflow.dto;

public class AccountDTO {
	private String name;
	private int age;
	private String gender;
	private String username;
	private String password;
	private long accountNo;
	private double balance;
	private static int counter = 10001;
	
	
	public AccountDTO() {
		super();
		this.accountNo = counter++;
	}
	public AccountDTO(String name, int age, String gender, String username, String password, double balance) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.username = username;
		this.password = password;
		this.accountNo = counter++;
		this.balance = balance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "AccountDTO [name=" + name + ", age=" + age + ", gender=" + gender + ", balance=" + balance + ", accountNo="
				+ accountNo + "]";
	}
	
	
}
