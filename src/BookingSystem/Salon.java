//Project1_Group_5 DAT23B

package BookingSystem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Salon {
    //
    public Salon() {
        // Initialize available times (10am-6pm in 30 min intervals)
        for (int hour = 10; hour < 18; hour++) {
            availableTimes.add(LocalTime.of(hour, 0));
            availableTimes.add(LocalTime.of(hour, 30));
        }
        // Initialize closed dates (e.g., weekends)
        LocalDate today = LocalDate.now();
        LocalDate threeMonthsLater = today.plusMonths(3);
        while (today.isBefore(threeMonthsLater)){
            if (today.getDayOfWeek() == DayOfWeek.SATURDAY || today.getDayOfWeek() == DayOfWeek.SUNDAY){
                closedDates.add(today);
            }
            today = today.plusDays(1);
        }
    }

    public static void main (String[] args){
        ArrayList<LocalTime> availableTimes = new ArrayList<>();
        ArrayList<LocalDate> closedDates = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int choice;

        // Only for tests.
        System.out.println("Avaliable Booking List times: " + salon.availableTimes);
        System.out.println("Avaliable Booking List closeddates: " + salon.closedDates);
        // end test

        String financePassword = "hairyharry";

        do {
            System.out.println("Welcome to Harry's Salon!");
            System.out.println("Hairy?");
            System.out.println("See Cotter!");
            System.out.println("1. Book appointment");
            System.out.println("2. Cancel appointment");
            System.out.println("3. Add closed date");
            System.out.println("4. Add and save transaction");
            System.out.println("5. View finances");
            System.out.println("6. View services");
            System.out.println("7. Exit");
            System.out.print("Please enter your choice: \n");

            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.println("Pleaser enter the following:");
                    System.out.println("Name: ");
                    String name = scanner.nextLine();
                    System.out.println("Year: ");
                    int year = scanner.nextInt();
                    System.out.println("Month: ");
                    int month = scanner.nextInt();
                    System.out.println("Day: ");
                    int day = scanner.nextInt();
                    System.out.println("Hour: ");
                    int hour = scanner.nextInt();
                    System.out.println("Minute: ");
                    int minute = scanner.nextInt();
                    LocalTime time = LocalTime.of(hour,minute);

                    salon.addBooking(name, LocalDate.of(year, month, day), time);
                        /*Booking newBook = new Booking(name, LocalDate.of(year, month, day), time);
                        newBook.createBooking(salon.bookingList);*/
                }
                case 2 -> {
                    System.out.println("Pleaser enter the following:");
                    System.out.println("Name: ");
                    String name = scanner.nextLine();
                    System.out.println("Year: ");
                    int year = scanner.nextInt();
                    System.out.println("Month: ");
                    int month = scanner.nextInt();
                    System.out.println("Day: ");
                    int day = scanner.nextInt();
                    System.out.println("Hour: ");
                    int hour = scanner.nextInt();
                    System.out.println("Minute: ");
                    int minute = scanner.nextInt();
                    LocalTime time = LocalTime.of(hour,minute);

                    salon.cancelBooking(name, LocalDate.of(year, month, day), time);

                        /*Booking removeBook = new Booking(name, LocalDate.of(year, month, day), time);
                        removeBook.removeBooking(salon.bookingList);*/

                }
                case 3 -> {
                    System.out.println("Enter closed date:");
                    System.out.println("Year: ");
                    int closedYear = scanner.nextInt();
                    System.out.println("Month: ");
                    int closedMonth = scanner.nextInt();
                    System.out.println("Day: ");
                    int closedDay = scanner.nextInt();
                    LocalDate closedDate = LocalDate.of(closedYear, closedMonth, closedDay);
                    salon.addClosedDate(closedDate);
                }
                case 4 -> {
                    System.out.println("Enter booking details:");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Note: ");
                    String note = scanner.nextLine();

                    System.out.print("Year: ");
                    int year = scanner.nextInt();
                    System.out.print("Month: ");
                    int month = scanner.nextInt();
                    System.out.print("Day: ");
                    int day = scanner.nextInt();
                    System.out.print("Hour: ");
                    int hour = scanner.nextInt();
                    System.out.print("Minute: ");
                    int minute = scanner.nextInt();

                    LocalTime time = LocalTime.of(hour, minute);
                    LocalDate date = LocalDate.of(year, month, day);

                    System.out.print("Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Was payment received? (yes/no): ");
                    String paymentReceivedInput = scanner.nextLine();
                    boolean paymentReceived = paymentReceivedInput.equalsIgnoreCase("yes");

                    Booking newBookingT = new Booking(name, note, date, time, amount, paymentReceived);

                    System.out.println(newBookingT);
                    System.out.println("Transaction saved.");
                }
                case 5 -> {
                    System.out.println("Please enter the password: ");
                    String enterPassword = scanner.nextLine();
                    int attendance = salon.getAttendance();
                    if (enterPassword.equals(financePassword)){
                        System.out.println("Access granted. Welcome to finance! You've had: "+attendance+" booking so far");

                        System.out.println(transactions);

                    } else {
                        System.out.println("Incorrect password. Please try again ");
                    }

                }
                case 6 -> {

                }
                case 7 -> System.out.println("Thanks for using our salon booking system. Goodbye!");
                default -> System.out.println("Error. Invalid input. Try again");
            }
            System.out.println(salon.availableTimes);
        } while (choice != 7);
        scanner.close();

    }

    public void printBooking () {
    }

    // skal rettes!
    public void addClosedDate(LocalDate closedDate) {
        LocalDateTime startOfDay = LocalDateTime.of(closedDate, LocalTime.of(10, 0));
        LocalDateTime endOfDay = LocalDateTime.of(closedDate, LocalTime.of(18, 0));

        while (startOfDay.isBefore(endOfDay)) {
            if (availableTimes.contains(startOfDay)) {
                System.out.println("Removing time on closed date: " + startOfDay);
                availableTimes.remove(startOfDay);
            }
            startOfDay = startOfDay.plusMinutes(30);
        }
        System.out.println("Adding closed date: " + closedDate);

    }
    public void viewTransactions(ArrayList<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }
        System.out.println("Transactions:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
    /*public void addBooking(String name, LocalDate date, LocalTime time) {
        if (isAvailable(date, time)) {
            Booking newBook = new Booking(name, date, time);
            newBook.createBooking(availableTimes);
            attendance++;
        }else{
            System.out.println("Booking not available on this date or time.");
        }
    }
    public void cancelBooking(String name, LocalDate date, LocalTime time) {
        if (!isAvailable(date, time)) {
            Booking removeBook = new Booking(name, date, time);
            removeBook.removeBooking(availableTimes);
            attendance--;
        }
    }
    private boolean isAvailable(LocalDate date, LocalTime time) {
        return !closedDates.contains(date) && availableTimes.contains(time);
    }*/

    void searchBookings(ArrayList<Booking> list, LocalDate searchDate, ArrayList<LocalTime> times, ArrayList<LocalDate> closedDates){ //Severin - 26/10
        if(searchDate.getDayOfWeek() == DayOfWeek.SATURDAY || searchDate.getDayOfWeek() == DayOfWeek.SUNDAY || closedDates.contains(searchDate)){
            System.out.println();
            System.out.println("Error: The salon is not open for business on this day.");
        }
        else {
            ArrayList<Booking> matchingDate = new ArrayList<>();
            boolean check = false;

            for (Booking b : list) {
                if (b.date.isEqual(searchDate))
                    matchingDate.add(b);
            } //for

            System.out.println();
            System.out.println("Available times for " + searchDate + ":");
            System.out.println();

            for (LocalTime t : times) {
                check = false;
                for (Booking b : matchingDate) {
                    if (b.time.equals(t)) {
                        check = true;
                        break;
                    }
                } //for
                if (!check)
                    System.out.println(t);
            } //for
        }
    } //searchBookings
}