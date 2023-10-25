package BookingSystem;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;

public class Booking {
    String name;
    double time; //skal vi have duration med, eller kører vi med 30min intervaller? Hvis ja, indsæt.
    LocalDate date;

    Booking(String name, double time, LocalDate date){
        this.name=name;
        this.time=time;
        this.date=date;
    }

    public void createBooking(ArrayList<Double> bookingList){
        /* int interval = 30; //30 min.
        int startTime = 10 * 60; // 10:00 converted to minutes
        int endTime = 18 * 60; // 18:00 converted to minutes
        int timeInMinutes = (int) (time * 60);

        Ikke nødvendig, da arraylist i Salon har available times.
*/
        if (bookingList.contains(time)) {
            System.out.println("Booking created for " + name + " on " + date + " at time " + time +" O'Clock.");
            bookingList.remove(time);
        }
        else{
            System.out.println("The selected time is already booked. Please choose another time.");
        }
    }
    /* første input, kan slettes.
    private boolean isValidTime(double time) {
        return time >= 10 && time <= 18;
    }
    */
    public void removeBooking(ArrayList<Double> bookingList, Booking removeBooking){
        if (bookingList.contains(removeBooking.time)) {
            System.out.println("Booking removed for " + removeBooking.name + " on " + removeBooking.date + " at time " + removeBooking.time +" O'Clock.");
            bookingList.remove(removeBooking.time);
        }
        else{
            System.out.println("No booking found at the selected time.");
        }
    }
}
