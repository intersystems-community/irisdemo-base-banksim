package com.irisdemo.banksim.event;

import com.irisdemo.banksim.model.Customer;
import java.util.Calendar;
import com.irisdemo.banksim.avroevent.DemographicsAvroEvent;

public class DemographicsEvent extends Event 
{ 
    public Customer customer;


    public DemographicsEvent(Calendar eventDate, Customer customer)
    {
        super(eventDate);
        this.customer = customer;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void displayInfo()
    {
        super.displayInfo();
        getCustomer().displayInfo(true);
    }

    public DemographicsAvroEvent getAvroEvent()
    {
        Customer customer = getCustomer();
        return new DemographicsAvroEvent(
                                            getId(), 
                                            getExternalEventDate(), 
                                            customer.getAccount().getAccountNumber(), 
                                            customer.getName(),
                                            customer.getState(),
                                            customer.getCity(),
                                            customer.getPhoneNumber()
                                        );
    }
}