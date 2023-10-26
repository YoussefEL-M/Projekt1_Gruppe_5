//Project1_Group_5 - Severin

package BookingSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class FileManager {
    public static void main (String[] marina) throws IOException {
        ArrayList<Booking> list = getBookings();
        for(Booking b: list){
            System.out.println(b);
        }
    }

    static ArrayList<Booking> getBookings() throws IOException {
        ArrayList<Booking> list = new ArrayList<>();

        FileReader file = new FileReader("Bookings.txt");
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
} //class
