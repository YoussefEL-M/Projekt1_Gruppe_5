package BookingSystem;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Booking {
    String name;
    LocalTime time; //skal vi have duration med, eller kører vi med 30min intervaller? Hvis ja, indsæt.
    LocalDate date;

    Booking(String name, LocalDate date, LocalTime time){
        this.name = name;
        this.time = time;
        this.date = date;
    }

    public void createBooking(ArrayList<LocalDateTime> bookingList){
        /* int interval = 30; //30 min.
        int startTime = 10 * 60; // 10:00 converted to minutes
        int endTime = 18 * 60; // 18:00 converted to minutes
        int timeInMinutes = (int) (time * 60);

        Ikke nødvendig, da arraylist i Salon har available times.
*/
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        if (bookingList.contains(dateTime)) {
            System.out.println("Booking created for " + name + " on " + date + " at time " + time + " O'Clock.");
            bookingList.remove(dateTime);
        } else {
            System.out.println("The selected time is unavailable. Please choose another time.");
        }
    }
    public void removeBooking(ArrayList<LocalDateTime> bookingList){
        LocalDateTime dateTime = LocalDateTime.of(this.date, this.time);
        if (!bookingList.contains(dateTime)) {
            System.out.println("Booking removed for " + name + " on " + date + " at time " + time + " O'Clock.");
            // Find the correct position to add the time to maintain sorted order
            int index = 0;
            while (index < bookingList.size() && bookingList.get(index).isBefore(dateTime)) {
                index++;
            }
            bookingList.add(index, dateTime);
        }
        else{
            System.out.println("No booking found at the selected time.");
        }
    }

}