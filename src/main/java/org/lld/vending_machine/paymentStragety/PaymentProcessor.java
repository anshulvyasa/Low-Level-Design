package org.lld.vending_machine.paymentStragety;

import lombok.Getter;
import lombok.Setter;

@Getter
public class PaymentProcessor{
    @Setter  PaymentStragety paymentStragety;

    public PaymentProcessor(){
        this.paymentStragety=new UpiPayment();
    }

}
