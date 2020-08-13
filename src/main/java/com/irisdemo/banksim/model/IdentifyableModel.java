package com.irisdemo.banksim.model;

public class IdentifyableModel 
{
    private static long idCounter = 0;

    private long id;

    public long getId()
    {
        return this.id;
    }

    public IdentifyableModel()
    {
        this.id = ++idCounter;
    }

}