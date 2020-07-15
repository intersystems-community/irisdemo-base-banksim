package src.com.BlackBox;

import java.util.Date;

public class LoanContract
{
    private Customer borrower;
    private int amountOwed;
    private int paymentSize;
    public static Bank bank;
    public int dayDue;

    public LoanContract(Customer borrower, int initialAmount, int dayDue, int paymentSize)
    {
        this.borrower=borrower;
        this.amountOwed=initialAmount;
        this.dayDue = dayDue;
        this.paymentSize = paymentSize;

    }

    public boolean dueToday(Date currentDate)
    {
        return currentDate.getDate() == this.dayDue;
    }

    public static boolean feasibleLoan(int amount)
    {
        return bank.enoughBalance(amount);
    }

    public boolean makePayment()
    {
        if (borrower.enoughBalance(paymentSize))
        {
            borrower.getAccount().addBalance(-paymentSize);
            bank.getAccount().addBalance(paymentSize);
            amountOwed-=paymentSize;
            return true;
        }
        return false;
    }

    public boolean isComplete()
    {
        return (int)amountOwed == 0;
    }


}