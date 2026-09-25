package org.lld.atm_machine.cor;

import org.lld.atm_machine.models.ATM;

public class FiveHundredDispenser implements  CashDispensor{
    private CashDispensor next;

    @Override
    public void setNextDispensor(CashDispensor next) {
        this.next=next;
    }

    @Override
    public boolean canDispense(ATM atm, int amount) {
        int quantity=amount/500;
        quantity=Math.min(quantity,atm.getFiveHundredNoteCount());

        int rem=amount-quantity*500;

        return  rem==0||(next!=null && next.canDispense(atm,rem));
    }

    @Override
    public void dispense(ATM atm, int amount) {
        int quantity=amount/500;
        quantity=Math.min(quantity,atm.getFiveHundredNoteCount());

        atm.setFiveHundredNoteCount(atm.getFiveHundredNoteCount()-quantity);

        if(quantity>0) System.out.println("Dispensed The "+quantity+" Five Hundred Notes");

        int rem=amount-quantity*500;

        if(next!=null && rem>0){
            next.dispense(atm,rem);
        }
    }
}
