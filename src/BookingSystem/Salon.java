//Project1_Group_5 DAT23B

package BookingSystem;

import jdk.jshell.spi.ExecutionControlProvider;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Salon {
    public static void main(String[] args) throws IOException {
        ArrayList<LocalTime> availableTimes = new ArrayList<>();
        ArrayList<LocalDate> closedDates = new ArrayList<>();
        ArrayList<Booking> bookings = FileManager.getBookings("Bookings");
        ArrayList<Booking> pastBookings = FileManager.getBookings("PastBookings");

        for (int hour = 10; hour < 18; hour++) {
            availableTimes.add(LocalTime.of(hour, 0));
            availableTimes.add(LocalTime.of(hour, 30));
        }
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Only for tests.
        System.out.println("Avaliable Booking List times: " + availableTimes);
        System.out.println("Avaliable Booking List closeddates: " + closedDates);
        // end of test

        String financePassword = "hairyharry";

        do {
            System.out.println("Welcome to Harry's Salon!");
            System.out.println("Hairy?");
            System.out.println("See Cotter!");
            System.out.println("1. Book appointment");
            System.out.println("2. Cancel appointment");
            System.out.println("3. Add closed date");
            System.out.println("4. Edit booking transactions");
            System.out.println("5. View finances");
            System.out.println("6. View services");
            System.out.println("7. Exit");
            System.out.print("Please enter your choice: \n");

            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    Booking newBook = getBookingDetails(scanner);

                    if (isAvailable(newBook.getDate(), newBook.getTime(), closedDates, availableTimes, bookings)) {
                        bookings.add(newBook);
                        System.out.println("Booking created for " + " on " + newBook.getDate() + " at time " + newBook.getTime() + " O'Clock.");
                    } else {
                        System.out.println("The selected time is unavailable. Please choose another time.");
                    }
                }
                case 2 -> {
                    cancelBooking(bookings, closedDates, availableTimes, scanner);
                }
                case 3 -> {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter closed date in format yyyy-mm-dd.");
                    LocalDate closedDate = LocalDate.parse(sc.nextLine());
                    closedDates.add(closedDate);
                    FileManager.saveClosedDays(closedDates);
                    System.out.println("Closed date added: " + closedDate);
                }
                case 4 -> {
                    editBooking(bookings, closedDates, availableTimes, scanner);
                }
                case 5 -> {
                    System.out.println("Please enter the password: ");
                    String enterPassword = scanner.nextLine();
                    // indsæt mulighed for index print
                    if (enterPassword.equals(financePassword)) {
                        //System.out.println("Access granted. Welcome to finance! You've had: "+attendance+" booking so far");

                        System.out.println(pastBookings);

                    } else {
                        System.out.println("Incorrect password. Please try again ");
                    }

                }
                case 6 -> {

                }
                case 7 -> {
                    FileManager.saveBookings(bookings);
                    System.out.println("Thanks for using our salon booking system. Goodbye!");
                }
                default -> System.out.println("Error. Invalid input. Try again");
            } //switch
            System.out.println(availableTimes);
        } while (choice != 7);
        scanner.close();

    }

    private static Booking getBookingDetails(Scanner scanner) {
        boolean details = true;

        while (details) {
            try {
                System.out.println("Pleaser enter the following:");
                System.out.println("Name: ");
                String name = scanner.nextLine();
                Scanner sc = new Scanner(System.in);
                LocalDate date;
                LocalTime time;
                System.out.println("Enter booking date in format yyyy-mm-dd.");
                date = LocalDate.parse(sc.nextLine());
                System.out.println("Enter booking time in format hh:mm.");
                time = LocalTime.parse(sc.nextLine());
                System.out.println("Enter note: ");
                String note = scanner.nextLine();
                int amount = 0;
                boolean paymentReceived = false;

                return new Booking(name, note, date, time, amount, paymentReceived);
            } catch (DateTimeParseException e) {
                System.out.println("An invalid date/time format. Please use the following format yyyy-mm-dd.");
                details = true;
            } catch (Exception e) {
                System.out.println("And error has occured " + e.getMessage());
                e.printStackTrace();
                details = true;
            }
        }
        return null;
    }

    private static boolean isAvailable(LocalDate date, LocalTime time, ArrayList<LocalDate> closedDates, ArrayList<LocalTime> availableTimes, ArrayList<Booking> bookings) {
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            // It's Saturday or Sunday
            System.out.println();
            System.out.println("Error: date is on a weekend.");
            return false;
        }

        for (Booking booking : bookings) {
            if (booking.getDate().equals(date) && booking.getTime().equals(time)) {
                // Booking already exists
                System.out.println();
                System.out.println("Error: a booking already exists at this time.");
                return false;
            }
        }
        if (closedDates.contains(date)) {
            System.out.println();
            System.out.println("Error: the salon is closed on this day.");
        }
        return !closedDates.contains(date) && availableTimes.contains(time);
    }  //isAvailable

    static void searchBookings(ArrayList<Booking> list, LocalDate searchDate, ArrayList<LocalTime> times, ArrayList<LocalDate> closedDates) { //Severin - 26/10
        if (searchDate.getDayOfWeek() == DayOfWeek.SATURDAY || searchDate.getDayOfWeek() == DayOfWeek.SUNDAY || closedDates.contains(searchDate)) {
            System.out.println();
            System.out.println("Error: The salon is not open for business on this day.");
        } else {
            for (int i = 1; i <= 5; ) {
                if (searchDate.getDayOfWeek() == DayOfWeek.SATURDAY || searchDate.getDayOfWeek() == DayOfWeek.SUNDAY || closedDates.contains(searchDate)) {
                    searchDate = searchDate.plusDays(1);
                    continue;
                }

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
                searchDate = searchDate.plusDays(1);
                i++;
            } //for
        } //else
    }//searchBookings

    private static void cancelBooking(ArrayList<Booking> bookings, ArrayList<LocalDate> closedDates, ArrayList<LocalTime> availableTimes, Scanner scanner) {
        boolean t = true;

        while (t) {
            try {
                System.out.println("Enter the date of the booking in format yyyy-mm-dd: ");
                LocalDate searchDate = LocalDate.parse(scanner.nextLine());

                System.out.println("Enter the time of the booking in format hh:mm: ");
                LocalTime searchTime = LocalTime.parse(scanner.nextLine());

                Booking bookingToRemove = null;

                for (Booking booking : bookings) {
                    if (booking.getDate().equals(searchDate) && booking.getTime().equals(searchTime)) {
                        bookingToRemove = booking;
                        System.out.println("Booking found, and cancelled.");
                        t = false;
                        break;
                    }
                }
                if (bookingToRemove!=null){
                    bookings.remove(bookingToRemove);
                }
                else {
                    System.out.println("No booking found on this date.");
                }
            }catch (DateTimeParseException e) {
                System.out.println("An invalid date/time format.");
                t = true;
            } catch (Exception e) {
                System.out.println("And error has occured " + e.getMessage());
                e.printStackTrace();
                t = true;
            }
        }
    }

    private static void editBooking(ArrayList<Booking> bookings, ArrayList<LocalDate> closedDates, ArrayList<LocalTime> availableTimes, Scanner scanner) {
        boolean t = true;

        while (t) {
            try {
                System.out.println("Enter the name of the booking you want to edit: ");
                String searchName = scanner.nextLine();

                System.out.println("Enter the date of the booking in format yyyy-mm-dd: ");
                LocalDate searchDate = LocalDate.parse(scanner.nextLine());

                System.out.println("Enter the time of the booking in format hh:mm: ");
                LocalTime searchTime = LocalTime.parse(scanner.nextLine());

                Booking bookingToEdit = null;

                for (Booking booking : bookings) {
                    if (booking.getName().equals(searchName) && booking.getDate().equals(searchDate) && booking.getTime().equals(searchTime)) {
                        bookingToEdit = booking;
                        break;
                    }
                }

                if (bookingToEdit != null) {
                    System.out.println("Current booking details:");
                    System.out.println(bookingToEdit);

                    System.out.println("Enter the new amount: ");
                    double newAmount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Was payment received? (yes/no): ");
                    String paymentReceivedInput = scanner.nextLine();
                    boolean newPaymentReceived = paymentReceivedInput.equalsIgnoreCase("yes");

                    bookingToEdit.transaction.addAmount(newAmount);
                    bookingToEdit.transaction.setPaymentReceived(newPaymentReceived);

                    System.out.println("Booking edited successfully!");
                    t=false;
                    FileManager.saveBookings(bookings);
                } else {
                    System.out.println("No matching booking found.");
                }
            }catch (DateTimeParseException e) {
                System.out.println("An invalid date/time format. Please use the following format yyyy-mm-dd.");
                t = true;
            } catch (Exception e) {
                System.out.println("And error has occured " + e.getMessage());
                e.printStackTrace();
                t = true;
            }
        }
    }
}