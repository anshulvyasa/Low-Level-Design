package org.lld.vending_machine.paymentStragety;

public class UpiPayment implements  PaymentStragety{
    @Override
    public void processPayment(double amount) {
        System.out.println("Payment of "+amount+" is done through UPI Payment");
    }
}
