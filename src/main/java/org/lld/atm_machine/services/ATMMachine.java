package org.lld.atm_machine.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.lld.atm_machine.factory.ATMStatefactory;
import org.lld.atm_machine.models.ATM;
import org.lld.atm_machine.models.Card;
import org.lld.atm_machine.repository.ATMRepository;
import org.lld.atm_machine.states.ATMState;


@Getter
public class ATMMachine {
    private final ATM atm;
    @Setter private Card card;
    @Setter private ATMState atmState;
    private final ATMRepository  atmRepository;

    public ATMMachine(int atmId,ATMRepository atmRepository) {
        this.atmRepository = atmRepository;
        this.atm = atmRepository.getById(atmId)
                .orElseThrow(() -> new RuntimeException("ATM not found"));
        this.atmState = ATMStatefactory.getState(atm.getAtmState(), this);
    }

    public void insertCard(Card card){
        atmState.insertCard(card);
    }

    public void enterPin(int pin){
        atmState.enterPin(pin);
    }

    public void selectOptions(String option){
        atmState.selectOptions(option);
    }

    public void withdrawCash(int amount){
        atmState.withdrawCash(amount);
    }

    public void ejectCard(){
        atmState.ejectCard();
    }

    public void setState(ATMState state){
        atmState=state;
    }
}
