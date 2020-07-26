package com.irisdemo.banksim.event;

import com.irisdemo.banksim.model.Customer;
import java.util.Calendar;
import com.irisdemo.banksim.avroevent.TransferAvroEvent;

public class TransferEvent extends Event
{
    private Customer receiver;
    private Customer sender;
    private double amount;
    private String transferType;

    public TransferEvent(Calendar eventDate, String type, Customer sender, Customer receiver, double amount)
    {
        super(eventDate);

        this.transferType = type;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public Customer getSender()
    {
        return sender;
    }

    public String getTransferType()
    {
        return transferType;
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
        System.out.println("Transfer type : " + getTransferType());
        System.out.println("SENDER:");
        getSender().displayInfo(true);
        System.out.println("RECEIVER:");
        getReceiver().displayInfo(true);
    }
    
    public TransferAvroEvent getAvroEvent()
    {
        Customer sender = getSender();
        Customer receiver = getReceiver();
        
        return new TransferAvroEvent(
                                        getId(), 
                                        getExternalEventDate(), 
                                        getTransferType(),
                                        sender.getAccount().getAccountNumber(), 
                                        receiver.getAccount().getAccountNumber(), 
                                        getAmountSent()
                                    );
    }

}