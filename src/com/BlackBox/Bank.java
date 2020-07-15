package src.com.BlackBox;

public class Bank
{
    private Account account;


    public Bank(int initialMoney, String accountNumber)
    {
        this.account = new Account(initialMoney, accountNumber);
    }

    public boolean enoughBalance(int requestedAmount)
    {
        return account.getBalance() > requestedAmount;
    } 
    public Account getAccount()
    {
        return this.account;
    }
}