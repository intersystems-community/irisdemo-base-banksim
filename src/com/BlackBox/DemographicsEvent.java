package src.com.BlackBox;

import java.util.Calendar;


public class DemographicsEvent implements Event {
 
    public Calendar dateCreated;
    public Customer customer;
    public String demographicsField;
    public String prevValue;
    public String newValue;

    public DemographicsEvent(Calendar dateCreated, Customer customer, String demographicsFieldChanged, String prevValue, String newValue)
    {
        this.dateCreated = (Calendar)dateCreated.clone();;
        this.customer = customer;
        this.demographicsField = demographicsFieldChanged;
        this.prevValue = prevValue;
        this.newValue = newValue;
    }

    public Calendar getDate()
    {
        return dateCreated;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public String getField()
    {
        return demographicsField;
    }

    public String getPrevious()
    {
        return prevValue;
    }

    public String getNew()
    {
        return newValue;
    }

    public String getInfo()
    {
        return "-----------------\nDEMOGRAPHIC EVENT \nDate: "+ getDate().getTime() + "\nCustomer: " + getCustomer() +"\nField: " + getField()+"\nPrevious: "
        + getPrevious() +"\nNew: " + getNew();
    }
}