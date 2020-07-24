package com.irisdemo.banksim;

import java.util.Calendar;

public class LoanContractEvent extends Event 
{
    public Customer customer;
    public float amountLoaned;

    public LoanContractEvent(Calendar eventDate, Customer customer, float amountLoaned)
    {
        super(eventDate);
        this.customer = customer;
        this.amountLoaned = amountLoaned;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public float getAmountLoaned()
    {
        return amountLoaned;
    }

    public void displayInfo()
    {
        super.displayInfo();
        System.out.println("Amount Loaned : " + getAmountLoaned());
        getCustomer().displayInfo(false);
    }
}