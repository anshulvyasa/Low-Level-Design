package org.lld.vending_machine.service;

import org.lld.vending_machine.state.VendingMachineState;

public class Vending_Checkout extends VendingMachineState {
    public Vending_Checkout(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void next() {

    }
}
