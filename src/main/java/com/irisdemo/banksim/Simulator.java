package com.irisdemo.banksim;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.ListIterator;

public class Simulator 
{
    private Customer[] allCustomers;
    private Bank bank;
    private LinkedList<LoanContract> loansList = new LinkedList<>();
    private Calendar currentCalendarDate;
    private int maxEventsPerDay;
    private int totalEvents;
    private int maxNumberOfEvents;
    private int currentEventsDay = 0;
    private int millisBetweenEvent;
    private LinkedList<Event> eventQueue = new LinkedList<>();
    private boolean loansChecked;
    private double probabilityTransfer = .45;
    private double probabilityDemographics = .20;
    private double probabilityLoanContract = .5;

    private String[] demographics = { "State", "City", "Phone Number" };

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

        // Customers and Account (This simulator only allows one account per customer for now)
        for (int i = 1; i < amountCustomers; i++) 
        {
            String accountNumber = String.format("%07d", i);
            String name = Util.getRandomName();
            String state = Util.getRandomState();
            String city = Util.getRandomCity();
            String phoneNumber = Util.getRandomPhoneNumber();

            allCustomers[i-1] = new Customer(accountNumber, (float) (Math.random() * 100000), name, state, city, phoneNumber);
        }

        // Initial balance of 10 billion
        bank = new Bank(10000000000d);

    }

    public Event next() 
    {
        // Base case. Return null if we have already finished the total
        // required events/reached the expected date
        if (totalEvents == maxNumberOfEvents) {
            return null;
        }

        // verify if any loans have to be paid. This does not advance time. 
        // Many loan payment events will be queued with the same time as now
        checkLoansDay();

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
        
        Event event = null;
        Customer randomCustomer1 = null;
        Customer randomCustomer2 = null;

        while (event == null)
        {
            int randomChoice = (int)(Math.random()*3);

            switch(randomChoice)
            {
                // Demographics
                case 0:
                    if (Math.random()>probabilityDemographics)
                    {
                        // Pick a random customer
                        randomCustomer1 = getRandomCustomer();

                        // Apply a random demographics change
                        event = demographicsEventMaker(randomCustomer1);
                    }
                    break;

                // Transfer
                case 1:
                    if (Math.random()>probabilityTransfer)
                    {
                        // Make sure we are picking two different random customers.
                        do
                        {
                            randomCustomer1 = getRandomCustomer();
                            randomCustomer2 = getRandomCustomer();
                        }
                        while (randomCustomer1==randomCustomer2);
            
                        event = transferEventMaker(randomCustomer1, randomCustomer2);
                    }
                    break;

                // New Loan Contract
                case 2:
                    if (Math.random()>probabilityLoanContract)
                    {
                        randomCustomer1 = getRandomCustomer();
                        float randomAmount = (float) (Math.random() * 10000);
                        event = loanEventMaker(randomCustomer1, randomAmount); 
            
                    }
                    break;
                
            } // switch case
        } // while

        totalEvents++;
        currentEventsDay++;

        return event;
    }

    public void checkLoansDay() 
    {
        LoanContract loan;
        TransferEvent paymentEvent;

        // Once a day, go over every loan and check if they're due.
        if (!loansChecked) 
        {
            ListIterator<LoanContract> loanIterator = loansList.listIterator();

            while (loanIterator.hasNext()) 
            {
                loan = loanIterator.next();

                if (loan.dueToday(this.currentCalendarDate)) 
                {
                    loan.makePayment();

                    // ADD LOAN PAYMENT EVENT TO EVENT QUEUE
                    try
                    {
                        paymentEvent = new TransferEvent(this.currentCalendarDate, loan.getBorrower(), bank, loan.getPaymentSize());
                        eventQueue.add(paymentEvent);
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("WRONG INPUT TYPE FOR SENDER OR RECEIVER");
                    }
                    

                    // if the loan has been succesfully paid off, remove it from the loan Linked
                    // List and make a Loan Complete Event
                    if (loan.isComplete()) {
                        loansList.remove(loan);
                    }

                }
            }
            loansChecked = true;
        }
    }

    public Event loanEventMaker(Customer loanee, float amount) {
        LoanContractEvent loanEvent = null;
        TransferEvent loanTransfer = null;

        // Check if bank can make a loan of this amount. Otherwise LOAN failed event
        if (bank.enoughBalance(amount)) 
        {
            // 2 Events. Loan Created, Loan Made (transfer).

            // First, create the loan event that is sent out to the system
            loanEvent = new LoanContractEvent(currentCalendarDate, loanee, amount);
            // Second, add to the eventqueue a transfer event from the bank to the customer.
            
            try
            {
                bank.addBalance(-amount);
                loanee.addBalance(amount);
                loanTransfer = new TransferEvent(currentCalendarDate, bank, loanee, amount);
            }
            catch (InputMismatchException e)
            {
                System.out.println("WRONG INPUT TYPE FOR SENDER OR RECEIVER");
                return null;
            }

            // If we are here is because we have both events built without problems. 
            // Let's queue the transfer and return the loan contract
            eventQueue.add(loanTransfer);
            return loanEvent;

        } 
        else 
        {
            // if the bank doesnt have enough money, try another event
            return null;
        }

    }

    public Event transferEventMaker(Customer sender, Customer receiver) {

        double amount = Util.getRandomTransferAmount(sender);

        // Verify if transfer is doable. If so, create and return the transfer event.
        // Otherwise, maybe take a loan and make the transfer.
        if (sender.enoughBalance(amount)) 
        {
            try
            {
                sender.addBalance(-amount);
                receiver.addBalance(amount);
                TransferEvent transferEvent = new TransferEvent(currentCalendarDate, sender, receiver, amount);
                return transferEvent;
            }
            catch (InputMismatchException e)
            {
                System.out.println("WRONG INPUT TYPE FOR SENDER OR RECEIVER");
                return null;
            }

        } 
        else 
        {
            // if there's no money, just try another event
            return null;
        }
 
    }

    public Event demographicsEventMaker(Customer customer) 
    {
        int choice = (int) (Math.random() * 2);

        switch (choice) 
        {
            case 0:
                customer.setState(Util.getRandomState());
                break;

            case 1:
                customer.setCity(Util.getRandomCity());
                break;

            case 2:
                customer.setPhoneNumber(Util.getRandomPhoneNumber());
                break;

            default:
                return null;
        }

        return new DemographicsEvent(currentCalendarDate, customer);

    }

    // Starts a new day
    public void newDay() 
    {        
        currentEventsDay = 0;
        loansChecked = false;
        currentCalendarDate.add(Calendar.DAY_OF_YEAR, 1);
        currentCalendarDate.set(Calendar.HOUR_OF_DAY, 0);
    }

    // advance time in current day
    public void advanceTime() 
    {
        currentCalendarDate.add(Calendar.MILLISECOND, this.millisBetweenEvent);
    }

    public Customer getRandomCustomer() {
        return allCustomers[(int) (Math.random() * (allCustomers.length - 1))];
    }


}