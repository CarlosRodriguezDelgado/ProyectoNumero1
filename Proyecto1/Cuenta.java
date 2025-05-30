public class Cuenta{
    private String accountNumber;
    private double balance;

    public Cuenta(){}


    public Cuenta(String accountNumber, double balance){
        this.accountNumber = accountNumber;
        this.balance =  balance;
    }

    public String getAccountNumber(){
        return this.accountNumber;
    }

    public double getBalance(){
        return this.balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }
    
    public synchronized boolean deposit(double amount){
        boolean success = false;
        if(amount > 0){
            this.balance =+ amount;
            success = true;
        }
        return success;
    
    }

    public synchronized boolean withdraw(double amount){
        boolean success = false;
        if(balance > amount){
            balance = balance - amount;
            success = true;
        }else{
            IOManager.escribir("No tiene fondos suficientes");
        }

        return success;
    }
    

}