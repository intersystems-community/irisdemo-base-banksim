package com.irisdemo.banksim.event;

import com.irisdemo.banksim.model.Customer;
import org.apache.avro.specific.SpecificRecordBase;
import java.util.Calendar;

public class LoanContractEvent extends Event 
{
    public Customer customer;
    public double amountLoaned;

    public LoanContractEvent(Calendar eventDate, Customer customer, double amountLoaned)
    {
        super(eventDate);
        this.customer = customer;
        this.amountLoaned = amountLoaned;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public double getAmountLoaned()
    {
        return amountLoaned;
    }

    public void displayInfo()
    {
        super.displayInfo();
        System.out.println("Amount Loaned : " + getAmountLoaned());
        getCustomer().displayInfo(false);
    }

    public com.irisdemo.banksim.avroevent.LoanContractEvent getAvroEvent()
    {
        Customer customer = getCustomer();
        return new com.irisdemo.banksim.avroevent.LoanContractEvent(
                                                                    getId(), 
                                                                    getExternalEventDate(), 
                                                                    customer.getAccount().getAccountNumber(), 
                                                                    getAmountLoaned()
                                                                );
    }
}