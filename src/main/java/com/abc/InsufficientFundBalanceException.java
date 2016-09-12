package com.abc;

public class InsufficientFundBalanceException extends Exception {
	private double balance;
	
	public InsufficientFundBalanceException(double balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Current account balance " + balance + " is Insufficient.";  
	}
}
