package BookingSystem;

import java.util.ArrayList;

import java.time.LocalDate;
import java.util.ArrayList;

public class Salon {
    private ArrayList<Double> bookingList = new ArrayList<>();

    public Salon() {
        this.bookingList = new ArrayList<>();

    }
        public static void main(String[] args) {
            ArrayList<Double> bookingList = new ArrayList<>();
            for (double hour = 10; hour < 18; hour++) {
                bookingList.add(hour);
                bookingList.add(hour + 0.5); // Add 30 minutes
            }


            Salon salon = new Salon();
            salon.bookingList = bookingList;

            // test
            Booking a = new Booking("John Jensen", 12.5, LocalDate.now());
            a.createBooking(salon.bookingList);


            System.out.println("Avaliable Booking List times: " + salon.bookingList);

        ArrayList<Booking> list=new ArrayList<>();
        int openingTime;
        int closingTime;

    }
    public void printBooking() {

    }

    public void opening(int openingTime){

    }
    public void closing(int closingTime) {

    }


}
