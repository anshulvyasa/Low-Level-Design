package org.lld.atm_machine.cor;

public class CashDispenserBuilder {
    public static CashDispensor build(){
        CashDispensor twoThousandDispenser=new TwoThousandsDispenser();
        CashDispensor fiveHundredDispenser=new FiveHundredDispenser();
        CashDispensor hundredDispenser=new HundredDispenser();

        twoThousandDispenser.setNextDispensor(fiveHundredDispenser);
        fiveHundredDispenser.setNextDispensor(hundredDispenser);

        return  twoThousandDispenser;
    }
}
