package com.irisdemo.banksim.model;

import java.util.Calendar;

public class LoanContract extends IdentifyableModel
{

    private Customer borrower;
    private double amountOwed;
    private double contractAmount;
    private double paymentSize;
    private Bank bank;
    private int dueDay;
    private boolean paidAndComplete = false;
    
    public LoanContract(Customer borrower, Bank bank, double contractAmount, int dueDay, double paymentSize)
    {
        super();

        this.borrower=borrower;
        this.borrower.registerNewLoan(this);
        this.amountOwed=contractAmount;
        this.contractAmount=contractAmount;
        this.dueDay = dueDay;
        this.paymentSize = paymentSize;
        this.bank = bank;
    }

    public void markAsPaidAndComplete()
    {
        this.paidAndComplete=true;
    }

    public boolean isActive()
    {
        return !paidAndComplete;
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
        return (currentCalendarDate.get(Calendar.DAY_OF_MONTH) == this.dueDay);
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