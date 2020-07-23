package src.com.BlackBox;

import java.util.Calendar;
import java.util.InputMismatchException;


public class TransferEvent implements Event{
    
    public Calendar dateCreated;
    public Object receiver;
    public Object sender;
    public float amount;

    public TransferEvent(Calendar dateCreated, Object sender, Object receiver, float amount) throws InputMismatchException
    {

        //Throws Exception if object type is incorrect. Want to allow multiple object types withour repeating code.
        if(sender instanceof Customer || sender instanceof Bank)
        {
            this.sender = sender;
        }
        else
        {
            throw new InputMismatchException();
        }
        if(receiver instanceof Customer || receiver instanceof Bank)
        {
            this.receiver = receiver;
        }
        else
        {
            throw new InputMismatchException();
        }

        this.dateCreated = dateCreated;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }



    public Calendar getDate()
    {
        return dateCreated;
    }
    public Object getSender()
    {
        return sender;

        
    }
    public Object getReceiver()
    {
        return receiver;
    }

    public float getAmountSent()
    {
        return amount;
    }

    public String getInfo()
    {
        return "-----------------\nTRANSFER EVENT \nDate: "+ getDate() + "\nSender: " + getSender() +"\neceiver: " + getReceiver()+"\nAmount: "
        + getAmountSent();
    }
    
}