package org.lld.ticket_booking_system.payment;

import org.lld.ticket_booking_system.interfaces.PaymentStragety;

public class UPIPayment implements PaymentStragety {
    @Override
    public boolean processPayment(double amount) {
        return true;
    }
}
