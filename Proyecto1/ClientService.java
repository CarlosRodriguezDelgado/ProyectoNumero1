import java.util.*;

public class ClientService{
    //Toda la logica del banco estará aca import java.util.*;
    private List<Cliente> clientsList;
    
    public ClientService(){
        clientsList = new ArrayList<>();
        clientsList.add(new Cliente("u1", "ana", "1234" ));
        clientsList.add(new Cliente("u2", "julian", "key" ));
        clientsList.add(new Cliente("u3", "jace", "12345" ));
        clientsList.add(new Cliente("u4", "bryce", "key1" ));
        clientsList.add(new Cliente("u5", "ines", "abcd" ));

    }

    public Cliente getClientById(String clientId){
        return clientsList.stream()
                .filter(client -> client.getId().equals(clientId)) //Makes a comparing between the clients registered and the provided id
                .findFirst().orElse(null); // Will find the first who´s make match or return null in case it didn´t found anything
    }
    

    public boolean createInvestment(String clientId, String accountNumber, double amount){
        //First we need to know if the client exists
        Cliente client = getClientById(clientId);
        boolean success = false;
        //Know if the client if logged 

        //Then find the account number in the list of clients - method on client

        //Last, procced with the invesment

       return success;
    }




 

}

