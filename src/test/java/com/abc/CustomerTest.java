package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){
    	
    	Customer henry = new Customer("Henry");

        Account checkingAccount = new CheckingAccount(henry);
        Account savingsAccount = new SavingsAccount(henry);
        
        henry.openAccount(checkingAccount);
        henry.openAccount(savingsAccount);

        

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(new SavingsAccount(oscar));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar");
        
        oscar.openAccount(new SavingsAccount(oscar));
        oscar.openAccount(new CheckingAccount(oscar));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Ignore
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar");
        oscar.openAccount(new SavingsAccount(oscar));
        oscar.openAccount(new CheckingAccount(oscar));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
    @Test
    public void testAccountTransfer() {
    	Customer oscar = new Customer("Oscar");
    	Account savingsAccount = new SavingsAccount(oscar);
    	oscar.openAccount(savingsAccount);
    	
    	Account checkingAccount = new CheckingAccount(oscar); 
    	oscar.openAccount(checkingAccount);
    	
    	checkingAccount.deposit(3000.0);
    	
    	savingsAccount.deposit(1500);
    	
    	double sumOfAccountsPre = savingsAccount.sumTransactions() + checkingAccount.sumTransactions();
    	
    	double amount = 1200;
    	try {
    		oscar.performAccountTransfer(checkingAccount, savingsAccount, amount);
    	} catch(InsufficientFundBalanceException ifbe) {
    		Assert.fail("Unexpected exception " + ifbe.toString());
    	}
    	
    	double sumOfAccountsPost  = savingsAccount.sumTransactions() + checkingAccount.sumTransactions();
    	
    	Assert.assertTrue(sumOfAccountsPre == sumOfAccountsPost);
    	
    }
    
    @Test
    public void testAccountTransferExcessiveAmount() {
    	Customer oscar = new Customer("Oscar");
    	Account savingsAccount = new SavingsAccount(oscar);
    	oscar.openAccount(savingsAccount);
    	
    	Account checkingAccount = new CheckingAccount(oscar); 
    	oscar.openAccount(checkingAccount);
    	
    	checkingAccount.deposit(3000.0);
    	
    	savingsAccount.deposit(1500);
    	
    	double sumOfAccountsPre = savingsAccount.sumTransactions() + checkingAccount.sumTransactions();
    	double oldFromAcctBalance = checkingAccount.sumTransactions();
    	double oldToAcctBalance = savingsAccount.sumTransactions();
    	
    	
    	double amount = 5000;
    	try {
    		oscar.performAccountTransfer(checkingAccount, savingsAccount, amount);
    	} catch(InsufficientFundBalanceException ifbe) {
    		double sumOfAccountsPost  = savingsAccount.sumTransactions() + checkingAccount.sumTransactions();
        	double newFromAcctBalance = checkingAccount.sumTransactions();
        	double newToAcctBalance = savingsAccount.sumTransactions();
        	
        	Assert.assertTrue(sumOfAccountsPre == sumOfAccountsPost);
        	Assert.assertTrue(oldFromAcctBalance == newFromAcctBalance);
        	Assert.assertTrue(oldToAcctBalance == newToAcctBalance);
    	}
    	
    	
    	
    }

}
