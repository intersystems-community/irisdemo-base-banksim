package com.irisdemo.banksim.model;

import java.util.Calendar;
 
public class LoanContract
{
    public Customer borrower;
    private float amountOwed;
    private float paymentSize;
    public Bank bank;
    public int dayDue;

    public LoanContract(Customer borrower, Bank bank, float initialAmount, int dayDue, float paymentSize)
    {
        this.borrower=borrower;
        this.amountOwed=initialAmount;
        this.dayDue = dayDue;
        this.paymentSize = paymentSize;
        this.bank = bank;

    }

    public boolean dueToday(Calendar currentCalendarDate)
    {
        return currentCalendarDate.get(Calendar.DAY_OF_MONTH) == this.dayDue;
    }

    public boolean feasibleLoan(float amount)
    {
        return this.bank.enoughBalance(amount);
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

    public float getPaymentSize()
    {
        return paymentSize;
    }

    public Customer getBorrower()
    {
        return borrower;
    }




}