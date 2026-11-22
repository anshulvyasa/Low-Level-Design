package org.lld.car_rental_system.payment;

public class UPIPayment implements  PaymentStragety{
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Amount "+amount+" is processedthrough the UPIPayment");

        return true;
    }
}
