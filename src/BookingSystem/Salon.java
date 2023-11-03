//Project1_Group_5 DAT23B

package BookingSystem;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Salon {
    public static void main(String[] ohMyGodDidTheyGiveYouAnEarcutToo) throws IOException {
        ArrayList<LocalTime> availableTimes = new ArrayList<>();
        ArrayList<LocalDate> closedDates = FileManager.getClosedDays();
        ArrayList<Booking> bookings = FileManager.getBookings("Bookings");
        // saveBookings is called here to sort bookings from yesterday into PastBookings.txt
        FileManager.saveBookings(bookings);
        ArrayList<Booking> backup = new ArrayList<>();
        // check is used as a universal boolean for various loops.
        boolean check;
        String financePassword = "hairyharry";

        // Creates the list of available times which searchTimes() checks against. Could probably be cleaned up.

        for (int hour = 10; hour < 18; hour++) {
            availableTimes.add(LocalTime.of(hour, 0));
            availableTimes.add(LocalTime.of(hour, 30));
        }
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println();
            System.out.println("Welcome to Harry's Salon!");
            System.out.println("Hairy?");
            System.out.println("See Cotter!");
            System.out.println("1. Search times, bookings, and closed days");
            System.out.println("2. Book appointment");
            System.out.println("3. Cancel appointment");
            System.out.println("4. Add or remove closed date");
            System.out.println("5. Edit booking transactions");
            System.out.println("6. View finances");
            System.out.println("7. Recover data after crash or power cut");
            System.out.println("8. Exit");
            System.out.print("Please enter your choice: \n");

            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    while (choice!=4) {
                        System.out.println();
                        System.out.println("1. Show bookings on a specific date");
                        System.out.println("2. Show available times for a specific date and four business days forward");
                        System.out.println("3. Show closed days");
                        System.out.println("4. Return to the main menu");

                        choice = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice) {
                            case 1 -> {
                                System.out.println();
                                System.out.println("Enter date in format yyyy-mm-dd.");
                                LocalDate searchDate = LocalDate.parse(scanner.nextLine());
                                showBookings(bookings,closedDates,searchDate);
                            }
                            case 2 -> {
                                System.out.println();
                                System.out.println("Enter date in format yyyy-mm-dd.");
                                LocalDate searchDate = LocalDate.parse(scanner.nextLine());
                                searchTimes(bookings,searchDate,availableTimes,closedDates);
                            }
                            case 3 -> {
                                System.out.println();
                                closedDates.sort(null);
                                for(LocalDate d: closedDates){
                                    System.out.println(d);
                                }
                            }
                            case 4 -> System.out.println("Returning to the main menu.");
                            default -> System.out.println("Error: Invalid input. Try again.");
                        }
                    }
                }
                case 2 -> {
                    Booking newBook = getBookingDetails(scanner);

                    if (isAvailable(newBook.getDate(), newBook.getTime(), closedDates, availableTimes, bookings)) {
                        bookings.add(newBook);
                        backup.add(newBook);
                        FileManager.backupBookings(backup);
                        System.out.println();
                        System.out.println("Booking created for " + " on " + newBook.getDate() + " at time " + newBook.getTime() + " O'Clock.");
                    } else {
                        System.out.println();
                        System.out.println("The selected time is unavailable. Please choose another time.");
                    }
                }
                case 3 -> cancelBooking(bookings, scanner);
                case 4 -> {
                    while (choice != 3) {

                        System.out.println();
                        System.out.println("1. Add a new closed day");
                        System.out.println("2. Remove a closed day");
                        System.out.println("3. Return to the main menu");

                        choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                            case 1 -> {
                                check = true;
                                while (check) {
                                    try {
                                        System.out.println();
                                        System.out.println("Enter closed date in format yyyy-mm-dd.");
                                        LocalDate closedDate = LocalDate.parse(scanner.nextLine());
                                        closedDates.add(closedDate);
                                        FileManager.saveClosedDays(closedDates);
                                        System.out.println("Closed date added: " + closedDate);
                                        check = false;
                                    } catch (DateTimeParseException e) {
                                        System.out.println();
                                        System.out.println("Date/Time format is invalid. Please use the following format: yyyy-mm-dd and hh:mm.");
                                    } catch (Exception e) {
                                        System.out.println("An error has occurred " + e.getMessage());
                                    }
                                }
                            }
                            case 2 -> {
                                check = false;
                                while (!check) {
                                    try {
                                        System.out.println();
                                        System.out.println("Enter closed date in format yyyy-mm-dd.");
                                        LocalDate closedDate = LocalDate.parse(scanner.nextLine());
                                        for(LocalDate d: closedDates){
                                            if(d.isEqual(closedDate)){
                                                closedDates.remove(d);
                                                check = true;
                                                break;
                                            }
                                        }
                                        System.out.println(check ? "Date successfully removed." : "Error: no such date found.");
                                        check = true;
                                    } catch (DateTimeParseException e) {
                                        System.out.println();
                                        System.out.println("Date/Time format is invalid. Please use the following format: yyyy-mm-dd and hh:mm.");
                                    } catch (Exception e) {
                                        System.out.println("An error has occurred " + e.getMessage());
                                    }
                                }
                            }
                            case 3 -> System.out.println("Returning to the main menu.");
                            default -> System.out.println("Error: Invalid input. Try again.");
                        }
                    }
                }
                case 5 -> editBooking(bookings, scanner);
                case 6 -> {
                    System.out.println();
                    System.out.println("Please enter the password: ");
                    String enterPassword = scanner.nextLine();
                    if (enterPassword.equals(financePassword)) {

                        check = false;
                        ArrayList<Booking> pastBookings = FileManager.getBookings("PastBookings");
                        LocalDate searchDate = null;

                        while(!check) {
                            try {
                                System.out.println();
                                System.out.println("Enter date to view transactions for in format yyyy-mm-dd.");
                                searchDate = LocalDate.parse(scanner.nextLine());
                                check = true;
                            }catch(DateTimeParseException e) {
                                System.out.println();
                                System.out.println("Date format is invalid. Please use the following format: yyyy-mm-dd.");
                            } catch (Exception e) {
                                System.out.println("An error has occurred " + e.getMessage());
                            }
                        }
                        check = false;
                        for(Booking b: pastBookings){
                            if(searchDate.isEqual(b.date)) {
                                System.out.println(b);
                                check = true;
                            }
                        }

                        if(!check)
                            System.out.println("Error: No bookings found for the given date.");

                    } else {
                        System.out.println();
                        System.out.println("Incorrect password. Please try again ");
                    }
                }
                case 7 -> {
                    try {
                        ArrayList<Booking> backupData = FileManager.getBookings("Backup");
                        bookings.addAll(backupData);
                        System.out.println();
                        System.out.println(backupData.isEmpty() ? "Error: No data to recover." : "Data successfully recovered.");
                    } catch (IOException e){
                        System.out.println("Error: Failed to read recovery data.");
                    }
                }
                case 8 -> {
                    FileManager.saveBookings(bookings);
                    FileManager.saveClosedDays(closedDates);
                    FileManager.backupBookings(new ArrayList<>());
                    System.out.println();
                    System.out.println("Thanks for using our salon booking system. Goodbye!");
                }
                default -> System.out.println("Error. Invalid input. Try again");
            } //switch
        } while (choice != 8);
        scanner.close();
    }

    private static Booking getBookingDetails(Scanner scanner) {

        while (true) {
            try {
                System.out.println();
                System.out.println("Pleaser enter the following:");
                System.out.println("Name: ");
                String name = scanner.nextLine();
                LocalDate date;
                LocalTime time;
                System.out.println("Enter booking date in format yyyy-mm-dd.");
                date = LocalDate.parse(scanner.nextLine());
                System.out.println("Enter booking time in format hh:mm.");
                time = LocalTime.parse(scanner.nextLine());
                System.out.println("Enter note: ");
                String note = scanner.nextLine();
                int amount = 0;
                boolean paymentReceived = false;

                return new Booking(name, note, date, time, amount, paymentReceived);
            } catch (DateTimeParseException e) {
                System.out.println();
                System.out.println("Date/Time format is invalid. Please use the following format: yyyy-mm-dd and hh:mm.");
            } catch (Exception e) {
                System.out.println("An error has occurred " + e.getMessage());
                e.printStackTrace();
            }
        }
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

    static void searchTimes(ArrayList<Booking> list, LocalDate searchDate, ArrayList<LocalTime> times, ArrayList<LocalDate> closedDates) { //Severin - 26/10
        try{
            if (searchDate.getDayOfWeek() == DayOfWeek.SATURDAY || searchDate.getDayOfWeek() == DayOfWeek.SUNDAY || closedDates.contains(searchDate)) {
                System.out.println();
                System.out.println("Error: The salon is not open for business on this day.");
            } else {

                // Loop runs for selected date and four business days into the future.

                for (int i = 1; i <= 5; ) {
                    if (searchDate.getDayOfWeek() == DayOfWeek.SATURDAY || searchDate.getDayOfWeek() == DayOfWeek.SUNDAY || closedDates.contains(searchDate)) {
                        searchDate = searchDate.plusDays(1);
                        continue;
                    }

                    // Bookings with matching dates are added to a new ArrayList in order to improve runtime when checking against times.

                    ArrayList<Booking> matchingDate = new ArrayList<>();
                    boolean check;

                    for (Booking b : list) {
                        if (b.date.isEqual(searchDate))
                            matchingDate.add(b);
                    } //for

                    System.out.println();
                    System.out.println("Available times for " + searchDate + ":");
                    System.out.println();

                    // For each time, checks each booking on the matching date to see if any times match.
                    // If not, prints the time as available.
                    // I figure there's probably a better way to do this.

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
            }//else
        }catch (Exception e){
            System.out.println("An error occurred: "+e.getMessage());
            e.printStackTrace();
        }
    }//searchTimes

    private static void cancelBooking(ArrayList<Booking> bookings, Scanner scanner) {
        boolean t = true;

        while (t) {
            try {
                System.out.println();
                System.out.println("Enter the date of the booking in format yyyy-mm-dd.");
                LocalDate searchDate = LocalDate.parse(scanner.nextLine());

                System.out.println("Enter the time of the booking in format hh:mm.");
                LocalTime searchTime = LocalTime.parse(scanner.nextLine());

                Booking bookingToRemove = null;

                for (Booking booking : bookings) {
                    if (booking.getDate().equals(searchDate) && booking.getTime().equals(searchTime)) {
                        bookingToRemove = booking;
                        System.out.println();
                        System.out.println("Booking successfully cancelled.");
                        t = false;
                        break;
                    }
                }
                if (bookingToRemove!=null){
                    bookings.remove(bookingToRemove);
                }
                else {
                    System.out.println();
                    System.out.println("No booking found on this date.");
                }
            }catch (DateTimeParseException e) {
                System.out.println();
                System.out.println("Date/Time format is invalid.");
                t = true;
            } catch (Exception e) {
                System.out.println("An error has occurred " + e.getMessage());
                e.printStackTrace();
                t = true;
            }
        }
    }

    private static void editBooking(ArrayList<Booking> bookings, Scanner scanner) {
        boolean check = true;

        while (check) {
            try {
                System.out.println();
                ArrayList<Booking> pastBookings = new ArrayList<>();
                Booking bookingToEdit = null;

                // Prompt user to enter date and time for searching.
                System.out.println("Enter the date of the booking in format yyyy-mm-dd.");
                LocalDate searchDate = LocalDate.parse(scanner.nextLine());
                System.out.println("Enter the time of the booking in format hh:mm.");
                LocalTime searchTime = LocalTime.parse(scanner.nextLine());

                // If the search date is in the past, retrieve past bookings.
                if(searchDate.isBefore(LocalDate.now())) {
                    pastBookings = FileManager.getBookings("PastBookings");
                    // Iterate through past bookings to find a matching booking.
                    for (Booking booking : pastBookings) {
                        if (booking.getDate().equals(searchDate) && booking.getTime().equals(searchTime)) {
                            bookingToEdit = booking; // Assign the found booking to bookingToEdit.
                            break;
                        }
                    }
                }
                if(bookingToEdit==null) {
                    for (Booking booking : bookings) {
                        if (booking.getDate().equals(searchDate) && booking.getTime().equals(searchTime)) {
                            bookingToEdit = booking;
                            break;
                        }
                    }
                }

                System.out.println();
                if (bookingToEdit != null) { // If a booking was found.
                    System.out.println("Current booking details:");
                    System.out.println(bookingToEdit);

                    System.out.println("Enter new transaction amount: ");
                    double newAmount = scanner.nextDouble(); // Prompt for a new transaction amount.
                    scanner.nextLine();

                    System.out.println("Was payment received? (y/n): ");
                    String paymentReceivedInput = scanner.nextLine();// Prompt for payment received.
                    boolean newPaymentReceived = paymentReceivedInput.equalsIgnoreCase("y");

                    // Update the transaction details of the booking.
                    bookingToEdit.transaction.setAmount(newAmount);
                    bookingToEdit.transaction.setPaymentReceived(newPaymentReceived);

                    System.out.println("Booking edited successfully!");

                    check=false; // Exit the while loop.

                    // Save the updated bookings to the appropriate file.
                    if (pastBookings.isEmpty()) {
                        FileManager.saveBookings(bookings);
                    } else {
                        FileManager.saveBookings(bookings, pastBookings);
                    }
                } else {
                    System.out.println("Error: No matching booking found.");
                }
            }catch (DateTimeParseException e) {
                System.out.println();
                System.out.println("Date/Time format is invalid. Please use the following format: yyyy-mm-dd and hh:mm.");
                check = true; // Continue the while loop.
            } catch (Exception e) {
                System.out.println("An error has occurred " + e.getMessage());
                e.printStackTrace();
                check = true; // Continue the while loop.
            }
        }
    }
    static void showBookings(ArrayList<Booking> list,ArrayList<LocalDate> closedDates, LocalDate date){
        try {
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY || closedDates.contains(date)) {
                System.out.println();
                System.out.println("Error: The salon is not open for business on this day.");
            } else {
                boolean check = false;

                // I've opted to make this code a little more complex in order to dramatically speed up sorting.
                // I don't know if it's worth it, but I choose to believe that it is.
                // Especially when the system is going to handle ~1000 bookings at a time.

                ArrayList<Booking> sortedList = new ArrayList<>();

                for(Booking b: list){
                    if(b.date.isEqual(date)) {
                        sortedList.add(b);
                        check = true;
                    }
                }

                // Sorts the list by booking time so that they display in order.

                sortedList.sort(null);
                System.out.println();
                for (Booking b : sortedList) {
                    System.out.println(b);
                }
                if (!check) {
                    System.out.println("No bookings found for that date.");
                }
            }
        }catch (Exception e) {
            System.out.println("An error has occurred: "+e.getMessage());
            e.printStackTrace();
        }
    }
}