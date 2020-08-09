package com.irisdemo.banksim.event;

import com.irisdemo.banksim.model.LoanContract;
import java.util.Calendar;
import com.irisdemo.banksim.avroevent.LoanContractAvroEvent;

public class LoanContractEvent extends Event 
{
    private LoanContract contract;

    public LoanContractEvent(Calendar eventDate, LoanContract contract)
    {
        super(eventDate);
        this.contract = contract;
    }

    public void displayInfo()
    {
        super.displayInfo();
        System.out.println("Amount Loaned : " + contract.getContractAmount());
        contract.getBorrower().displayInfo(false);
    }

    public LoanContractAvroEvent getAvroEvent()
    {
        return new LoanContractAvroEvent(
                                            getId(), 
                                            getExternalEventDate(), 
                                            contract.getId(),
                                            contract.getBorrower().getId(),
                                            contract.getBorrower().getAccount().getAccountNumber(), 
                                            contract.getContractAmount()
                                        );
    }
}