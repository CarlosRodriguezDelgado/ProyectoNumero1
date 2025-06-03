public class Cuenta{
    private String accountNumber;
    private double balance;

    public Cuenta(){}


    public Cuenta(String accountNumber, double balance){
        this.accountNumber = accountNumber;
        this.balance =  balance;
    }

    public Cuenta(String accountNum){
        this.accountNumber = accountNum;
        this.balance = 0;
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
    
    public boolean deposit(double amount) throws Exception{
        boolean success = false;
        if(amount <= 0) throw new Exception("El monto a depositar debe ser positivo.");
      
        this.balance += amount;
        success = true;

        return success;
    
    }

    public boolean withdraw(double amount){
        boolean success = false;
        if(amount < 0 ) throw new IllegalArgumentException("El monto a depositar no puede ser menor a 0");
    
        if(balance > amount){
            balance = balance - amount;
            success = true;
        }else{
            throw new IllegalStateException("No tiene fondos suficinetes, su saldo es de" + this.balance);
        }

        return success;
    }
    
}