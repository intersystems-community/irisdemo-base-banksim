package src.com.BlackBox;

public class Test {
    public static void main(String [] args)
    {

        int amountDays = Integer.parseInt(args[0]);
        int amountEvents = Integer.parseInt(args[1]);
        int amountCustomers = Integer.parseInt(args[2]);

        Simulator blackBox = new Simulator(amountDays, amountEvents, amountCustomers);
        Event event = blackBox.next();
        while(event instanceof Event)
        {
            System.out.println(event.getInfo());
            event = blackBox.next();

        }


    }
}