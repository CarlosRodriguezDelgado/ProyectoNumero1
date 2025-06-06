
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {
    
    public static void main(String[] args) throws IOException {
        int puerto = SingletonPuerto.getInstanceofPuerto().getPuerto();
        ClientService service = new ClientService();
        ClientFacade facade = new ClientFacade(service);

        // Pool de hilos, si hay mas de 4 hilos, el resto se queda en espera hasta que se libere un espacio
        ExecutorService threads = Executors.newFixedThreadPool(4);

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Esperando conexion en el puerto " + puerto);

            while (true) {
                Socket clienteSocket = servidor.accept();
                System.out.println("Conexion establecida con" + clienteSocket.getInetAddress());

                ClientHandler handler = new ClientHandler(clienteSocket, facade);
                threads.execute(handler);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}