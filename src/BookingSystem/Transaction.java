//Project1_Group_5 DAT23B

package BookingSystem;
public class Transaction{

    private double amount;
    private boolean paymentReceived = false;

    public void setAmount(double amount){
        this.amount=amount;
    }
    public double getAmount() {
        return amount;
    }
    public void setPaymentReceived(boolean paymentReceived){
        this.paymentReceived = paymentReceived;
    }

    public String toString(){
        String status;
        if (paymentReceived) {
            status = "Received";
        } else {
            status = "Not Received, customer owes: "+amount;
        }
        return "Amount: " + amount + ", Payment: " + status;
    }

}