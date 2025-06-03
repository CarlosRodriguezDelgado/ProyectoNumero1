
public class ClientFacade {
    // Se llamaran todos los metodos desde aca
    private ClientService service;
    

    public ClientFacade(ClientService service) {
       this.service = service;

    }

    public boolean logIn(String user, String password){
        return service.authenticateUser(user, password);
    }

    public boolean logOut(String username){
        return service.logout(username);
    }

    public String createAcount(String username, String accountNumber){
       return service.createAcount(username, accountNumber);
    }
    public String createAnInvestmentCertificate(String clientId, String accountNumber, double amount) {
        return service.createInvestment(clientId, accountNumber, amount);
    }

    public String deposit(String username, String accountNumber, double amount) throws Exception {
        return service.deposit(username, accountNumber, amount);
    }

    public String withdraw(String username, String accountNumber, double amount) throws Exception {
        return service.withdraw(username, accountNumber, amount);
    }

   
    public String listarInversiones(String username) {
        return service.listInversions(username);
    }

    public String listarCuentas(String username) {
        return service.AccountList(username);
    }

}


