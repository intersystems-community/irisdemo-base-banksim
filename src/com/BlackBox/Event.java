package src.com.BlackBox;

import java.util.Date;

public class Event {
    private Date dateMade;
    private String eventDetails;


    public Event (Date dateMade, String eventDetails)
    {
        this.dateMade = dateMade;
        this.eventDetails = eventDetails;
    }
}