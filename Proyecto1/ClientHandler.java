
import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {

    private Socket socketCliente;
    private ClientFacade facade;
    private String actualUser; 

    public ClientHandler(Socket s, ClientFacade facade) {
        socketCliente = s;
        this.facade = facade;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream())); PrintWriter out = new PrintWriter(socketCliente.getOutputStream(), true);) {
            // 1- Autenticación del usuario
            out.println("Bienvenido/a a la aplicación bancaria móvil");
            authenticateUser(in, out);
          
            //2- Cuando se autentique correctamente procede a aparecer el menú
            boolean isRunning = true;

            while (isRunning) {
                 showMenu(out);
                 String option = in.readLine();

                 switch (option) {
                      case "1":
                           addAccount(in, out);
                           break;
                      case "2":
                           makeDeposit(in, out);
                           break;
                      case "3":
                           makeWithdrawal(in, out);
                           break;
                      case "4":
                           createInvestment(in, out);
                           break;
                      case "5":
                           out.println(facade.listarCuentas(actualUser));
                           out.println(facade.listarInversiones(actualUser));
                           break;
                      case "6":
                           logOut(out);
                           isRunning = false;
                           break;
                      default:
                         throw new AssertionError();
                 }
            }

       } catch (IOException e) {
            IOManager.escribir("Error: " + e);
       } catch (Exception ex) {
            IOManager.escribir("Error: " + ex);
       }
  }

    private void authenticateUser(BufferedReader in, PrintWriter out) throws IOException {
        boolean inUser = false;
          //El inicio de sesión se hara correctamente hasta que se ingresen credenciales validas
        while (!inUser) {
            out.println("Ingrese su usuario");
            String user = in.readLine();
            out.println("Ingrese su contraseña");
            String pass = in.readLine();

            if (facade.logIn(user, pass)) {
                actualUser = user;
                inUser = true;
                out.println("¡Has iniciado sesión correctamente!");
            } else {
                out.println("Datos incorrectos, o ya has iniciado sesión anteriormente");
            }
        }
    }

    private void showMenu(PrintWriter out) {
        out.println("\n--- MENÚ DE LA APLICACIÖN---");
        out.println("1. Crear cuenta bancaria");
        out.println("2. Depositar");
        out.println("3. Retirar");
        out.println("4. Crear inversión");
        out.println("5. Listar cuentas e inversiones");
        out.println("6. Cerrar sesión");
        out.println("Seleccione una opción:");

    }

    private void makeDeposit(BufferedReader in, PrintWriter out){

         try {
              out.println("Digite el número de cuenta");
              String accNumber = in.readLine();
              out.println("Ingrese el monto a depositar: ");
              double amount = Double.parseDouble(in.readLine());
              out.println(facade.deposit(this.actualUser, accNumber, amount));
         } catch (Exception e) {
              out.println("Error: " + e);
         }
    }

    private void makeWithdrawal(BufferedReader in, PrintWriter out) {
    
         try {
              out.println("Ingrese el número de cuenta donde desea retirar");
              String acc = in.readLine();
              out.println("Ingrese el monto a retirar");
              double amount = Double.parseDouble(in.readLine());
              out.println(facade.withdraw(this.actualUser, acc, amount));
         } catch (Exception e) {
              out.println("Error: " + e);
         }
    }

    private void logOut(PrintWriter out) {
        facade.logOut(actualUser);
        out.println("sesion cerrada");
    }

    private void addAccount(BufferedReader in, PrintWriter out) {
         try {
              out.println("Ingrese el número de cuenta que va a crear");
              String accNumber = in.readLine();

              out.println(facade.createAcount(this.actualUser, accNumber));
         } catch (Exception e) {
              out.println("Error: " + e);
         }

    }

    private void createInvestment(BufferedReader in, PrintWriter out) {
         try {
              out.println("Ingrese el numero de cuenta de la cual invertirá");
              String accNum = in.readLine();
              out.println("Ingrese el monto a invertir");
              double amount = Double.parseDouble(in.readLine());
              out.println(facade.createAnInvestmentCertificate(this.actualUser, accNum, amount));
         } catch (Exception e) {
              out.println("Error: " + e);
         }
    }

}
