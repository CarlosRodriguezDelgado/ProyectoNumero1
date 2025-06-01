public class ClientFacade{
//Usar en ClientHandler y la parte de app dependiendo de la funcionalidad
private ClientService service;
private String numeroCuenta;
private double saldo;

    
public ClientFacade(){
    service = new ClientService();
    
}


public String createAnInvestmentCertificate(String clientId, String accountNumber, double amount){
    return service.createInvestment(clientId, accountNumber, amount);
}

public double depositar(double monto) throws Exception {
    if (monto <= 0) {
        throw new Exception("El monto a depositar debe ser positivo.");
    }
    this.saldo += monto; 
    return this.saldo;


} public double retirar(double monto) throws Exception {
    if (monto <= 0) {
        throw new Exception("El monto a retirar debe ser positivo.");
    }
    if (this.saldo < monto) {
        throw new Exception("Saldo insuficiente. Saldo actual: " + saldo + ". Monto solicitado: " +  monto + ".");
    }
    this.saldo -= monto; 
    return this.saldo;
}
}