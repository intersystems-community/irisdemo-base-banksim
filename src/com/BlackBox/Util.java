package src.com.BlackBox;

public class Util {


    //reused code from restm2 demo
    private static final String[] FIRST_NAMES = {"Andrew", "Bob", "Bill", "Charles" , "Cleo", "Dina", "Daniel", "Elbert", "Eustace", "Francesca", 
                                                "Gabriel", "Gloria","Harold", "Hue", "Isabella","Iris", "Isaac", "Johnathan", "Juana", "Jack", "Kathryn", 
                                                "Luis", "Mike", "Nora", "Norman", "Oswald", "O'Reilley", "Patty", "Peter","Quinn",
                                                "Robert", "Sandy", "Trish", "Taylor", "Umar", "Vivian", "Waldo", "Xavier", "Yusef", "Zoe"};
                                                
	private static final String[] LAST_NAMES = {"Aranda", "Brooks", "Campbell", "Dunn", "Eparvier", "Fabregas", "Gump", "Hernandez", "Iacobelli",
												"Jackson", "Johnson", "Kringle", "Lopez", "Maldonado", "Newman", "Oppenheimer" , "Packer", "Quintana",
												"Robertson", "Sacher", "Tadema", "Ubacke", "Valdez", "Wright", "Xanders", "Yapp", "Zafra"};
	private static final String[] STATES = {"AL", "AK", "AZ" , "AR" ,"CA", "CO", "CT", "DE" , "FL" ,"GA", "HI", "ID", "IL" , "IN" ,"IA", "KS", "KY", "LA" , "ME" ,"MD", "MA", "MI", "MN" , "MO" ,"MT", 
											"NE", "NV", "NH" , "NJ" ,"NM", "NY", "NC", "ND" , "OH" ,"OK", "OR", "PA", "RI" , "SC" ,"SD", "TN", "TX", "UT" , "VT" ,"VA", "WA", "WV", "WI" , "WY" ,"PR" };
    
	
	private static final String [] CITY = {"Boston", "Jacksonville", "Orlando", "Charlston", "San Francisco", "Cambridge", "New Cambridge", "York", "San Juan", "London", "Gondor",
                                            "Isengard", "San Fransokyo", "Tokyo", "Berlin", "New Boston", "Guaynabo", "Lima"};
                                            

    private static String NUMERIC_STRING =  "0123456789";


    //using randomAlphaNumeric from restm2 demo

    public static String randomAlphaNumeric(int count) 
    {
        final StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            final int character = (int) (Math.random() * NUMERIC_STRING.length());
            builder.append(NUMERIC_STRING.charAt(character));
        }

        return builder.toString();
    }

    public static String getRandomName() {
        final String name = FIRST_NAMES[(int) Math.round(Math.random() * (FIRST_NAMES.length - 1))] + " "
                + LAST_NAMES[(int) (Math.random() * (LAST_NAMES.length - 1))];
        return name;
    }

    public static String getRandomState() {
        return STATES[(int) Math.round(Math.random() * (STATES.length - 1))];
    }

    public static String getRandomCity() {

        return CITY[(int) Math.round(Math.random() * (CITY.length - 1))];
    }

    public static String getRandomPhoneNumber() {
        final String phoneNumber = randomAlphaNumeric(3) + randomAlphaNumeric(3) + randomAlphaNumeric(4);
        return phoneNumber;
    }

    public static float getRandomTransferAmount(final Customer loaner) {
        final float maxAmount = loaner.account.getBalance();
        //getting random amount, multiplied by a Random number between 1 and 2 to allow for possibillity of asking for too much money.
        return (float)((Math.random()*maxAmount)*(1.0+Math.random()));
    }
}