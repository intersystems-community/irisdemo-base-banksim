package com.irisdemo.banksim;

public class Identifyable {
    private static long idCounter = 0;

    private long id;

    public long getId()
    {
        return this.id;
    }

    public Identifyable()
    {
        this.id = getNextId();
    }

    public synchronized long getNextId()
    {
        return ++idCounter;
    }
}