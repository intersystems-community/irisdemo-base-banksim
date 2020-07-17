package src.com.BlackBox;


public class Customer
{

    public Account account;
    private String name;
    private String state;
    private String city;
    private String phoneNumber;

    
    public Customer(Account account, String name, String state, String city, String phoneNumber)
    {
        this.name = name;
        this.state = state;
        this.account = account;
        this.phoneNumber = phoneNumber;
    }






    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }


    public String getState()
    {
        return state;
    }
    public void setState(String state)
    {
        this.state = state;
    }


    public String getCity()
    {
        return city;
    }
    public void setCity(String city)
    {
        this.city = city;
    }


    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }


    //operations that are about the customer's account
    public boolean enoughBalance(int requestedAmount)
    {
        return this.account.getBalance() > requestedAmount;

    }
    public void addBalance(double amountToAdd)
    {
        this.account.addBalance(amountToAdd);
    }
    public String getAccountNumber()
    {
        return this.account.getAccountNumber();
    }
    


}

