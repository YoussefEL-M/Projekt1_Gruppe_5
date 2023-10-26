package BookingSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Salon {
    ArrayList<Booking> bookingList;
    int openingTime;
    int closingTime;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;


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

            String message;
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    message = "Thanks for using our salon booking system. Goodbye!";
                    break;
                default:
                    message = "Error. Invalid input. Try again";
                    break;

            }
        }while (choice !=4);
        scanner.close();


    }

    public void Bookings() {
        bookingList = new ArrayList<>();
    }
    public void printBooking() {
    }

    public void opening(int openingTime){
        this.openingTime = openingTime;


    }
    public void closing(int closingTime) {
        this.closingTime = closingTime;

    }

}
