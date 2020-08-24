package com.irisdemo.banksim.module;

import com.irisdemo.banksim.Simulator;
import com.irisdemo.banksim.event.Event;

public abstract class Module 
{
    protected double probability;
    protected Simulator simulator;

    public Module(Simulator simulator, double probability)
    {
        this.simulator = simulator;
        this.probability=probability;
    } 
    
    public abstract void produceEvents() throws Exception;

    public abstract void dailyChecks() throws Exception;

}