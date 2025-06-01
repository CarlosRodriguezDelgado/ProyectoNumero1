public class ClientFacade {
    // Usar en ClientHandler y la parte de app dependiendo de la funcionalidad
    private ClientService service;

    public ClientFacade() {
        service = new ClientService();

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

}