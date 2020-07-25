package com.irisdemo.banksim.event;

import com.irisdemo.banksim.model.Customer;
import org.apache.avro.specific.SpecificRecordBase;
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
    
    public com.irisdemo.banksim.avroevent.TransferEvent getAvroEvent()
    {
        Customer sender = getSender();
        Customer receiver = getReceiver();
        
        return new com.irisdemo.banksim.avroevent.TransferEvent(
                                                                    getId(), 
                                                                    getExternalEventDate(), 
                                                                    sender.getAccount().getAccountNumber(), 
                                                                    receiver.getAccount().getAccountNumber(), 
                                                                    getAmountSent()
                                                                );
    }

}