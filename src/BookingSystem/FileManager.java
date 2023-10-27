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

    static ArrayList<Booking> getBookings(String fileName) throws IOException {
        ArrayList<Booking> list = new ArrayList<>();

        FileReader file = new FileReader(fileName+".txt");
        BufferedReader in = new BufferedReader(file);
        String line = in.readLine();

        while(line!=null){
            String[] bits = line.split(",");
            String name = bits[0];
            String note = bits[1];
            LocalDate date = LocalDate.parse(bits[2]);
            LocalTime time = LocalTime.parse(bits[3]);
            double amount = Double.parseDouble(bits[4]);
            boolean paymentReceived = Boolean.getBoolean(bits[5]);

            list.add(new Booking(name,note,date,time,amount,paymentReceived));
            line = in.readLine();
        } //while
        file.close();
        return list;
    } //getBookings

    static void saveBookings(ArrayList<Booking> list) throws IOException {
        ArrayList<Booking> secondList = getBookings("PastBookings");

        FileWriter file = new FileWriter("Bookings.txt");
        FileWriter secondFile = new FileWriter("PastBookings.txt");
        PrintWriter out = new PrintWriter(file);
        PrintWriter secondOut = new PrintWriter(secondFile);

        for(Booking b: secondList){
            secondOut.println(b.name+","+b.note+","+b.date+","+b.time+","+b.transaction.getAmount()+","+b.transaction.getPaymentReceived());
        }

        for(Booking b: list){
            if(b.date.isBefore(LocalDate.now())){
                secondOut.println(b.name+","+b.note+","+b.date+","+b.time+","+b.transaction.getAmount()+","+b.transaction.getPaymentReceived());
            }
            else{
                out.println(b.name+","+b.note+","+b.date+","+b.time+","+b.transaction.getAmount()+","+b.transaction.getPaymentReceived());
            }
        } //for
        file.close();
        secondFile.close();
    } //saveBookings

    static ArrayList<LocalDate> getClosedDays() throws IOException {
        ArrayList<LocalDate> list = new ArrayList<>();
        FileReader file = new FileReader("ClosedDays.txt");
        BufferedReader in = new BufferedReader(file);
        String line = in.readLine();

        while(line!=null){
            LocalDate date = LocalDate.parse(line);
            list.add(date);
            line = in.readLine();
        } //while
        file.close();
        return list;
    } //getClosedDays

    static void saveClosedDays(ArrayList<LocalDate> list) throws IOException {
        FileWriter file = new FileWriter("ClosedDays.txt");
        PrintWriter out = new PrintWriter(file);

        for(LocalDate d: list){
            if(!d.isBefore(LocalDate.now()))
                out.println(d);
        }
        file.close();
    } //saveClosedDays
} //class
