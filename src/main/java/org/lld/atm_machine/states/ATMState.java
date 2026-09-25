package org.lld.atm_machine.states;

import org.lld.atm_machine.enums.AtmStatus;
import org.lld.atm_machine.models.Card;

public interface ATMState {
    void insertCard(Card card);
    void enterPin(int pin);
    void selectOptions(String option);
    void withdrawCash(int amount);
    void ejectCard();
    AtmStatus getStatus();
}
