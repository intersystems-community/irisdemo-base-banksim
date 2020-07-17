package src.com.BlackBox;

import java.util.Calendar;
 
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

    public boolean dueToday(Calendar currentCalendarDate)
    {
        return currentCalendarDate.get(Calendar.DAY_OF_MONTH) == this.dayDue;
    }

    public static boolean feasibleLoan(int amount)
    {
        return bank.enoughBalance(amount);
    }

    public boolean makePayment()
    {
        if (borrower.enoughBalance(paymentSize))
        {
            borrower.addBalance(-paymentSize);
            bank.addBalance(paymentSize);
            amountOwed-=paymentSize;
            return true;
        }
        return false;
    }

    public boolean isComplete()
    {
        return (int)amountOwed == 0;
    }

    public String getBorrowerAccount()
    {
        return borrower.getAccountNumber();
    }


}