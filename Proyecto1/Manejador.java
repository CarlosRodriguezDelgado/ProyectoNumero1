import java.util.*;
import java.io.*;
import java.net.Socket;

public class Manejador implements Runnable {
  private Socket clienteSocket=null;

  public Manejador(Socket s) {
    this.clienteSocket=s;
  }

    @Override
    public void run() {
        try (

            // entradas y salidas
           PrintWriter salida= new PrintWriter(clienteSocket.getOutputStream(), true);
           BufferedReader entrada= new BufferedReader( new InputStreamReader(clienteSocket.getInputStream()));
        ) {
            
            //primera salida  para avisar


            //toda la logica

        }catch(Exception e){
          e.printStackTrace();
        }
    }

}
