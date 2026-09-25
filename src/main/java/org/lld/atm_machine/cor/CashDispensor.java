package org.lld.atm_machine.cor;

import org.lld.atm_machine.models.ATM;

public interface CashDispensor {
    void setNextDispensor(CashDispensor next);
    boolean canDispense(ATM atm,int amount);
    void dispense(ATM atm,int amount);
}
