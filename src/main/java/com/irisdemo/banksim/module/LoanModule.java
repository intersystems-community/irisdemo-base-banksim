package com.irisdemo.banksim.module;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.ListIterator;

import com.irisdemo.banksim.Simulator;
import com.irisdemo.banksim.Util;
import com.irisdemo.banksim.event.DemographicsEvent;
import com.irisdemo.banksim.event.Event;
import com.irisdemo.banksim.event.LoanContractEvent;
import com.irisdemo.banksim.event.TransferEvent;
import com.irisdemo.banksim.model.Bank;
import com.irisdemo.banksim.model.Customer;
import com.irisdemo.banksim.model.LoanContract;

public class LoanModule extends Module {

    public LoanModule(Simulator simulator, double probability) 
    {
        super(simulator, probability);
    }
    
    private Integer[] loanLengths = {3,6,9,12,18,24};
    private LinkedList<LoanContract> activeLoanContracts = new LinkedList<LoanContract>();

    public void produceEvents()
    {
        Customer customer;
        Bank bank;
        LoanContract loanContract;
        double amount;
        double paymentSize;
        LoanContractEvent loanContractEvent;
        TransferEvent transferEventOut;
        TransferEvent transferEventIn;
        Calendar dueDate;

        if (Math.random()>probability)
        {
            // Make sure we are picking two different random customers.
            customer = simulator.getRandomCustomer();
            bank = simulator.getBank();
            amount = (int) (Math.random() * 10000);

            paymentSize = amount/loanLengths[(int)Math.round(Math.random()*(loanLengths.length-1))];

            dueDate = (Calendar) this.simulator.getCurrentCalendarDate().clone();

            dueDate.add(Calendar.DAY_OF_YEAR,1);
            loanContract = new LoanContract(customer, bank, amount, dueDate.get(Calendar.DAY_OF_MONTH), paymentSize);
            activeLoanContracts.add(loanContract);

            loanContractEvent = new LoanContractEvent(simulator.getCurrentCalendarDate(), loanContract);

            // Now the transactions for money transfer:
            bank.addBalance(-amount);
            customer.addBalance(amount);

            transferEventOut = new TransferEvent(simulator.getCurrentCalendarDate(), "BANK_LOAN_OUT", bank, customer, -amount, "");
            transferEventIn = transferEventOut.createInverse();
            
            simulator.queueEvent(loanContractEvent);
            simulator.queueEvent(transferEventOut);
            simulator.queueEvent(transferEventIn);
        }
    }

    public void dailyChecks() 
    {
        LoanContract loanContract;
        TransferEvent paymentEventOut;
        TransferEvent paymentEventIn;

        // Once a day, go over every loanContract and check if they're due.
        ListIterator<LoanContract> loanIterator = activeLoanContracts.listIterator();

        while (loanIterator.hasNext()) 
        {
            loanContract = loanIterator.next();

            if (loanContract.dueToday(simulator.getCurrentCalendarDate()))
            {
                loanContract.makePayment();

                // ADD LOAN PAYMENT EVENT TO EVENT QUEUE
                paymentEventOut = new TransferEvent(simulator.getCurrentCalendarDate(), "LOAN_PAYMENT_OUT", loanContract.getBorrower(), simulator.getBank(), -loanContract.getPaymentSize(), loanContract.getReference());
                simulator.queueEvent(paymentEventOut);                    

                paymentEventIn = paymentEventOut.createInverse();
                simulator.queueEvent(paymentEventIn);                    

                // if the loanContract has been succesfully paid off, remove it from the activeLoanContracts Linked List
                if (loanContract.isComplete()) 
                {
                    loanIterator.remove();
                }

            }
        }
    }
}