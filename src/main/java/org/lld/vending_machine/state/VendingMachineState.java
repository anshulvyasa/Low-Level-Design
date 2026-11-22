package org.lld.vending_machine.state;

import org.lld.vending_machine.service.VendingMachine;

public abstract class VendingMachineState {
    protected VendingMachine vendingMachine;

    public VendingMachineState(VendingMachine vendingMachine){
        this.vendingMachine=vendingMachine;
    }

    public abstract void next();
}
