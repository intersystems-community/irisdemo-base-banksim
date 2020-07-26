package com.irisdemo.banksim.event;

import java.util.Calendar;
import org.apache.avro.specific.SpecificRecordBase;
import java.text.SimpleDateFormat;

public abstract class Event 
{
    private static long idCounter = 0;

    private Calendar eventDate;
    private long id;
    private static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public Event(Calendar eventDate)
    {
        this.id = getNextId();
        this.eventDate = (Calendar)eventDate.clone();
    }

    private synchronized long getNextId()
    {
        return ++this.idCounter;
    }

    public Calendar getEventDate()
    {
        return this.eventDate;
    }

    public String getExternalEventDate()
    {
        return dateTimeFormatter.format(this.eventDate.getTimeInMillis());
    }

    public long getId()
    {
        return this.id;
    }

    public void displayInfo()
    {
        System.out.println("Event type    : " + getClass().getName());
        System.out.println("Event id      : " + getId());
        System.out.println("Event Date    : " + getExternalEventDate());
    }

    public abstract org.apache.avro.specific.SpecificRecordBase getAvroEvent();
}