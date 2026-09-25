package org.lld.atm_machine.states;

import lombok.AllArgsConstructor;
import org.lld.atm_machine.cor.CashDispenserBuilder;
import org.lld.atm_machine.cor.CashDispensor;
import org.lld.atm_machine.enums.AtmStatus;
import org.lld.atm_machine.models.Card;
import org.lld.atm_machine.services.ATMMachine;


public class WithdrawCash implements  ATMState {
    private final ATMMachine atmMachine;
    private final CashDispensor cashDispensor;

    public WithdrawCash(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
        this.cashDispensor = CashDispenserBuilder.build();
    }

    @Override
    public void insertCard(Card card) {
        System.out.println("Invalid Operation. Card Already Inserted");
    }

    @Override
    public void enterPin(int pin) {
        System.out.println("Invalid Operation. Pin Already Entered");
    }

    @Override
    public void selectOptions(String option) {
        System.out.println("Invalid Operation. Option Already Selected");
    }

    @Override
    public void withdrawCash(int amount) {
         double userBalance=atmMachine.getCard().getAccount().getBalance();
         double atmBalance=atmMachine.getAtm().getTotalAmount();

         if(amount>userBalance){
             System.out.println("Insufficient Balance");
             return;
         }

         if(amount>atmBalance){
             System.out.println("Insufficient Balance in ATM");
             return;
         }

         if(!cashDispensor.canDispense(atmMachine.getAtm(),amount)){
             System.out.println("Inssuficient Denomination");
             return;
         }

         cashDispensor.dispense(atmMachine.getAtm(),amount);
         atmMachine.getCard().getAccount().setBalance(userBalance - amount);
         atmMachine.getAtm().setTotalAmount(atmBalance - amount);
    }

    @Override
    public void ejectCard() {
        atmMachine.setCard(null);
        System.out.println("Card is Allowded to pull");
        atmMachine.setState(new CardIdleState(atmMachine));
    }

    @Override
    public AtmStatus getStatus() {
        return AtmStatus.WITHDRAWL;
    }
}
