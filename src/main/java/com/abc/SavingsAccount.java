package com.abc;

public class SavingsAccount extends Account {
	public SavingsAccount(Customer customer) {
		super(Account.SAVINGS, customer);
	}
	
	public SavingsAccount(int accountType, Customer customer) {
		//	check account type here whether it is savings type of not.
		super(accountType, customer);
	}

	@Override
	public double interestEarned() {
		double amount = sumTransactions();
		if (amount <= 1000)
            return amount * 0.001;
        else
            return 1 + (amount-1000) * 0.002;
	}
}
