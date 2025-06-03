import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientService{
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

    public boolean logout(String clientUser){
        Cliente cliente = getClientByUser(clientUser);

        if(cliente != null && cliente.isLoggedIn()) cliente.setLoggedIn(false);
        return true;
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
    
    public void createAcount(String username, String accountNumber, double amount){

        Cliente client = getClientByUser(username);
        
        if(client == null) throw new IllegalAccessError("Cliente no existente");

        Cuenta cuenta = new Cuenta(accountNumber, amount);
        client.addAccount(cuenta);
    }

    public String createInvestment(String username, String accountNumber, double amount){
        //First we need to know if the client exists
        Cliente client = getClientByUser(username);
        
        if(client == null) throw new IllegalAccessError("Cliente no existente");

        //Then find the account number in the list of clients 
        Cuenta clientAccount = client.findAccount(accountNumber);
        if(clientAccount == null) return "No se pudo realizar la inversion";
 
        //Last, procced with the invesment
        //Create an ID for the invesment
        String idInvesment = "INVEST-" + counter.getAndIncrement();
        Inversion investment = new Inversion(idInvesment, amount, clientAccount); 
        client.addInvestment(investment);

        return "Cuenta " + investment.getSourceAccount() + "  -  " + investment.getId() +" - "
        + (investment.getAmountInvesment() + (investment.getAmountInvesment() * 1.1)) + "de certificado de ganancias"; 

        
    }

    public boolean deposit(String username ,String accountNumber, double amount) throws Exception{
        //Verify if the client exists
        Cliente client = getClientByUser(username);
        
        if(client == null) throw new Exception("Cliente no encontrado.");
        
        //Search if the account exists en client
        Cuenta cuenta = client.findAccount(accountNumber);
        if(cuenta == null) throw new Exception("Cuenta no encontrada para el cliente.");

        cuenta.deposit(amount);
        return true;
    }

    public boolean withdraw(String username, String accountNumber, double amount) throws Exception{
         //Verify if the client exists
        Cliente client = getClientByUser(username);
        
        if(client == null) throw new Exception("Cliente no encontrado.");
        
        //Search if the account exists en client
        Cuenta cuenta = client.findAccount(accountNumber);
        if(cuenta == null) throw new Exception("Cuenta no encontrada para el cliente.");

        cuenta.withdraw(amount);
        return true;
    }
 

      public String listInversions(String username) {
        Cliente client = getClientByUser(username);
        if (client == null)
            return "Cliente no encontrado.";

        List<Inversion> inversiones = client.getInvestments();
        if (inversiones.isEmpty())
            return "El cliente no tiene inversiones registradas.";

        StringBuilder resultado = new StringBuilder();
        for (Inversion inversion : inversiones) {
            resultado.append("Monto: ").append(inversion.getAmountInvesment())
                    .append(" | Cuenta: ").append(inversion.getSourceAccount())
                    .append(" | ID: ").append(inversion.getId())
                    .append("\n");
        }
        return resultado.toString();
    }

    public String AccountList(String username) {
        Cliente client = getClientByUser(username);
        if (client == null)
            return "Cliente no encontrado.";

        List<Cuenta> cuentas = client.getAccounts();
        if (cuentas.isEmpty())
            return "El cliente no tiene cuentas.";

        StringBuilder resultado = new StringBuilder();
        for (Cuenta cuenta : cuentas) {
            resultado.append("Número de cuenta: ").append(cuenta.getAccountNumber())
                    .append(" | Fondos: ").append(cuenta.getBalance())
                    .append("\n");
        }
        return resultado.toString();
    }

}

