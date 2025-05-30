import java.util.*;
import java.util.concurrent.*;

public class Inversion {
    private final String id;
    private final double amountInvesment;
    private final Cuenta sourceAccount;
    private static final ScheduledExecutorService schedulerRetribution = Executors.newScheduledThreadPool(5); //Hardcode 4 clients
    

    public Inversion(String id, double amount, Cuenta originAccount){
        this.id = id;
        this.amountInvesment = amount;
        this.sourceAccount = originAccount;

        validateWithdraw();
        scheduleReimbursement(); 
    }

    public String getId(){
        return id;
    }

    public double getAmountInvesment() {
        return amountInvesment;
    }

    public String getSourceAccount() {
        return sourceAccount.getAccountNumber();
    }

    //This method validates if is possible to withdraw the amount of investment and stop it if is necessary
    private void validateWithdraw(){
        if(!sourceAccount.withdraw(amountInvesment)){
            throw new IllegalArgumentException("No se puede realizar la inversion porque no hay fondos suficientes");
        }
    }
    
    //This method returns the investment after a minute
    private void scheduleReimbursement(){
        schedulerRetribution.schedule(() ->{
            double totalreturn = amountInvesment + (amountInvesment * 0.10);
            sourceAccount.deposit(totalreturn); // returns the investment with the winnings
        },1, TimeUnit.MINUTES);
    }

}
