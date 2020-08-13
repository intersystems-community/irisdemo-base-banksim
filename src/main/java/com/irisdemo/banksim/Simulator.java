package com.irisdemo.banksim;

import com.irisdemo.banksim.model.*;
import com.irisdemo.banksim.event.*;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.ListIterator;

import com.irisdemo.banksim.module.*;

public class Simulator 
{
    private Customer[] allCustomers;
    private Bank bank;
    
    private Calendar currentCalendarDate;
    private int maxEventsPerDay;
    private int totalEvents;
    private int maxNumberOfEvents;
    private int currentEventsDay = 0;
    private int millisBetweenEvent;
    private LinkedList<Event> initializationEventsQueue = new LinkedList<>();
    private LinkedList<Event> eventQueue = new LinkedList<>();
    private double probabilityTransfer = .45;
    private double probabilityDemographics = .20;
    private double probabilityLoanContract = .05;
    

    private CustomerDemographicsModule customerDemographicsModule;
    private TransferModule transfersModule;
    private LoanModule loanModule;

    public Simulator(int amountDays, int maxNumberOfEvents, int amountCustomers) 
    {

        // initialize state machine
        this.currentCalendarDate = Calendar.getInstance();
        this.currentCalendarDate.add(Calendar.DATE, -amountDays);
        currentCalendarDate.set(Calendar.HOUR_OF_DAY, 0);

        this.maxEventsPerDay = maxNumberOfEvents / amountDays;
        
        // A day has 24*60*60 = 86400 seconds
        this.millisBetweenEvent = 86400/maxEventsPerDay*1000;

        this.maxNumberOfEvents = maxNumberOfEvents;

        // Initial state for Customers/Bank/Accounts/Loans
        allCustomers = new Customer[amountCustomers];

        // Modules:
        this.customerDemographicsModule = new CustomerDemographicsModule(this, this.probabilityDemographics);
        this.transfersModule = new TransferModule(this, this.probabilityTransfer);
        this.loanModule = new LoanModule(this, this.probabilityLoanContract);

        // All accounts will be created on the same day for now
        Calendar newAccountCreationDate = (Calendar)currentCalendarDate.clone();

        // Customers and Account (This simulator only allows one account per customer for now)
        for (int i = 1; i < amountCustomers; i++) 
        {
            String accountNumber = String.format("%07d", i);
            String name = Util.getRandomName();
            String state = Util.getRandomState();
            String city = Util.getRandomCity();
            String phoneNumber = Util.getRandomPhoneNumber();

            allCustomers[i-1] = new Customer(accountNumber, (float) (Math.random() * 100000), name, state, city, phoneNumber);

            initializationEventsQueue.add(new NewCustomerEvent(newAccountCreationDate, allCustomers[i-1]));
        }

        // Initial balance of 10 billion
        bank = new Bank(10000000000d);
        initializationEventsQueue.add(new NewCustomerEvent(newAccountCreationDate, bank));

    }

    public synchronized org.apache.avro.specific.SpecificRecordBase nextAvroEvent()
    {
        try
        {
            return next().getAvroEvent();
        }
        catch (NullPointerException e)
        {
            return null;
        }
    }

    public synchronized Event next() 
    {
        // Initialization events do not advance time and do not count as events of the simulation
        if (!initializationEventsQueue.isEmpty())
        {
            return initializationEventsQueue.pop();
        }

        // Base case. Return null if we have already finished the total
        // required events/reached the expected date
        if (totalEvents == maxNumberOfEvents) {
            return null;
        }

        // checkLoansDay() can queue several loan payments on the eventQueue. These are of higher priority.
        // At every call to next() we will return events from this queue until it is empty. Eventually, after 
        // many calls to next(), the queue will be empty and all loan payments will be reported. We will then move
        // on beyond this point and generate new events for normal transactions and demographics.
        // As we are returning these events, they also do not advance time and we want it that way
        if (!eventQueue.isEmpty()) 
        {
            totalEvents++;
            currentEventsDay++;
            return eventQueue.pop();
        }

        // Now that we have delivered all loan payments for the day, we can start doing normal transactions and let time flow

        // Let's see if we have completed all the required events for the day. We may need to force a change to the next date
        if (currentEventsDay == maxEventsPerDay) 
        {
            newDay();

            // We need to call it like this so that loan payments for the beginning of the new day get processed
            return next();
        }

        // Advance time between events... This may change the date to the next day... 
        // That is ok. We are not being too strict about the number of events per day anyway...
        advanceTime();

        /* 
           Randomly choose what type of event will be made.

           There are only three choices: 
           0 - Demographics
           1 - Transfers
           2 - New Loan Contract
        */
        
        // These calls to produceEvents() will queue one or more events on the event queue for the next time
        // the next() method is called.
        int randomChoice = (int)(Math.random()*3);

        switch(randomChoice)
        {
            case 0:
                customerDemographicsModule.produceEvents();
                break;

            case 1:
                transfersModule.produceEvents();
                break;

            case 2:
                loanModule.produceEvents();
                break;
            
        }

        return next();
    }

    public void queueEvent(Event event)
    {
        eventQueue.add(event);
    }

    // Starts a new day
    public void newDay() 
    {        
        currentEventsDay = 0;
        currentCalendarDate.add(Calendar.DAY_OF_YEAR, 1);
        currentCalendarDate.set(Calendar.HOUR_OF_DAY, 0);

        // verify if any loans have to be paid. This does not advance time. 
        // Many loan payment events will be queued with the same time as now

        loanModule.dailyChecks();
        customerDemographicsModule.dailyChecks();
        transfersModule.dailyChecks();
    }

    public Calendar getCurrentCalendarDate()
    {
        return this.currentCalendarDate;
    }

    // advance time in current day
    public void advanceTime() 
    {
        currentCalendarDate.add(Calendar.MILLISECOND, this.millisBetweenEvent);
    }

    public Customer getRandomCustomer() {
        return allCustomers[(int) (Math.random() * (allCustomers.length - 1))];
    }

    public Bank getBank()
    {
        return this.bank;
    }


}