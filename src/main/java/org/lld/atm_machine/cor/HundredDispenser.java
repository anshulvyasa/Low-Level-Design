package org.lld.atm_machine.cor;

import org.lld.atm_machine.models.ATM;

public class HundredDispenser implements  CashDispensor{
    private CashDispensor next;

    @Override
    public void setNextDispensor(CashDispensor next) {
        this.next=next;
    }

    @Override
    public boolean canDispense(ATM atm, int amount) {
        int quantity=amount/100;
        quantity=Math.min(quantity,atm.getHundredNoteCount());

        int rem=amount-quantity*100;

        return rem==0 || (next!=null && next.canDispense(atm,rem));
    }

    @Override
    public void dispense(ATM atm, int amount) {
       int quantity=amount/100;
       quantity=Math.min(quantity,atm.getHundredNoteCount());
       atm.setHundredNoteCount(atm.getHundredNoteCount()-quantity);

       if(quantity>0) System.out.println("Dispensed The "+quantity+" Hundred Notes");

       int rem=amount-quantity*100;
       if(next!=null&&rem>0){
           next.dispense(atm,rem);
       }
    }
}
