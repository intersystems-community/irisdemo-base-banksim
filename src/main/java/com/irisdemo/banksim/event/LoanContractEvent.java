package com.irisdemo.banksim.event;

import com.irisdemo.banksim.model.Customer;
import org.apache.avro.specific.SpecificRecordBase;
import java.util.Calendar;
import com.irisdemo.banksim.avroevent.LoanContractAvroEvent;

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

    public LoanContractAvroEvent getAvroEvent()
    {
        Customer customer = getCustomer();
        return new LoanContractAvroEvent(
                                            getId(), 
                                            getExternalEventDate(), 
                                            customer.getAccount().getAccountNumber(), 
                                            getAmountLoaned()
                                        );
    }
}