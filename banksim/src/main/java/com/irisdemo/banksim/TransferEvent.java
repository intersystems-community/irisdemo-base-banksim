package com.irisdemo.banksim;

import java.util.Calendar;

public class TransferEvent extends Event
{
    public Customer receiver;
    public Customer sender;
    public double amount;

    public TransferEvent(Calendar eventDate, Customer sender, Customer receiver, double amount)
    {
        super(eventDate);

        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public Customer getSender()
    {
        return sender;
    }

    public Customer getReceiver()
    {
        return receiver;
    }

    public double getAmountSent()
    {
        return amount;
    }

    public void displayInfo()
    {
        super.displayInfo();
        System.out.println("Amount sent   : " + getAmountSent());
        System.out.println("SENDER:");
        getSender().displayInfo(true);
        System.out.println("RECEIVER:");
        getReceiver().displayInfo(true);
    }
    
}