import java.util.Scanner;

public class IOManager {
    private Scanner scanner;

    public IOManager() {
        scanner = new Scanner(System.in);
    }
     public static void escribir(String mensaje){
        System.out.println(mensaje);
    }

    public String leerString(String prompt) {
        escribir(prompt);
        return scanner.nextLine();
    }

    public int leerInt(String mensaje) {
        escribir(mensaje);
        while (!scanner.hasNextInt()) {
            escribir("Por favor, ingresa un número entero válido.");
            scanner.next(); 
            escribir(mensaje);
        }
        return scanner.nextInt();
    }

    public double leerDouble(String mensaje) {
        escribir(mensaje);
        while (!scanner.hasNextDouble()) {
            escribir("Por favor, ingresa un número decimal válido.");
            scanner.next();
            escribir(mensaje);
        }
        return scanner.nextDouble();
    }


}
