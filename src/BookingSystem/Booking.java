//Project1_Group_5 DAT23B

package BookingSystem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Booking implements Comparable<Booking>{
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
    public int compareTo(Booking b){
        return this.time.compareTo(b.time);
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
    public String getName(){
        return name;
    }
}