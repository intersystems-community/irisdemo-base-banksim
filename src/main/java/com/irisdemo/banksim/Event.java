package com.irisdemo.banksim;

import java.util.Calendar;

public class Event 
{
    private Calendar eventDate;
    
    public Event(Calendar eventDate)
    {
        this.eventDate = (Calendar)eventDate.clone();
    }

    public Calendar getEventDate()
    {
        return this.eventDate;
    }

    public void displayInfo()
    {
        System.out.println("Event type    : " + getClass().getName());
        System.out.println("Event Date    : " + getEventDate().getTime());
    }
}