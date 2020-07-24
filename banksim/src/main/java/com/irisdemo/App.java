package com.irisdemo;

import com.irisdemo.banksim.*;

import java.util.Calendar;
import java.util.Scanner; 

public class App
{
    public static void main(String [] args)
    {
        int amountDays = Integer.parseInt(args[0]);
        int amountEvents = Integer.parseInt(args[1]);
        int amountCustomers = Integer.parseInt(args[2]);
        String pauseInBetweenDays = args[3];

        long eventCounter = 0;

        Simulator simulator = new Simulator(amountDays, amountEvents, amountCustomers);

        // Let's take the first event
        Event event = simulator.next();

        // Let's store the day of the first event - we will pause in between days
        int currentDay = event.getEventDate().get(Calendar.DAY_OF_YEAR);

        // Let's print the events
        while(event != null)
        {
            // Should we pause before displaying this next event?
            if (event.getEventDate().get(Calendar.DAY_OF_YEAR) != currentDay)
            {
                // Change of day. Let's pause
                currentDay = event.getEventDate().get(Calendar.DAY_OF_YEAR);
                if (pauseInBetweenDays.equals("pause"))
                {
                    pause();
                }
            }

            eventCounter++;
            System.out.println(String.format("\n--------------------------------\rEvent n:%s ", String.valueOf(eventCounter)));
            event.displayInfo();

            // Get next event
            event = simulator.next();
        }
    }

    private static void pause()
    {
        System.console().readLine();
    }
}