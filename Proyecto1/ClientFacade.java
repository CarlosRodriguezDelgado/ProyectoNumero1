import java.util.List;

public class ClientFacade {
    // Usar en ClientHandler y la parte de app dependiendo de la funcionalidad
    private ClientService service;
    private Cliente cliente;

    public ClientFacade(Cliente c) {
        service = new ClientService();
        this.cliente= c;

    }

    public boolean logIn(String user, String password){
        return service.authenticateUser(user, password);
    }

    public boolean logOut(String username){
        return service.logout(username);
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

   
    public String listarInversiones() {
        return service.listInversions(cliente.getUsername());
    }

    public String listarCuentas() {
        return service.AccountList(cliente.getUsername());
    }

}


