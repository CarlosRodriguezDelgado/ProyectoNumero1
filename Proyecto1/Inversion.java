import java.util.*;
import java.util.concurrent.*;

public class Inversion {
    private final String id;
    private final double amountInvesment;
    private final Cuenta sourceAccount;
    private static final ScheduledExecutorService schedulerRetribution = Executors.newScheduledThreadPool(5); //How many threads can run

    public Inversion(String id, double amount, Cuenta originAccount){
        this.id = id;
        this.amountInvesment = amount;
        this.sourceAccount = originAccount;

        scheduleReimbursement(); //Hace el reintegro internamente en el instante en que se hace la inversion
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

    //Retorna la inversion despues de un minuto
    private void scheduleReimbursement(){
        schedulerRetribution.schedule(() ->{
            double totalreturn = amountInvesment  * 0.10;
            try {
                sourceAccount.deposit(totalreturn);
            } catch (Exception e) {
                e.printStackTrace();
            } 
        },1, TimeUnit.MINUTES);
    }

    public void scheduleShutdown(){
        schedulerRetribution.shutdown();
    }

}
