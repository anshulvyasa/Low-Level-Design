package org.lld.atm_machine.states;

import lombok.AllArgsConstructor;
import org.lld.atm_machine.enums.AtmStatus;
import org.lld.atm_machine.models.Card;
import org.lld.atm_machine.services.ATMMachine;

@AllArgsConstructor
public class CardIdleState implements  ATMState{
    private final ATMMachine atmMachine;

    @Override
    public void insertCard(Card card) {
        atmMachine.setCard(card);
        System.out.println("Card is Inserted");
        atmMachine.setState(new CardInsertedState(atmMachine));
    }

    @Override
    public void enterPin(int pin) {
        System.out.println("Insert The Card First");
    }

    @Override
    public void selectOptions(String option) {
        System.out.println("Insert The Card First");
    }

    @Override
    public void withdrawCash(int amount) {
        System.out.println("Insert The Card First");
    }

    @Override
    public void ejectCard() {
        System.out.println("Insert The Card First");
    }

    @Override
    public AtmStatus getStatus() {
        return AtmStatus.IDLE;
    }
}
