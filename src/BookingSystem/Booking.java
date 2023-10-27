//Project1_Group_5 DAT23B

package BookingSystem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Booking {
     static int noOfBookings = 0;
     int indexNo;
    String name;
    String note;
    LocalTime time;
    LocalDate date;
    Transaction transaction = new Transaction(0,false);

    Booking(String name, String note, LocalDate date, LocalTime time, double amount, boolean paymentReceived){
        indexNo = noOfBookings;
        noOfBookings++;
        this.name = name;
        this.note = note;
        this.time = time;
        this.date = date;
        transaction.setAmount(amount);
        transaction.setPaymentReceived(paymentReceived);
    }

    public String toString(){
        return indexNo+". "+name+" - "+date+" "+time+" - "+note;
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