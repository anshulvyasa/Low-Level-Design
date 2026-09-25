package org.lld.atm_machine.states;

import lombok.AllArgsConstructor;
import org.lld.atm_machine.enums.AtmStatus;
import org.lld.atm_machine.models.Card;
import org.lld.atm_machine.services.ATMMachine;

@AllArgsConstructor
public class CardInsertedState implements ATMState{
    private final ATMMachine atmMachine;

    @Override
    public void insertCard(Card card) {
        System.out.println("you have Already Inserted The Card");
    }

    @Override
    public void enterPin(int pin) {
        if(atmMachine.getCard().getPin()==pin){
            System.out.println("You Are Authenticated to perform operation");
           atmMachine.setState(new CardAuthnticated(atmMachine));
        }
        else{
            System.out.println("Wrong Pin");
        }
    }

    @Override
    public void selectOptions(String option) {
        System.out.println("Enter The Pin Before Selecting The Operation");
    }

    @Override
    public void withdrawCash(int amount) {
        System.out.println("Enter The Pin First");
    }

    @Override
    public void ejectCard() {
        System.out.println("Ejecting Card....");
        atmMachine.setCard(null);
        atmMachine.setState(new CardIdleState((atmMachine)));
    }

    @Override
    public AtmStatus getStatus() {
        return AtmStatus.INSERTED;
    }
}
