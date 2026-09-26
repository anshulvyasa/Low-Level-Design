package org.lld.parking_lot.payments;

public class DebitCardpayment implements PaymentStragety {
    @Override
    public void processPayment(double amount) {
        System.out.println("Deducted "+amount+" from your account through Debit Card");
    }
}
