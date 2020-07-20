package src.com.BlackBox;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.lang.model.util.ElementScanner6;

import org.omg.CORBA.Current;


public class Simulator {
    public Customer[] allCustomers;
    public Bank bank;
    private LinkedList<LoanContract> loansList = new LinkedList<>();
    private Calendar currentCalendarDate;
    private int eventsPerDay;
    private int totalEvents;
    private int numberOfEventsAll;
    private int currentEventsDay = 0;
    private LinkedList<Event> eventQueue = new LinkedList<>();
    private boolean loansChecked;

    private String[] eventTypes = { "payment", "loan","demographics" };

    public Simulator(int amountDays, int amountEvents, int amountCustomers) {

        // initialize state machine
        currentCalendarDate = Calendar.getInstance();
        currentCalendarDate.set(2020, 06, 15, 0, 0, 0);
        eventsPerDay = amountEvents / amountDays;
        numberOfEventsAll = amountEvents;
        allCustomers = new Customer[amountCustomers];

        // initial state for Customers/Bank/Accounts/Loans
        // customers
        for (int i = 1; i <= amountCustomers; i++) {
            String accountNumber = String.format("%07d", i);
            String name = Util.getRandomName();
            String state = Util.getRandomState();
            String city = Util.getRandomCity();
            String phoneNumber = Util.getRandomPhoneNumber();

            Account customerAccount = new Account((float)(Math.random() * 100000),
                    accountNumber.substring(accountNumber.length() - 8));
            allCustomers[i] = new Customer(customerAccount, name, state, city, phoneNumber);
        }
        // Bank
        bank = new Bank(10000000, "00000000");

    }

    
    
    
    public Event next()
    {
        //advance current time to current system time, but for the current date.
        advanceTime();

        //Base case. Return null/Special No Event if we have already finished the total required events/reached the expected date
        if (totalEvents == numberOfEventsAll)
        {
            return new Event(currentCalendarDate, "DONE!");
        }


        //verify if any loans have to be paid
        checkLoansDay();

        //If we have just created a new loan payment event, now we need to send a transaction event. This transaction event is sent from the queue.
        //Or if we added multiple loan payments to the queue, and we want to see them reflected.
        if(!eventQueue.isEmpty())
        {
            totalEvents++;
            currentEventsDay++;
            return eventQueue.pop();
        }

        //Now verify if we have completed all the required events for the day.
        else if(currentEventsDay == eventsPerDay)
        {
            newDay();
            return next();
        }

        //Otherwise, we need to send out a new event, and we randomly pick which one we'll do.
        else{
            //Randomly choose what type of event will be made.
            switch ((int)(Math.random()*(eventTypes.length-1)))
            {
                //Loan
                case 1:
                //CREATE LOAN EVENT
                //CREATE NEW TRANSFER EVENT FROM BANK TO PERSON
                //ADD TRANSFER EVENT TO EVENTQUEUE
                //RETURN LOAN EVENT
                Customer customerLoanTaker = allCustomers[(int)(Math.random()*(allCustomers.length-1))];
                float randomAmount = (float)(Math.random() * 10000);
                return loanEventMaker(customerLoanTaker, randomAmount);
            
                    

                //Payment
                case 2:

                //CREATE NEW TRANSFER EVENT FROM PERSON1 TO PERSON2
                Customer customerRecipient = allCustomers[(int)(Math.random()*(allCustomers.length-1))];
                Customer customerSender = allCustomers[(int)(Math.random()*(allCustomers.length-1))];

                //Make sure customers are different
                while (customerRecipient == customerSender)
                {
                    customerSender = allCustomers[(int)(Math.random()*(allCustomers.length-1))];
                }
                return transferEventMaker(customerSender, customerRecipient);
                
                

                //Demographics change
                case 3:
                //CREATE NEW DEMOGRAPHICS CHANGE EVENT

                //RETURN DEMOGRAPHICS CHANGE EVENT
                Customer customerDemographics = allCustomers[(int)(Math.random()*(allCustomers.length-1))];
                return demographicsEventMaker(customerDemographics);

                

            }
            
            
        }
        
    }

    public void checkLoansDay()
    {
        
        //Once a day, go over every loan and check if they're due.
        if(!loansChecked)
        {
            ListIterator<LoanContract> loanIterator = loansList.listIterator();
            while(loanIterator.hasNext())
            {
                LoanContract loan = loanIterator.next();
                if (loan.dueToday(currentCalendarDate))
                {
                    if (loan.makePayment())
                    {
                        //ADD LOAN PAYMENT EVENT TO EVENT QUEUE
                        Event paymentEvent = new Event(currentCalendarDate, "Loan Payment from "+loan.getBorrowerAccount());
                        eventQueue.add(paymentEvent);

                        //if the loan has been succesfully paid off, remove it from the loan Linked List and make a Loan Complete Event
                        if (loan.isComplete())
                        {
                            loansList.remove(loan);
                            Event loanCompleteEvent = new Event(currentCalendarDate, "Loan Payment complete from "+loan.getBorrowerAccount());
                            eventQueue.add(loanCompleteEvent);
                        }

                    }
                    else
                    {
                        //ADD LOAN FAIL PAYMENT EVENT TO QUEUE???
                    }
                }
            }
            loansChecked = true;
        }
    }


    public Event loanEventMaker(Customer loanee, float amount)
    {
        //Check if bank can make a loan of this amount. Otherwise LOAN failed event
        if (bank.enoughBalance(amount))
        {
            //2 Events. Loan Created, Loan Made (transfer). 

            //First, create the loan event that is sent out to the system
            Event loanEvent = new Event(currentCalendarDate, "Loan Created from Bank to: " + loanee.getAccountNumber());
            //Second, add to the eventqueue a transfer event from the bank to the customer.
            bank.addBalance(-amount);
            loanee.addBalance(amount);
            Event loanTransfer = new Event(currentCalendarDate, "Loan of "+amount+"made to account#" + loanee.getAccountNumber());
            eventQueue.add(loanTransfer);
            return loanEvent;
        }
        else
        {
            //Make a loan failure event here.

        }

    }

    public Event transferEventMaker(Customer sender, Customer receiver)
    {
        
        float amount = Util.getRandomTransferAmount(sender);
                    
        //Verify if transfer is doable. If so, create and return the transfer event. Otherwise, maybe take a loan and make the transfer.
        if(sender.enoughBalance(amount))
        {
            sender.addBalance(-amount);
            receiver.addBalance(amount);
            Event transferEvent = new Event(currentCalendarDate, "transfer between :" );
            return transferEvent;
        }
        else
        {
            //making it a 10% chance the person takes the loan
            double chanceLoan = 1.0/10.0;
            if(Math.random() < chanceLoan)
            {  
                


            }
            else
            {
                return next();
            }
        }

    }


    public Event demographicsEventMaker(Customer customerDemographicsChange)
    {

    }


    //UTILITY CLASSES TO DEAL WITH PASSAGE OF TIME
    //Starts a new day
    public void newDay()
    {
        currentEventsDay = 0;
        loansChecked = false;
        currentCalendarDate.add(Calendar.DAY_OF_MONTH, 1);
        currentCalendarDate.set(Calendar.HOUR_OF_DAY, 0);
        currentCalendarDate.set(Calendar.MINUTE, 0);
        currentCalendarDate.set(Calendar.SECOND, 0);   
    }


    //advance time in current day
    public void advanceTime()
    {
        long currentMillis = System.currentTimeMillis();
        currentCalendarDate.set(Calendar.SECOND, (int)currentMillis/1000);
        currentCalendarDate.set(Calendar.MINUTE, (int)(currentMillis/1000)/60);
        currentCalendarDate.set(Calendar.HOUR, (int)((currentMillis/1000)/60)/60);
    }



}









