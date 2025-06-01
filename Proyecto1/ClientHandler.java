import java.io.*;
import java.util.*;
import java.net.*;

public class ClientHandler implements Runnable {
     private Socket socketCliente;
     private ClientFacade facade;
     
     public ClientHandler(Socket s, ClientFacade facade) {
          socketCliente = s;
          this.facade = facade;
     }

    @Override
    public void run() {
       try(BufferedReader in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()))
           PrintWriter out = new PrintWriter(socketCliente.getOutputStream(), true);
          ){
               out.println("Bienvenido/a a la aplicación bancaria móvil");
               
       }catch(IOException e){
            IOManager.escribir("Error: " + e);
       }catch(Exception ex){
            IOManager.escribir("Error: " + ex);
       }
    }
    
}
