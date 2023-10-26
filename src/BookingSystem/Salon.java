package BookingSystem;

import java.awt.print.Book;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Salon  {
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

        System.out.println("Avaliable Booking List times: " + salon.bookingList);

        String financePassword = "hairyharry";

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
            System.out.print("Please enter your choice: \n");

            choice = scanner.nextInt();
            scanner.nextLine();
            String message = null;
            switch (choice) {
                case 1 -> {
                    System.out.println("Pleaser enter the following:");
                    System.out.println("Name: ");
                    String name=scanner.nextLine();
                    System.out.println("Year: ");
                    int year = scanner.nextInt();
                    System.out.println("Month: ");
                    int month = scanner.nextInt();
                    System.out.println("Day: ");
                    int day = scanner.nextInt();
                    System.out.println("Time: ");
                    double time = scanner.nextDouble();
                    Booking newBook= new Booking(name,LocalDate.of(year,month,day), time);
                    newBook.createBooking(bookingList);
                    System.out.println(bookingList);
                }
                case 2 -> {
                    message = "Pleaser enter the following:";
                    System.out.println("Name: ");
                    String name=scanner.nextLine();
                    System.out.println("Year: ");
                    int year = scanner.nextInt();
                    System.out.println("Month: ");
                    int month = scanner.nextInt();
                    System.out.println("Day: ");
                    int day = scanner.nextInt();
                    System.out.println("Time: ");
                    double time = scanner.nextDouble();
                    Booking removeBook= new Booking(name,LocalDate.of(year,month,day), time);
                    removeBook.removeBooking(bookingList);

                }
                case 3 -> {
                }
                case 4 -> {
                }
                case 5 -> {
                    System.out.println("Please enter the password: ");
                    String enterPassword = scanner.nextLine();
                    if (enterPassword.equals(financePassword)){
                        System.out.println("Access granted. Welcome to finance! ");
                    } else {
                        System.out.println("Incorrect password. Please try again ");
                    }
                }
                case 6 -> message = "Thanks for using our salon booking system. Goodbye!";
                default -> message = "Error. Invalid input. Try again";
            }
            System.out.println(bookingList);
        } while (choice != 6);
        scanner.close();

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
