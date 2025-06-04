import java.util.*;

public class Cliente{
    private String id;
    private String username;
    private String password;
    private List<Cuenta> accounts;;
    private List<Inversion> investments;
    private Boolean loggedIn;
    

    public Cliente(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<>();
        this.investments = new ArrayList<>();
        this.loggedIn = false;
    }

     public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<Cuenta> getAccounts() {
        return accounts;
    }

    public List<Inversion> getInvestments() {
        return investments;
    }
        
    
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

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean verifyPassword(String password){
        return this.password.equals(password);
    }

    public boolean addAccount(Cuenta account){
        
        if (findAccount(account.getAccountNumber()) != null) {
            throw new IllegalArgumentException("La cuenta con número " + account.getAccountNumber() + " ya existe.");
        }

        accounts.add(account);
        return true;
    }

    public boolean addInvestment(Inversion investment){
     this.investments.add(investment);
     return true;
    }
    

    public Cuenta findAccount(String accountNumber){
        return accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst().orElse(null);
    }
}