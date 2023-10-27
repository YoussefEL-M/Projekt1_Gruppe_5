//Project1_Group_5 DAT23B

package BookingSystem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Salon {
    public static void main (String[] args) throws IOException{
        ArrayList<LocalTime> availableTimes = new ArrayList<>();
        ArrayList<LocalDate> closedDates = new ArrayList<>();
        ArrayList<Booking> bookings = FileManager.getBookings("Bookings");
        for (int hour = 10; hour < 18; hour++) {
            availableTimes.add(LocalTime.of(hour, 0));
            availableTimes.add(LocalTime.of(hour, 30));
        }
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Only for tests.
        System.out.println("Avaliable Booking List times: " + availableTimes);
        System.out.println("Avaliable Booking List closeddates: " + closedDates);
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
                    System.out.println("Enter note: ");
                    String note = scanner.next();
                    LocalDate date = LocalDate.of(year, month, day);
                    int amount = 0;
                    boolean paymentReceived = false;
                    LocalTime time = LocalTime.of(hour,minute);
                    if (isAvailable(date,time,closedDates,availableTimes,bookings)) {
                        Booking newBook = new Booking(name, note, date, time, amount, paymentReceived);
                        bookings.add(newBook);
                        System.out.println("Booking created for " + name + " on " + date + " at time " + time + " O'Clock.");
                    }
                    else {
                        System.out.println("The selected time is unavailable. Please choose another time.");
                    }
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
                    LocalDate date = LocalDate.of(year,month,day);
                    if (isAvailable(date,time,closedDates,availableTimes,bookings)){
                        bookings.add(new Booking(name,note,date,time,amount,paymentReceived));
                        System.out.println("Booking created for " + name + " on " + date + " at time " + time + " O'Clock.");
                    }

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

    /* skal rettes!
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

    }*/
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
    }*/
    private static boolean isAvailable(LocalDate date, LocalTime time, ArrayList<LocalDate> closedDates, ArrayList<LocalTime> availableTimes, ArrayList<Booking> bookings) {
    if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
        // It's Saturday or Sunday
        return false;
    }

    for (Booking booking : bookings) {
        if (booking.getDate().equals(date) && booking.getTime().equals(time)) {
            // Booking already exists
            return false;
        }
    }

    return !closedDates.contains(date) && availableTimes.contains(time);
}

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