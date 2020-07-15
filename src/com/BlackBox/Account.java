package src.com.BlackBox;

public class Account
{

    private String accountNumber;
    private double balance;


    public Account(double balance, String accountNumber)
    {
        this.balance = balance;
        this.accountNumber = accountNumber;
    }




    public double getBalance() {
        return balance;
    }



    public void addBalance(double amount)
    {
        this.balance += amount;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }
    
    public void setAccountNumber(String acctNumber)
    {
        accountNumber = acctNumber;
    }

}
