package org.lld.atm_machine;

import org.lld.atm_machine.enums.AtmStatus;
import org.lld.atm_machine.models.ATM;
import org.lld.atm_machine.models.Account;
import org.lld.atm_machine.models.Card;
import org.lld.atm_machine.repository.ATMRepository;
import org.lld.atm_machine.services.ATMMachine;

public class Main {
    public static void main(String[] args) {
        ATM atm1=new ATM(1, AtmStatus.IDLE,5000,2,1,5);
        ATM atm2=new ATM(2, AtmStatus.IDLE,10000,3,4,20);

        ATMRepository atmRepository=new ATMRepository();
        atmRepository.save(atm1);
        atmRepository.save(atm2);

        ATMMachine atmMachine=new ATMMachine(2,atmRepository);

        Account account=new Account(123,5000);
        Card card=new Card(account,9134,1293435);

        atmMachine.insertCard(card);
        atmMachine.enterPin(9134);
        atmMachine.selectOptions("withdrawl");
        atmMachine.withdrawCash(5000);
    }
}
