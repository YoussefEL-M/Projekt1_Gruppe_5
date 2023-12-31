//Project1_Group_5 DAT23B

package BookingSystem;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class FileManager {
    public static void main (String[] marina) throws IOException {
        ArrayList<Booking> list = getBookings("Bookings");
        ArrayList<Booking> listTwo = getBookings("PastBookings");
        for(Booking b: list){
            System.out.println(b);
        }
        list.get(0).transaction.setPaymentReceived(true);
        //list.add(new Booking()));
        saveBookings(list,listTwo);
    }

    static ArrayList<Booking> getBookings(String fileName) throws IOException {
        ArrayList<Booking> list = new ArrayList<>();

        FileReader file = new FileReader(fileName+".txt");
        BufferedReader in = new BufferedReader(file);
        String line = in.readLine();

        // Reads each line and parses it as a Booking object.

        while(line!=null){
            String[] bits = line.split(",");
            String name = bits[0];
            String note = bits[1];
            LocalDate date = LocalDate.parse(bits[2]);
            LocalTime time = LocalTime.parse(bits[3]);
            double amount = Double.parseDouble(bits[4]);
            boolean paymentReceived = Boolean.parseBoolean(bits[5]);

            list.add(new Booking(name,note,date,time,amount,paymentReceived));
            line = in.readLine();
        } //while
        file.close();
        return list;
    } //getBookings

    static void saveBookings(ArrayList<Booking> list) throws IOException {
        FileWriter file = new FileWriter("Bookings.txt");
        FileWriter secondFile = new FileWriter("PastBookings.txt",true);
        PrintWriter out = new PrintWriter(file);
        BufferedWriter secondOut = new BufferedWriter(secondFile);
        ArrayList<Booking> toRemove = new ArrayList<>();

            // Sorts bookings so that past bookings are written to the PastBookings text file.

            for(Booking b: list){
            if(b.date.isBefore(LocalDate.now())){
                secondOut.write(b.name+","+b.note+","+b.date+","+b.time+","+b.transaction.getAmount()+","+b.transaction.getPaymentReceived());
                secondOut.newLine();
                toRemove.add(b);
            }
            else{
                out.println(b.name+","+b.note+","+b.date+","+b.time+","+b.transaction.getAmount()+","+b.transaction.getPaymentReceived());
            }
        } //for

        // Removes past bookings from current Bookings ArrayList; prevents duplication for date before current date.

        list.removeAll(toRemove);
        secondOut.close();
        file.close();
        secondFile.close();
    } //saveBookings

    static void saveBookings(ArrayList<Booking> list, ArrayList<Booking> pastList) throws IOException {

        // This method overwrites the existing PastBookings file and is called only when edits have been made to past bookings.

        FileWriter file = new FileWriter("Bookings.txt");
        FileWriter secondFile = new FileWriter("PastBookings.txt");
        PrintWriter out = new PrintWriter(file);
        PrintWriter secondOut = new PrintWriter(secondFile);
        ArrayList<Booking> toRemove = new ArrayList<>();

        for(Booking b: pastList){
            secondOut.println(b.name+","+b.note+","+b.date+","+b.time+","+b.transaction.getAmount()+","+b.transaction.getPaymentReceived());
        }

        for(Booking b: list){
            if(b.date.isBefore(LocalDate.now())){
                secondOut.println(b.name+","+b.note+","+b.date+","+b.time+","+b.transaction.getAmount()+","+b.transaction.getPaymentReceived());
                toRemove.add(b);
            }
            else{
                out.println(b.name+","+b.note+","+b.date+","+b.time+","+b.transaction.getAmount()+","+b.transaction.getPaymentReceived());
            }
        } //for
        list.removeAll(toRemove);
        file.close();
        secondFile.close();
    } //saveBookings

    static ArrayList<LocalDate> getClosedDays() throws IOException {
        ArrayList<LocalDate> list = new ArrayList<>();
        FileReader file = new FileReader("ClosedDays.txt");
        BufferedReader in = new BufferedReader(file);
        String line = in.readLine();

        // Reads each line and parses it as a LocalDate.

        while (line != null) {
            LocalDate date = LocalDate.parse(line);
            list.add(date);
            line = in.readLine();
        }
        file.close();

        return list;
    } //getClosedDays

    static void saveClosedDays(ArrayList<LocalDate> list) throws IOException {
        FileWriter file = new FileWriter("ClosedDays.txt");
        PrintWriter out = new PrintWriter(file);
            for (LocalDate d: list) {
                if (!d.isBefore(LocalDate.now()))
                    out.println(d);
            }
            file.close();
    } //saveClosedDays

    static void backupBookings(ArrayList<Booking> list) throws IOException {
        FileWriter file = new FileWriter("Backup.txt");
        PrintWriter out = new PrintWriter(file);
        for (Booking b: list) {
            out.println(b.name+","+b.note+","+b.date+","+b.time+","+b.transaction.getAmount()+","+b.transaction.getPaymentReceived());
        }
        file.close();
    }
} //class
