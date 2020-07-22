package src.com.BlackBox;

import java.util.Calendar;

public class TransferEvent implements Event{
    
    public Calendar dateCreated;
    public Bank bank;
    public Customer receiver;
    public Customer sender;
    public float amount;

    public TransferEvent(Calendar dateCreated, Customer sender, Customer receiver, float amount)
    {
        this.dateCreated = dateCreated;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public TransferEvent(Calendar dateCreated, Bank bank, Customer receiver, float amount)
    {
        this.dateCreated = dateCreated;
        this.bank = bank;
        this.receiver = receiver;
        this.amount = amount;
    }

    public TransferEvent(Calendar dateCreated, Customer sender, Bank bank, float amount)
    {
        this.dateCreated = dateCreated;
        this.bank = bank;
        this.bank = bank;
        this.amount = amount;
    }


    public Calendar getDate()
    {
        return dateCreated;
    }
    
}