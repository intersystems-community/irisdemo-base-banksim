package src.com.BlackBox;

import java.util.Calendar;
import java.util.Scanner; 



public class Test {
    public static void main(String [] args)
    {

        int amountDays = Integer.parseInt(args[0]);
        int amountEvents = Integer.parseInt(args[1]);
        int amountCustomers = Integer.parseInt(args[2]);

        Simulator blackBox = new Simulator(amountDays, amountEvents, amountCustomers);
        Event event = blackBox.next();
        Calendar day = event.getDate();
        Scanner obj = new Scanner(System.in);
        while(event instanceof Event)
        {
            System.out.println(event.getInfo());
            if (event.getDate().get(Calendar.DAY_OF_YEAR) != day.get(Calendar.DAY_OF_YEAR))
            {
                String a = obj.nextLine();
            }
            event = blackBox.next();
            
            
            

        }


    }
}