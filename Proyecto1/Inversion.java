public class Inversion {
    private String id;
    private double balance;
    private String originAccount;

    public Inversion(String id, double monto, String originAccount){
        this.id = id;
        this.balance = monto;
        this.originAccount = originAccount;
    }

    public String getId(){
        return id;
    }


}
