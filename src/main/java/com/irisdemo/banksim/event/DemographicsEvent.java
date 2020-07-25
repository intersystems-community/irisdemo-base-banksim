package com.irisdemo.banksim.event;

import com.irisdemo.banksim.model.Customer;
import java.util.Calendar;
       
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

    public com.irisdemo.banksim.avroevent.DemographicsEvent getAvroEvent()
    {
        Customer customer = getCustomer();
        return new com.irisdemo.banksim.avroevent.DemographicsEvent(
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