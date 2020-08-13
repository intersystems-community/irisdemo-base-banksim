package com.irisdemo.banksim.model;

import java.util.LinkedList;

public class Customer extends IdentifyableModel
{

    private Account account;
    private String name;
    private String state;
    private String city;
    private String phoneNumber;
    private LinkedList<LoanContract> loanContracts = new LinkedList<LoanContract>();
    
    public Customer(String accountNumber, double initialMoney, String name, String state, String city, String phoneNumber)
    {
        super();

        this.name = name;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.city = city;

        this.account = new Account(this, accountNumber, initialMoney);
    }

    public void registerNewLoan(LoanContract loanContract)
    {
        this.loanContracts.add(loanContract);
    }

    public boolean hasActiveLoan()
    {
        for (LoanContract contract: loanContracts)
        {
            if (contract.isActive())
                return true;
        }

        return false;
    }

    public void displayInfo(boolean verbose)
    {
        System.out.println("Name          : " + getName());
        System.out.println("Account       : " + getAccount().getAccountNumber());
        if (verbose)
        {
            System.out.println("State         : " + getState());
            System.out.println("City          : " + getCity());
            System.out.println("Phone         : " + getPhoneNumber());
        }
    }

    public Account getAccount() 
    {
        return this.account;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getState()
    {
        return state;
    }
    public void setState(String state)
    {
        this.state = state;
    }

    public String getCity()
    {
        return city;
    }
    public void setCity(String city)
    {
        this.city = city;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    //operations that are about the customer's account
    public boolean enoughBalance(double requestedAmount)
    {
        return this.account.getBalance() > requestedAmount;

    }
    public void addBalance(double amountToAdd)
    {
        this.account.addBalance(amountToAdd);
    }
    public String getAccountNumber()
    {
        return this.account.getAccountNumber();
    }
    
    public double getRandomTransferAmount() 
    {
        final double maxAmount = this.getAccount().getBalance();
        //getting random amount, multiplied by a Random number between 1 and 2 to allow for possibillity of asking for too much money.
        return Math.random()*maxAmount;
    }
  


}

