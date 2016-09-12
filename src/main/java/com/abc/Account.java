package com.abc;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    protected final int accountType;
    public List<Transaction> transactions;
    
    private Customer customer;

    public Account(int accountType, Customer customer) {
    	this.customer = customer;
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

	public void withdraw(double amount) {
	    if (amount <= 0) {
	        throw new IllegalArgumentException("amount must be greater than zero");
	    } else {
	        transactions.add(new Transaction(-amount));
	    }
	}
	
	public void accountTransferFrom(Account toAccount, double amount) throws InsufficientFundBalanceException {
		//	perform any needed authorizations
		//	make a note of toAccount for record keeping
		double currentBalance = this.sumTransactions();
		
		if(currentBalance < amount) {
			throw new InsufficientFundBalanceException(currentBalance);
		} else {
			synchronized(customer) {
				transactions.add(new Transaction(-amount));
			}
		}
	}
	
	public void accountTransferTo(Account fromAccount, double amount) {
		//	perform any needed authorizations
		//	make a note of fromAccount for record keeping
		
		synchronized(customer) {
			transactions.add(new Transaction(amount));
		}
	}
	
	public abstract double interestEarned();

	/*
    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount-1000) * 0.002;
//            case SUPER_SAVINGS:
//                if (amount <= 4000)
//                    return 20;
            case MAXI_SAVINGS:
                if (amount <= 1000)
                    return amount * 0.02;
                if (amount <= 2000)
                    return 20 + (amount-1000) * 0.05;
                return 70 + (amount-2000) * 0.1;
            default:
                return amount * 0.001;
        }
    }
    */

    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

}
