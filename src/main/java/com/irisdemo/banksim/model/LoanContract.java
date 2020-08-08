package com.irisdemo.banksim.model;

import java.util.Calendar;
 
public class LoanContract extends Identifyable
{

    private Customer borrower;
    private double amountOwed;
    private double contractAmount;
    private double paymentSize;
    private Bank bank;
    private int dayDue;
    
    public LoanContract(Customer borrower, Bank bank, double contractAmount, int dayDue, double paymentSize)
    {
        super();

        this.borrower=borrower;
        this.amountOwed=contractAmount;
        this.contractAmount=contractAmount;
        this.dayDue = dayDue;
        this.paymentSize = paymentSize;
        this.bank = bank;
    }

    public double getContractAmount()
    {
        return this.contractAmount;
    }

    public String getReference()
    {
        return "LOAN #"+getId();
    }

    public boolean dueToday(Calendar currentCalendarDate)
    {
        return currentCalendarDate.get(Calendar.DAY_OF_MONTH) == this.dayDue;
    }

    public void makePayment()
    {
       
        borrower.addBalance(-paymentSize);
        bank.addBalance(paymentSize);
        amountOwed-=paymentSize;
        
    }

    public boolean isComplete()
    {
        return (int)amountOwed <= 0;
    }

    public String getBorrowerAccount()
    {
        return borrower.getAccountNumber();
    }

    public double getPaymentSize()
    {
        return paymentSize;
    }

    public Customer getBorrower()
    {
        return borrower;
    }

}