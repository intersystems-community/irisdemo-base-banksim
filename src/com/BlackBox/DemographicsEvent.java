package src.com.BlackBox;

import java.util.Calendar;

public class DemographicsEvent implements Event {
 
    public Calendar dateCreated;
    public Customer customer;
    public String demographicsField;

    public DemographicsEvent(Calendar dateCreated, Customer customer, String demographicsFieldChanged)
    {
        this.dateCreated = dateCreated;
        this.customer = customer;
        this.demographicsField = demographicsFieldChanged;
    }

    public Calendar getDate()
    {
        return dateCreated;
    }
}