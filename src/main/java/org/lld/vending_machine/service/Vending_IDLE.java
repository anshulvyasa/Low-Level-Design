package org.lld.vending_machine.service;

import org.lld.vending_machine.state.VendingMachineState;

public class Vending_IDLE extends VendingMachineState {

    public Vending_IDLE(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void next() {

    }
}
