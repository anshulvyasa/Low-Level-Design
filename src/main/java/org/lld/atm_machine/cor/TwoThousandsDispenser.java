package org.lld.atm_machine.cor;

import org.lld.atm_machine.models.ATM;

public class TwoThousandsDispenser implements CashDispensor{
    private CashDispensor next;


    @Override
    public void setNextDispensor(CashDispensor next) {
        this.next=next;
    }

    @Override
    public boolean canDispense(ATM atm, int amount) {
        int quantity=amount/2000;
        quantity=Math.min(quantity,atm.getTwoThousandNoteCount());

        int rem=amount-quantity*2000;

        return rem==0|| (next!=null && next.canDispense(atm,amount-quantity*2000));
    }

    @Override
    public void dispense(ATM atm, int amount) {
        int quantity=amount/2000;
        quantity=Math.min(quantity,atm.getTwoThousandNoteCount());
        atm.setTwoThousandNoteCount(atm.getTwoThousandNoteCount()-quantity);

        if(quantity>0) System.out.println("Dispensed The "+quantity+" Two Thousand Notes");

        int rem=amount-quantity*2000;

        if(next!=null&&rem>0){
            next.dispense(atm,rem);
        }
    }
}
