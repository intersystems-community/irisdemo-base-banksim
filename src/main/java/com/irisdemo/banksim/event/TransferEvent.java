package com.irisdemo.banksim.event;

import com.irisdemo.banksim.model.Customer;
import java.util.Calendar;
import com.irisdemo.banksim.avroevent.TransferAvroEvent;

public class TransferEvent extends Event
{
    private Customer customer;
    private Customer otherCustomer;
    private double amount;
    private String transferType;
    private String reference;

    public TransferEvent(Calendar eventDate, String type, Customer customer, Customer otherCustomer, double amount, String reference)
    {
        super(eventDate);

        this.transferType = type;
        this.customer = customer;
        this.otherCustomer = otherCustomer;
        this.amount = amount;
        this.reference = reference;
    }

    public TransferEvent createInverse() throws Exception
    {
        TransferEvent inverseEvent;
        
        switch (this.getTransferType())
        {
            case "TRANSFER_OUT":
                inverseEvent = new TransferEvent(this.getEventDate(), "TRANSFER_IN", this.getOtherCustomer(), this.getCustomer(), -this.getAmount(), this.getReference());
                break;

            case "TRANSFER_IN":
                inverseEvent = new TransferEvent(this.getEventDate(), "TRANSFER_OUT", this.getOtherCustomer(), this.getCustomer(), -this.getAmount(), this.getReference());
                break;

            case "BANK_LOAN_IN":
                inverseEvent = new TransferEvent(this.getEventDate(), "BANK_LOAN_OUT", this.getOtherCustomer(), this.getCustomer(), -this.getAmount(), this.getReference());
                break;

            case "BANK_LOAN_OUT":
                inverseEvent = new TransferEvent(this.getEventDate(), "BANK_LOAN_IN", this.getOtherCustomer(), this.getCustomer(), -this.getAmount(), this.getReference());
                break;
            
            default:
                throw new Exception("Can not create inverse event for transfer type: " + this.getTransferType());
        }

        return inverseEvent;
        
    }

    public String getReference()
    {
        return this.reference;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public String getTransferType()
    {
        return transferType;
    }

    public Customer getOtherCustomer()
    {
        return otherCustomer;
    }

    public double getAmount()
    {
        return amount;
    }

    public long getPartitionKey()
    {
        return customer.getId();
    }

    public void displayInfo()
    {
        super.displayInfo();
        System.out.println("Amount        : " + getAmount());
        System.out.println("Transfer type : " + getTransferType());
        System.out.println("Reference     : " + getReference());
        System.out.println("CUSTOMER:");
        getCustomer().displayInfo(true);
        System.out.println("OTHER CUSTOMER:");
        getOtherCustomer().displayInfo(true);
    }
    
    public TransferAvroEvent getAvroEvent()
    {
        Customer customer = getCustomer();
        Customer otherCustomer = getOtherCustomer();
        
        return new TransferAvroEvent(
                                        getId(), 
                                        getExternalEventDate(), 
                                        getTransferType(),
                                        getReference(),
                                        customer.getId(), 
                                        customer.getAccount().getAccountNumber(), 
                                        otherCustomer.getAccount().getAccountNumber(), 
                                        getAmount()
                                    );
    }

}