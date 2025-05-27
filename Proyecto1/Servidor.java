
mport java.io.IOError;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    
   public static void main(String[] args) throws IOException {
   int puerto= SingletonPuerto.getInstanceofPuerto().getPuerto();
    try (ServerSocket servidor= new ServerSocket(puerto)) {
        System.out.println("esperando conexion en el puerto"+ puerto);
        Socket clienteSocket= servidor.accept();
        System.out.println("Conexion establecida con" + clienteSocket.getInetAddress());

        //manejador
        
        //thread
        
      
    } catch (IOException e) {
        e.printStackTrace();
    }


   }