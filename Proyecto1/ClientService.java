import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientService{
    private List<Cliente> clientsList;
    private static final AtomicInteger counter = new AtomicInteger(1); //Para asignar un numero de ID a las inversiones

    public ClientService(){
        //Lista de clientes de prueba
        clientsList = new ArrayList<>();
        clientsList.add(new Cliente("u1", "ana", "1234" ));
        clientsList.add(new Cliente("u2", "julian", "key" ));
        clientsList.add(new Cliente("u3", "jace", "12345" ));
        clientsList.add(new Cliente("u4", "bryce", "key1" ));
        clientsList.add(new Cliente("u5", "ines", "abcd" ));

        //Cuentas de prueba
        Cliente cliente = getClientByUser("ana"); 
        cliente.addAccount(new Cuenta("6767", 120000));

        Cliente cliente1 = getClientByUser("julian");
        cliente1.addAccount(new Cuenta("1212", 230000));
    }

    
    public boolean authenticateUser(String userName, String password){
        Cliente client = getClientByUser(userName);

        if(client == null || client.isLoggedIn()) return false;
        
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
    
    public String createAcount(String username, String accountNumber){
        Cliente client = getClientByUser(username);

        if (client == null)
            throw new IllegalArgumentException("Cliente no existente");

        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Numero de cuenta inválido");
        }

        
        Cuenta cuenta = new Cuenta(accountNumber);
        client.addAccount(cuenta);

         return "Cuenta " + accountNumber + " creada exitosamente.";
    }

    public String createInvestment(String username, String accountNumber, double amount){
        Cliente client = getClientByUser(username);
        
        if(client == null) throw new IllegalAccessError("Cliente no existente");

        Cuenta clientAccount = client.findAccount(accountNumber);
        if(clientAccount == null) throw new IllegalStateException("No se pudo realizar la inversion");
 
        String idInvesment = "INVEST-" + counter.getAndIncrement();
        Inversion investment = new Inversion(idInvesment, amount, clientAccount); 
        client.addInvestment(investment);

        return "Cuenta # " + investment.getSourceAccount() + ", Inversion #" + investment.getId() +" monto invertido:  "
        + investment.getAmountInvesment() +" +  "+ (amount * 0.10)+ ": de certificado de ahorro"; 

        
    }

    public String deposit(String username ,String accountNumber, double amount) throws Exception{
        Cliente client = getClientByUser(username);
        
        if(client == null) throw new Exception("Cliente no encontrado.");
        
        Cuenta cuenta = client.findAccount(accountNumber);
        if(cuenta == null) throw new Exception("Cuenta no encontrada para el cliente.");

        cuenta.deposit(amount);
        return "¡Depósito realizado correctamente!, tu saldo ahora es de " + cuenta.getBalance();
    }

    public String withdraw(String username, String accountNumber, double amount) throws Exception{
        Cliente client = getClientByUser(username);
        
        if(client == null) throw new Exception("Cliente no encontrado.");
        
        Cuenta cuenta = client.findAccount(accountNumber);
        if(cuenta == null) throw new Exception("Cuenta no encontrada para el cliente.");

        cuenta.withdraw(amount);
        return "Retiro realizado correctamente, tu saldo ahora es de " + cuenta.getBalance();
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
            resultado.append("ID: ").append(inversion.getId())
                    .append(" | Cuenta: ").append(inversion.getSourceAccount())
                    .append(" | Monto: ").append(inversion.getAmountInvesment())
                    .append(" | Ahorro: ").append((inversion.getAmountInvesment() * 0.10))
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

