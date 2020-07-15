package src.com.BlackBox;

import java.util.Date;


public class Simulator
{   
    public Customer[] allCustomers;
    public Bank bank;
    private LoanContract[] loans;
    private Date currentDate;
    private int eventsPerDay;
    private int eventsLeft;

    private String[] eventTypes = {"payment" ,"loan"};



    public Simulator(int amountDays, int amountEvents, int amountCustomers)
    {

        //initialize state machine
        long DAY_MS = 24*60*60*1000;
        currentDate = new Date(System.currentTimeMillis() - (amountDays * DAY_MS));
        eventsPerDay = amountEvents/amountDays;
        eventsLeft = amountEvents;
        allCustomers = new Customer[amountCustomers];
        

        //initial state for Customers/Bank/Accounts/Loans
        //customers
        for(int i=1; i <= amountCustomers; i++)
        {
            String accountNumber = String.format("%07d" , i);
            String name = Util.getRandomName();
            String state = Util.getRandomState();
            String city = Util.getRandomCity();
            String phoneNumber = Util.getRandomPhoneNumber();


            Account customerAccount = new Account(Math.random()*100000, accountNumber.substring(accountNumber.length()-8));
            allCustomers[i] = new Customer(customerAccount, name, state, city, phoneNumber);
        }
        //Bank
        bank = new Bank(10000000, "00000000");

    }


    //Continues the simulation until the day is over.
    public void simulateDay()
    {
        int currentEvents = 0;
        while (currentEvents != eventsPerDay)
        {
            switch ((int)(Math.random()*(eventTypes.length-1)))
            {
                //Loan
                case 1:

                //Payment
                case 2:

            }
            

        }



        //at the end of the day, go over every loan and check if they're due.
        for (LoanContract loan : loans) 
        {
            if (loan.dueToday(currentDate))
            {
                if (loan.makePayment())
                {
                    //DO LOAN PAYMENT EVENT WITH SUCCESS
                }
                else
                {
                    //DO LOAN PAYMENT EVENT WITH FAIL
                }
            }
        }


        
    }









}