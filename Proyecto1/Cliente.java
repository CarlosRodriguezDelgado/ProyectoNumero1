
import java.io.Serializable;


public class Cliente implements Serializable{
    IOManager ioManager = new IOManager();
    private String id;
    private String username;
    private String password;
    //List<Account> accounts = new List<Account>();
    //List<Investment> investments = new List<Investment>();
    private Boolean loggedIn = false;

    public Cliente(String id, String username, String password) {
       this.id = id;
        this.username = username;
        this.password = password;
        //this.accounts = new ArrayList<>();
        //this.investments = new ArrayList<>();
        this.loggedIn = false;
    }

     public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

   /* public List<Account> getAccounts() {
        return accounts;
    }

    public List<Investment> getInvestments() {
        return investments;
    }
        */
    
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoggedIn(Boolean LoggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean verifyPassword(String password){
        return this.password.equals(password);
    }

   /*  public void addAccount(Account account){
    boolean cuentaExiste = false;
    for (int i = 0; i < this.accounts.size(); i++) { 
        Account cuentaExistente = this.accounts.get(i);
        if (cuentaExistente.getNumber().equals(account.getNumber())) {
            cuentaExiste = true; 
            break;
        }
    }
        if (cuentaExiste) {
        ioManager.escribir("La cuenta con número " + account.getNumber() + " ya existe.");
        return false;
    }
        this.accounts.add(account);
        return true;
    }

    public boolean addInvestment(Investment investment){
     this.investments.add(investment);
     return true;
    }
    */
}
