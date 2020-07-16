package src.com.BlackBox;

import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;

import org.omg.CORBA.Current;


public class Simulator {
    public Customer[] allCustomers;
    public Bank bank;
    private LinkedList<LoanContract> loansList = new LinkedList<>();
    private Date currentDate;
    private int eventsPerDay;
    private int totalEvents;
    private int numberOfEventsAll;
    private int currentEventsDay = 0;
    private boolean newLoan = false;
    private LinkedList<Event> eventQueue = new LinkedList<>();
    private boolean loansChecked;

    private String[] eventTypes = { "payment", "loan" };

    public Simulator(int amountDays, int amountEvents, int amountCustomers) {

        // initialize state machine
        long DAY_MS = 24 * 60 * 60 * 1000;
        currentDate = new Date(System.currentTimeMillis() - (amountDays * DAY_MS));
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

            Account customerAccount = new Account(Math.random() * 100000,
                    accountNumber.substring(accountNumber.length() - 8));
            allCustomers[i] = new Customer(customerAccount, name, state, city, phoneNumber);
        }
        // Bank
        bank = new Bank(10000000, "00000000");

    }

    public Event next()
    {
        //Base case. Return null/Special No Event if we have already finished the total required events/reached the expected date
        if (totalEvents == numberOfEventsAll)
        {
            return null;
        }



        checkLoansDay();
        //If we have just created a new loan, now we need to send a transaction event. This transaction event is sent from the queue.
        //Or if we added multiple loan payments to the queue, and we want to see them reflected.
        if(!eventQueue.isEmpty())
        {
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
                //RETURN LOAN EVENT
                return null;
                    

                //Payment
                case 2:
                //CREATE NEW TRANSFER EVENT FROM PERSON1 TO PERSON2
                //RETURN TRANSFER EVENT
                return null;

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
                if (loan.dueToday(currentDate))
                {
                    if (loan.makePayment())
                    {
                        //DO LOAN PAYMENT EVENT WITH SUCCESS
                        totalEvents+=2;
                        currentEventsDay+=2;
                    }
                    else
                    {
                        //DO FAIL LOAN EVENT????
                        totalEvents++;
                        currentEventsDay++;
                    }
                }
            }
            loansChecked = true;
        }
    }

    //Starts a new day
    public void newDay()
    {
        currentEventsDay = 0;
        loansChecked = false;
        currentDate.setDate(currentDate.getDate()+1);

        
    }









}