package org.lld.ticket_booking_system.payment;

import org.lld.ticket_booking_system.interfaces.PaymentStragety;
import org.lld.ticket_booking_system.models.Booking;
import org.lld.ticket_booking_system.models.User;
import org.lld.ticket_booking_system.services.BookingService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProcessPayment {
    private BookingService bookingService;
    private PaymentStragety paymentStragety;

    Map<Booking,Integer> bookingFailures;

    public ProcessPayment(BookingService bookingService,PaymentStragety paymentStragety){
        this.bookingService=bookingService;
        this.paymentStragety=paymentStragety;
        bookingFailures=new ConcurrentHashMap<>();
    }

    public void processPayment(int bookingId, User user,double amount){
        try{
            Booking booking=bookingService.getBooking(bookingId);
            if(paymentStragety.processPayment(amount)){
                booking.confirmBooking();
            }
            else{
               handlePaymentFailure(bookingId,user,amount);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void handlePaymentFailure(int bookingId,User user,double amount) throws Exception{
        Booking booking = bookingService.getBooking(bookingId);
        if (!booking.getUser().equals(user)) {
            throw new Exception("Only the booking owner can report payment failure.");
        }


        if (!bookingFailures.containsKey(booking))
            bookingFailures.put(booking, 0);

        // Increment failure count.

        final Integer currentFailuresCount = bookingFailures.get(booking);
        final Integer newFailuresCount = currentFailuresCount + 1;
        bookingFailures.put(booking, newFailuresCount);
        System.out.println(
                "Could not process the payment for Booking with ID : " + bookingId);
    }
}
