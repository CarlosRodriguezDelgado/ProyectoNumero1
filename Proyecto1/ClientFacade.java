public class ClientFacade{
//Usar en ClientHandler y la parte de app dependiendo de la funcionalidad
private ClientService service;

public ClientFacade(){
    service = new ClientService();
}


public boolean createAnInvestmentCertificate(String clientId, String accountNumber, double amount){
    return service.createInvestment(clientId, accountNumber, amount);
}


}