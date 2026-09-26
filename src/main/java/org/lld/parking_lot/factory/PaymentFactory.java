package org.lld.parking_lot.factory;

import org.lld.parking_lot.payments.CreditCardPaymentStragety;
import org.lld.parking_lot.payments.DebitCardpayment;
import org.lld.parking_lot.payments.PaymentStragety;
import org.lld.parking_lot.payments.UPIPaymentStragety;

public class PaymentFactory {
    public static PaymentStragety getPaymentStragety(int num){
        return switch (num){
            case 1 -> new CreditCardPaymentStragety();
            case 2 -> new DebitCardpayment();
            case 3 -> new UPIPaymentStragety();
            default -> throw  new IllegalArgumentException("Payment Methord not supported");
        };
    }
}
