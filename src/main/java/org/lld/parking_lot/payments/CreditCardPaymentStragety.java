package org.lld.parking_lot.payments;

public class CreditCardPaymentStragety implements PaymentStragety {

    @Override
    public void processPayment(double amount){
        System.out.println("Deducted "+amount+" from your account through CreditCard");
    }
}
