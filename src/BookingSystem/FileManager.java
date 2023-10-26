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
        list.add(new Booking("Please Be Happy",LocalDate.parse("2023-11-21"),LocalTime.parse("22:42:00")));
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
            LocalDate date = LocalDate.parse(bits[1]);
            LocalTime time = LocalTime.parse(bits[2]);

            list.add(new Booking(name,date,time));
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
            secondOut.println(b.name+","+b.date+","+b.time);
        }

        for(Booking b: list){
            if(b.date.isBefore(LocalDate.now())){
                secondOut.println(b.name+","+b.date+","+b.time);
            }
            else{
                out.println(b.name+","+b.date+","+b.time);
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
