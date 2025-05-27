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
    
    

}