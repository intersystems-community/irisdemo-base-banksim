package src.com.BlackBox;

import java.util.Calendar;

public class Event {
    public Calendar calendarDateMade;
    public String eventDetails;


    public Event (Calendar dateMade, String eventDetails)
    {
        this.calendarDateMade = dateMade;
        this.eventDetails = eventDetails;
    }

    // public JSONObject toJSON()
    // {

    // }
}