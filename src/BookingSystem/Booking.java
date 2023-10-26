package BookingSystem;

import java.time.LocalDate;
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

    public void createBooking(ArrayList<LocalTime> bookingList){
        LocalTime selectedTime = time;
        if (bookingList.contains(selectedTime)) {
            System.out.println("Booking created for " + name + " on " + date + " at time " + time + " O'Clock.");
            bookingList.remove(selectedTime);
        } else {
            System.out.println("The selected time is unavailable. Please choose another time.");
        }
    }
    public void removeBooking(ArrayList<LocalTime> bookingList){
        LocalTime selectedTime = time;
        if (!bookingList.contains(selectedTime)) {
            System.out.println("Booking removed for " + name + " on " + date + " at time " + time + " O'Clock.");
            // Find the correct position to add the time to maintain sorted order
            int index = 0;
            while (index < bookingList.size() && bookingList.get(index).isBefore(selectedTime)) {
                index++;
            }
            bookingList.add(index, selectedTime);
        }
        else{
            System.out.println("No booking found at the selected time.");
        }
    }

}