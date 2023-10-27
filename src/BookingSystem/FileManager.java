//Project1_Group_5 DAT23B

package BookingSystem;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class FileManager {
    public static void main (String[] marina) throws IOException {
        ArrayList<Booking> list = getBookings("Bookings");
        for(Booking b: list){
            System.out.println(b);
        }
        //list.add(new Booking()));
        saveBookings(list);
    }

    static ArrayList<Booking> getBookings(String fileName) {
        ArrayList<Booking> list = new ArrayList<>();

        try (BufferedReader in = new BufferedReader((new FileReader(fileName + ".txt")))) {
            String line = in.readLine();

            while (line != null) {
                String[] bits = line.split(",");
                String name = bits[0];
                String note = bits[1];
                LocalDate date = LocalDate.parse(bits[2]);
                LocalTime time = LocalTime.parse(bits[3]);
                double amount = Double.parseDouble(bits[4]);
                boolean paymentReceived = Boolean.parseBoolean(bits[5]);

                list.add(new Booking(name, note, date, time, amount, paymentReceived));
                line = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    } //getBookings

    static void saveBookings(ArrayList<Booking> list) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter("Bookings.txt"));
             PrintWriter secondOut = new PrintWriter(new FileWriter("PastBookings.txt"))) {

            ArrayList<Booking> secondList = getBookings("PastBookings");

            for (Booking b : secondList) {
                secondOut.println(b.name + "," + b.note + "," + b.date + "," + b.time + "," + b.transaction.getAmount() + "," + b.transaction.getPaymentReceived());
            }

            for (Booking b : list) {
                if (b.date.isBefore(LocalDate.now())) {
                    secondOut.println(b.name + "," + b.note + "," + b.date + "," + b.time + "," + b.transaction.getAmount() + "," + b.transaction.getPaymentReceived());
                } else {
                    out.println(b.name + "," + b.note + "," + b.date + "," + b.time + "," + b.transaction.getAmount() + "," + b.transaction.getPaymentReceived());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ArrayList<LocalDate> getClosedDays() {
        ArrayList<LocalDate> list = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader("ClosedDays.txt"))) {
            String line = in.readLine();

            while (line != null) {
                LocalDate date = LocalDate.parse(line);
                list.add(date);
                line = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }

        return list;
    } //getClosedDays

    static void saveClosedDays(ArrayList<LocalDate> list) {
        try (PrintWriter out = new PrintWriter(new FileWriter("ClosedDays.txt"))) {
            for (LocalDate d : list) {
                if (!d.isBefore(LocalDate.now()))
                    out.println(d);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception properly (log it, show a message, etc.)
        }
    } //saveClosedDays
} //class
