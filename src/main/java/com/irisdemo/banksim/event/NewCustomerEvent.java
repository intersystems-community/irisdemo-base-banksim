package com.irisdemo.banksim.event;

import com.irisdemo.banksim.model.Customer;
import java.util.Calendar;
import com.irisdemo.banksim.avroevent.NewCustomerAvroEvent;
import com.irisdemo.banksim.avroevent.mailing_address;

public class NewCustomerEvent extends Event 
{
    private Customer customer;

    public NewCustomerEvent(Calendar eventDate, Customer customer)
    {
        super(eventDate);
        this.customer = customer;
    }

    public void displayInfo()
    {
        super.displayInfo();
        customer.displayInfo(true);
    }

    public long getPartitionKey()
    {
        return customer.getId();
    }

    public NewCustomerAvroEvent getAvroEvent()
    {
        mailing_address address = new mailing_address(customer.getState(), customer.getCity(), customer.getPhoneNumber()); 
        
        return new NewCustomerAvroEvent(
                                            getId(), 
                                            getExternalEventDate(), 
                                            customer.getId(),
                                            customer.getName(),
                                            customer.getAccount().getAccountNumber(),
                                            customer.getAccount().getBalance(),
                                            address);
    }
}