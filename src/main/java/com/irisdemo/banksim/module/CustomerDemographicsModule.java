package com.irisdemo.banksim.module;

import com.irisdemo.banksim.Simulator;
import com.irisdemo.banksim.Util;
import com.irisdemo.banksim.event.DemographicsEvent;
import com.irisdemo.banksim.event.Event;
import com.irisdemo.banksim.model.Customer;

public class CustomerDemographicsModule extends Module
{

    public CustomerDemographicsModule(Simulator simulator, double probability) 
    {
        super(simulator, probability);
    }
    
    public void produceEvents() throws Exception
    {
        Customer randomCustomer;
        Event event;
        int choice = (int) (Math.random() * 2);

        if (Math.random()>this.probability)
        {
            // Pick a random customer
            randomCustomer = this.simulator.getRandomCustomer();
            
            switch (choice) 
            {
                case 0:
                    randomCustomer.setState(Util.getRandomState());
                    break;
    
                case 1:
                    randomCustomer.setCity(Util.getRandomCity());
                    break;
    
                case 2:
                    randomCustomer.setPhoneNumber(Util.getRandomPhoneNumber());
                    break;    
            }
    
            event = new DemographicsEvent(this.simulator.getCurrentCalendarDate(), randomCustomer);
            simulator.queueEvent(event);
        }
    }

    public void dailyChecks() throws Exception
    {
        //Nothing to be done here
    }
    
}