package com.irisdemo.banksim;

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
}