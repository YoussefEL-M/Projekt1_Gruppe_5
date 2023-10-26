package BookingSystem;
import java.util.*;
public class Transaction{

    private double amount;
    private boolean paymentReceived = false;

    public void setAmount(double amount){
        this.amount=amount;
    }
    public boolean setPaymentReceived(boolean paymentReceived){
        this.paymentReceived = paymentReceived;
        return paymentReceived;
    }

    public String toString(){
        String status;
        if (paymentReceived) {
            status = "Received";
        } else {
            status = "Not Received";
        }
        return "Amount: " + amount + ", Payment: " + status;
    }

}