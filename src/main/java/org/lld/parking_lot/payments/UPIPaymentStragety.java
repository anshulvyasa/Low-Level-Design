package org.lld.parking_lot.payments;

public class UPIPaymentStragety implements PaymentStragety {
    @Override
    public void processPayment(double amount) {
        System.out.println("Deducted "+amount+" from your account through UPI");
    }
}
