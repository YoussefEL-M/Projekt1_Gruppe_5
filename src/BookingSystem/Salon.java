package BookingSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Salon {
    ArrayList<Double> bookingList;
    int openingTime;
    int closingTime;

    public Salon() {
        this.bookingList = new ArrayList<>();

    }

    public static void main(String[] args) {
        ArrayList<Double> bookingList = new ArrayList<>();
        for (double hour = 10; hour < 18; hour++) {
            bookingList.add(hour);
            bookingList.add(hour + 0.5); // Add 30 minutes
        }
        Scanner scanner = new Scanner(System.in);
        int choice;
        Salon salon = new Salon();
        salon.bookingList = bookingList;

        // test
        Booking a = new Booking("John Jensen", 12.5, LocalDate.now());
        a.createBooking(salon.bookingList);


        System.out.println("Avaliable Booking List times: " + salon.bookingList);

        ArrayList<Booking> list=new ArrayList<>();
        int openingTime;
        int closingTime;

        do {
            System.out.println("Welcome to Harry's Salon!");
            System.out.println("Hairy?");
            System.out.println("See Cotter!");
            System.out.println("1. Book appointment");
            System.out.println("2. Cancel appointment");
            System.out.println("3. Add product");
            System.out.println("4. View services");
            System.out.println("5. View finances");
            System.out.println("6. Exit");
            System.out.print("Please enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();
            String message = null;
            switch (choice) {
                case 1 -> {
                }
                case 2 -> {
                }
                case 3 -> {
                }
                case 4 -> {
                }
                case 5 -> {
                }
                case 6 -> message = "Thanks for using our salon booking system. Goodbye!";
                default -> message = "Error. Invalid input. Try again";
            }
            System.out.println(message);
        } while (choice != 6);
        scanner.close();

    }

    public void Bookings() {
        bookingList = new ArrayList<>();
    }

    public void printBooking() {
    }

    public void opening(int openingTime) {
        this.openingTime = openingTime;

    }

    public void closing(int closingTime) {
        this.closingTime = closingTime;
    }


}
