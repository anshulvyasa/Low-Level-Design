package org.lld.car_rental_system.payment;

public class CreditCardPayment implements  PaymentStragety{
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Amount "+amount+" is processedthrough the CreditCardPayment");

        return true;
    }
}
