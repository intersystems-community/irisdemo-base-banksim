package com.irisdemo.banksim.model;


public class Customer extends Identifyable
{

    private Account account;
    private String name;
    private String state;
    private String city;
    private String phoneNumber;

    
    public Customer(String accountNumber, double initialMoney, String name, String state, String city, String phoneNumber)
    {
        super();

        this.name = name;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.city = city;

        this.account = new Account(this, accountNumber, initialMoney);
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
    

   


}

