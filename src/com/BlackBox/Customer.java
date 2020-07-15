package src.com.BlackBox;


public class Customer
{

    private Account account;
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



    public Account getAccount()
    {
        return this.account;
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




    public boolean enoughBalance(int requestedAmount)
    {
        return this.account.getBalance() > requestedAmount;

    }


}

