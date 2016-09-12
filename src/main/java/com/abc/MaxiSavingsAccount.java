package com.abc;

import java.time.LocalDate;
import java.util.Date;

public class MaxiSavingsAccount extends SavingsAccount {
	public int NO_OF_DAYS_LIMIT_FOR_INTEREST_RATE_QUALIFICATION = 10;
	
	public MaxiSavingsAccount(Customer customer) {
		super(Account.MAXI_SAVINGS, customer);
	}
	
	@Override
	public double interestEarned() {
		double amount = sumTransactions();
		if(hasAnyTransactions(NO_OF_DAYS_LIMIT_FOR_INTEREST_RATE_QUALIFICATION)) {
			if (amount <= 1000)
	            return amount * 0.02;
	        if (amount <= 2000)
	            return 20 + (amount-1000) * 0.05;
	        return 70 + (amount-2000) * 0.1;
		} else {
			return amount * 0.05;
		}
	}
	
	private boolean hasAnyTransactions(int numberOfDays) {
		if(transactions == null || transactions.size() == 0)
			return false;
		

		LocalDate priorDate = LocalDate.now().minusDays(numberOfDays);
		
		for(Transaction t : transactions) {
			if(t.getTransactionDate().isAfter(priorDate))
				return true;
		}
		
		return false;
	}
}
