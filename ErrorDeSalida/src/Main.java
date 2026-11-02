import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        // Ejecuta un ping con un parámetro incorrecto.
        ProcessBuilder pb = new ProcessBuilder(
                "ping", "-parametroIncorrecto", "localhost");

        try {
            Process proceso = pb.start();

            // Lee el mensaje de error.
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(proceso.getErrorStream()));

            String linea;

            // Muestra el error por pantalla.
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            proceso.waitFor();

        } catch (IOException e) {
            System.out.println("Error al ejecutar el proceso.");

        } catch (InterruptedException e) {
            System.out.println("Error al esperar al proceso.");
            Thread.currentThread().interrupt();
        }
    }
}
