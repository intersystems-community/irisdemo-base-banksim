package com.irisdemo.banksim.model;

public class Bank extends Customer
{
    public Bank(double initialMoney)
    {
        super("00000000", initialMoney, "SimBank", "MA", "Boston", "555-515 5656");
    }

}