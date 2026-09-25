package org.lld.atm_machine.factory;

import org.lld.atm_machine.enums.AtmStatus;
import org.lld.atm_machine.services.ATMMachine;
import org.lld.atm_machine.states.*;

public class ATMStatefactory {
    public static ATMState getState(AtmStatus status, ATMMachine machine) {
        return switch (status) {
            case IDLE -> new CardIdleState(machine);
            case INSERTED -> new CardInsertedState(machine);
            case AUTHENTICATED -> new CardAuthnticated(machine);
            case WITHDRAWL -> new WithdrawCash(machine);
            default -> throw new IllegalArgumentException("Unknown ATM status: " + status);
        };
    }
}
