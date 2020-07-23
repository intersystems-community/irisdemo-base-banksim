package src.com.BlackBox;

import java.util.Calendar;

public class LoanContractEvent implements Event {
    public Calendar dateCreated;
    public Customer customer;
    public float amountLoaned;

    public LoanContractEvent(Calendar dateCreated, Customer customer, float amountLoaned)
    {
        this.dateCreated = (Calendar)dateCreated.clone();
        this.customer = customer;
        this.amountLoaned = amountLoaned;
    }

    public Calendar getDate()
    {
        return dateCreated;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public float getAmountLoaned()
    {
        return amountLoaned;
    }

    public String getInfo()
    {
        return "-----------------\nLOAN CONTRACT EVENT EVENT \nDate: "+ getDate().getTime() + "\nCustomer: " + getCustomer() +"\nAmount Loaned: " + getAmountLoaned();

    }
}