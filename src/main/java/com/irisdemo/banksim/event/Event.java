package com.irisdemo.banksim.event;

import java.util.Calendar;
import org.apache.avro.specific.SpecificRecordBase;
import java.text.SimpleDateFormat;
import com.irisdemo.banksim.Identifyable;

public abstract class Event extends Identifyable
{
    private static long idCounter = 0;

    private Calendar eventDate;
    private static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public Event(Calendar eventDate)
    {
        super();

        this.eventDate = (Calendar)eventDate.clone();
    }

    public Calendar getEventDate()
    {
        return this.eventDate;
    }

    public String getExternalEventDate()
    {
        return dateTimeFormatter.format(this.eventDate.getTimeInMillis());
    }

    public abstract long getPartitionKey();

    public void displayInfo()
    {
        System.out.println("Event type    : " + getClass().getName());
        System.out.println("Event id      : " + getId());
        System.out.println("Event Date    : " + getExternalEventDate());
    }

    public abstract org.apache.avro.specific.SpecificRecordBase getAvroEvent();
}