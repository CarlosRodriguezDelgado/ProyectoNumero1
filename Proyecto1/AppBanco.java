import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class AppBanco {
  public static void main(String[] args) {

    try (
        Socket socketApp = new Socket("localhost", SingletonPuerto.getInstanceofPuerto().getPuerto());
        BufferedReader in = new BufferedReader(new InputStreamReader(socketApp.getInputStream()));
        PrintWriter out = new PrintWriter(socketApp.getOutputStream(), true);
        Scanner keyboard = new Scanner(System.in);) {
      IOManager.escribir("Conectandose al sistema...");

      String serverMessage = "";

      while ((serverMessage = in.readLine()) != null) {
        IOManager.escribir(serverMessage);

        if (input(serverMessage)) {
          IOManager.escribir("->");
          String answer = keyboard.nextLine();
          out.println(answer);
        }

        if (serverMessage.toLowerCase().equals("sesion cerrada"))
          break;
      }

    } catch (IOException e) {
      IOManager.escribir("Error: " + e);
    }
  }

  public static boolean input(String mss) {
    String lower = mss.toLowerCase();
    return lower.contains("ingrese") || lower.contains("digite") || lower.contains("escriba") || lower.contains("seleccione");
  }

}
