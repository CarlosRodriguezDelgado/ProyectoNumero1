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

    public String ListInvestments(){
     List<Inversion> lista1= this.cliente.getInvestments();
      String Inversiones="";
     for (Inversion inversion : lista1) {
        Inversiones+= "Monto: "+ inversion.getAmountInvesment()+ "Cuenta:"   + inversion.getSourceAccount()+ "ID:" + inversion.getId()+"\n";
     }
            return Inversiones;
        }


        public String ListAccounts(){
       List<Cuenta> lista2=  this.cliente.getAccounts();
        String cuentas= "";
         for (Cuenta cuenta : lista2) {
            cuentas+= "Numero de cuenta:" + cuenta.getAccountNumber()+"Fondos:"+ String.valueOf(cuenta.getBalance())+"\n" ;
         }
         return cuentas;

        }

        
          
    }


