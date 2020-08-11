package com.irisdemo.banksim.event;

import com.irisdemo.banksim.model.Customer;
import java.util.Calendar;
import com.irisdemo.banksim.avroevent.DemographicsAvroEvent;
import com.irisdemo.banksim.avroevent.mailing_address;

public class DemographicsEvent extends Event 
{ 
    private Customer customer;

    public DemographicsEvent(Calendar eventDate, Customer customer)
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

    public DemographicsAvroEvent getAvroEvent()
    {
        mailing_address address = new mailing_address(customer.getState(), customer.getCity(), customer.getPhoneNumber()); 

        return new DemographicsAvroEvent(
                                            getId(), 
                                            getExternalEventDate(), 
                                            customer.getId(),
                                            customer.getName(),
                                            address);
    }
}