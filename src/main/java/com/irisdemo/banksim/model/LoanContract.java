package com.irisdemo.banksim.model;

import java.util.Calendar;
 
public class LoanContract
{
    public Customer borrower;
    private float amountOwed;
    private float paymentSize;
    public static Bank bank;
    public int dayDue;

    public LoanContract(Customer borrower, float initialAmount, int dayDue, float paymentSize)
    {
        this.borrower=borrower;
        this.amountOwed=initialAmount;
        this.dayDue = dayDue;
        this.paymentSize = paymentSize;

    }

    public boolean dueToday(Calendar currentCalendarDate)
    {
        return currentCalendarDate.get(Calendar.DAY_OF_MONTH) == this.dayDue;
    }

    public static boolean feasibleLoan(float amount)
    {
        return bank.enoughBalance(amount);
    }

    public void makePayment()
    {
       
        borrower.addBalance(-paymentSize);
        bank.addBalance(paymentSize);
        amountOwed-=paymentSize;
        
    }

    public boolean isComplete()
    {
        return (int)amountOwed == 0;
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