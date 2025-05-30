import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientService{
    //Toda la logica del banco estará aca
    private List<Cliente> clientsList;
    private static final AtomicInteger counter = new AtomicInteger(1);    
    public ClientService(){
        clientsList = new ArrayList<>();
        clientsList.add(new Cliente("u1", "ana", "1234" ));
        clientsList.add(new Cliente("u2", "julian", "key" ));
        clientsList.add(new Cliente("u3", "jace", "12345" ));
        clientsList.add(new Cliente("u4", "bryce", "key1" ));
        clientsList.add(new Cliente("u5", "ines", "abcd" ));

    }

    
    public boolean authenticateUser(String userName, String password){
        Cliente client = getClientByUser(userName);
        
        if(client != null && client.verifyPassword(password)){
            client.setLoggedIn(true);
            return true; 
        }

        return false;
    }

    public void logout(String clientId){
        Cliente cliente = getClientById(clientId);

        if(cliente != null && cliente.isLoggedIn()) cliente.setLoggedIn(false);
    }

    public Cliente getClientById(String clientId){
        return clientsList.stream()
                .filter(client -> client.getId().equals(clientId)) //Makes a comparing between the clients registered and the provided id
                .findFirst().orElse(null); // Will find the first who´s make match or return null in case it didn´t found anything
    }

    public  Cliente getClientByUser(String clientUser){
        return clientsList.stream()
                .filter(client -> client.getUsername().equals(clientUser))
                .findFirst().orElse(null);
    }
    

    public String createInvestment(String clientId, String accountNumber, double amount){
        //First we need to know if the client exists
        Cliente client = getClientById(clientId);
        //Know if the client if logged  
        if(!client.isLoggedIn() || client == null) return "No se pudo realizar la inversion";

        //Then find the account number in the list of clients 
        Cuenta clientAccount = client.findAccount(accountNumber);
        if(clientAccount == null) return "No se pudo realizar la inversion";
 
        //Last, procced with the invesment
        //Create an ID for the invesment
        String idInvesment = "INVEST-" + counter.getAndIncrement();
        Inversion investment = new Inversion(idInvesment, amount, clientAccount); 
        client.addInvestment(investment);

        return "Cuenta " + investment.getSourceAccount() + " - " + investment.getId() 
        + (investment.getAmountInvesment() + (investment.getAmountInvesment() * 1.1)) + "de certificado de ganancias"; 

        
    }
 
}

