package com.irisdemo.banksim.event;

public class IdentifyableEvent 
{
    private static long idCounter = 0;

    private long id;

    public long getId()
    {
        return this.id;
    }

    public IdentifyableEvent()
    {
        this.id = ++idCounter;
    }

}