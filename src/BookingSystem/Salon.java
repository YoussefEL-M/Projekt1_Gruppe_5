package BookingSystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Salon {
    private ArrayList<LocalDateTime> bookingList = new ArrayList<>();

    public Salon() {
        LocalDate today = LocalDate.now();
        LocalDate threeMonthsLater = today.plusMonths(3);
        while (today.isBefore(threeMonthsLater)) {
            if (today.getDayOfWeek() != DayOfWeek.SATURDAY && today.getDayOfWeek() != DayOfWeek.SUNDAY) {
                for (int hour = 10; hour < 18; hour++) {
                    bookingList.add(LocalDateTime.of(today, LocalTime.of(hour, 0)));
                    bookingList.add(LocalDateTime.of(today, LocalTime.of(hour, 30)));
                }
            }
            today = today.plusDays(1);
        }
    }

        public static void main (String[] args){
            Scanner scanner = new Scanner(System.in);
            int choice;
            Salon salon = new Salon();

            System.out.println("Avaliable Booking List times: " + salon.bookingList);

            do {
                System.out.println("Welcome to Harry's Salon!");
                System.out.println("Hairy?");
                System.out.println("See Cotter!");
                System.out.println("1. Book appointment");
                System.out.println("2. Cancel appointment");
                System.out.println("3. Add closed date");
                System.out.println("4. Add product");
                System.out.println("5. View services");
                System.out.println("6. View finances");
                System.out.println("7. Exit");
                System.out.print("Please enter your choice: \n");

                choice = scanner.nextInt();
                scanner.nextLine();
                String message = null;
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
                        Booking newBook = new Booking(name, LocalDate.of(year, month, day), time);
                        newBook.createBooking(salon.bookingList);
                    }
                    case 2 -> {
                        message = "Pleaser enter the following:";
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
                        Booking removeBook = new Booking(name, LocalDate.of(year, month, day), time);
                        removeBook.removeBooking(salon.bookingList);

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
                    }
                    case 5 -> {
                    }
                    case 6 -> {
                    }
                    case 7 -> message = "Thanks for using our salon booking system. Goodbye!";
                    default -> message = "Error. Invalid input. Try again";
                }
                System.out.println(salon.bookingList);
            } while (choice != 7);
            scanner.close();

        }

        public void printBooking () {
        }

        public void addClosedDate(LocalDate closedDate) {
            LocalDate nextDay = closedDate;
            LocalDateTime startOfDay = LocalDateTime.of(closedDate, LocalTime.of(10, 0));
            LocalDateTime endOfDay = LocalDateTime.of(closedDate, LocalTime.of(18, 0));

            while (startOfDay.isBefore(endOfDay)) {
                if (bookingList.contains(startOfDay)) {
                    System.out.println("Removing time on closed date: " + startOfDay);
                    bookingList.remove(startOfDay);
                }
                startOfDay = startOfDay.plusMinutes(30);
            }
            System.out.println("Adding closed date: " + closedDate);

        }
}



