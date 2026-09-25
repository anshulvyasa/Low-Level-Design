package org.lld.atm_machine.states;

import lombok.AllArgsConstructor;
import org.lld.atm_machine.enums.AtmStatus;
import org.lld.atm_machine.models.Card;
import org.lld.atm_machine.services.ATMMachine;

@AllArgsConstructor
public class CardAuthnticated implements  ATMState {
    private final ATMMachine atmMachine;

    @Override
    public void insertCard(Card card) {
        System.out.println("Card is Already Inserted");
    }

    @Override
    public void enterPin(int pin) {
        System.out.println("Invalid Operation you already entered The Pin");
    }

    @Override
    public void selectOptions(String option) {
         if(option.equals("withdrawl")){
             System.out.println("Option Selected Withdrawl");
             atmMachine.setState(new WithdrawCash(atmMachine));
         }
          //  Todo: implement next methord
    }

    @Override
    public void withdrawCash(int amount) {
        System.out.println("Select The Option First");
    }

    @Override
    public void ejectCard() {
        atmMachine.setCard(null);
        System.out.println("Card is Save to Pull");
        atmMachine.setState(new CardIdleState(atmMachine));
    }

    @Override
    public AtmStatus getStatus() {
        return null;
    }
}
