package com.abc;

public class CheckingAccount extends Account {
	public CheckingAccount(Customer customer) {
		super(Account.CHECKING, customer);
	}

	@Override
	public double interestEarned() {
		double amount = sumTransactions();
		return amount * 0.001;
	}
}
