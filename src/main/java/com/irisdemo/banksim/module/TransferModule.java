package com.irisdemo.banksim.module;

import com.irisdemo.banksim.Simulator;
import com.irisdemo.banksim.Util;
import com.irisdemo.banksim.event.DemographicsEvent;
import com.irisdemo.banksim.event.Event;
import com.irisdemo.banksim.event.TransferEvent;
import com.irisdemo.banksim.model.Customer;

public class TransferModule extends Module {

    public TransferModule(Simulator simulator, double probability) 
    {
        super(simulator, probability);
    }
    
    public void produceEvents() throws Exception
    {
        Customer customer;
        Customer otherCustomer;
        double amount;
        TransferEvent transferEventOut;
        TransferEvent transferEventIn;

        if (Math.random()>probability)
        {
            // Make sure we are picking two different random customers.
            do
            {
                customer = simulator.getRandomCustomer();
                otherCustomer = simulator.getRandomCustomer();
            }
            while (customer==otherCustomer);

            amount = customer.getRandomTransferAmount();

            customer.addBalance(-amount);
            otherCustomer.addBalance(amount);

            transferEventOut = new TransferEvent(simulator.getCurrentCalendarDate(), "TRANSFER_OUT", customer, otherCustomer, -amount, "");
            transferEventIn = transferEventOut.createInverse();
            
            simulator.queueEvent(transferEventOut);
            simulator.queueEvent(transferEventIn);
        }
    }

    public void dailyChecks() throws Exception
    {
        //Nothing to be done here
    }

}