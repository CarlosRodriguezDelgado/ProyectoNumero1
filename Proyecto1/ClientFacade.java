
public class ClientFacade {
    // Usar en ClientHandler y la parte de app dependiendo de la funcionalidad
    private ClientService service;
    private Cliente cliente;

    public ClientFacade(ClientService service) {
       this.service = service;

    }

    public boolean logIn(String user, String password){
        return service.authenticateUser(user, password);
    }

    public boolean logOut(String username){
        return service.logout(username);
    }

    public void createAcount(String username, String accountNumber, double amount){
        service.createAcount(username, accountNumber, amount);
    }
    public String createAnInvestmentCertificate(String clientId, String accountNumber, double amount) {
        return service.createInvestment(clientId, accountNumber, amount);
    }

    public boolean deposit(String username, String accountNumber, double amount) throws Exception {
        return service.deposit(username, accountNumber, amount);
    }

    public boolean retirar(String username, String accountNumber, double amount) throws Exception {
        return service.withdraw(username, accountNumber, amount);
    }

   
    public String listarInversiones(String username) {
        return service.listInversions(username);
    }

    public String listarCuentas(String username) {
        return service.AccountList(username);
    }

}


