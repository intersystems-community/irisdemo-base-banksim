package com.irisdemo.banksim.model;

public class Account
{
    private Customer customer;
    private String accountNumber;
    private double balance;


    public Account(Customer customer, String accountNumber, double balance)
    {
        this.customer=customer;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void addBalance(double amount)
    {
        this.balance += amount;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }
    
    public void setAccountNumber(String acctNumber)
    {
        accountNumber = acctNumber;
    }

}
