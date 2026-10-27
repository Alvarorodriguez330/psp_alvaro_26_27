import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        // Comprueba el s.o para ejecutar el ping
        String sistemaOperativo = System.getProperty("os.name");

        ProcessBuilder processBuilder;

        if (sistemaOperativo.toLowerCase().contains("win")) {
            processBuilder = new ProcessBuilder("ping", "localhost");
        } else {
            processBuilder = new ProcessBuilder("ping", "-c", "4", "localhost");
        }

        try {
            Process process = processBuilder.start();

            // Comprueba si el proceso está vivo al principio
            System.out.println("¿El proceso está vivo? " + process.isAlive());

            // información del proceso
            System.out.println("PID: " + process.pid());
            System.out.println("Comando: " + process.info().command().orElse("No disponible"));
            System.out.println("Argumentos: " + process.info().arguments().map(argumentos -> String.join(" ", argumentos)).orElse("No disponibles"));
            System.out.println("Usuario: " + process.info().user().orElse("No disponible"));

            // Lee y muestra el resultado del comando ping
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            System.out.println("Resultado del ping");

            reader.lines().forEach(System.out::println);

            // Termina el proceso y guarda su resultado final
            int resultado = process.waitFor();

            System.out.println("Resultado final");
            System.out.println("Código de salida: " + resultado);

            // Comprueba si el proceso sigue vivo al finalizar
            System.out.println("¿El proceso está vivo al finalizar? "
                    + process.isAlive());

        } catch (IOException e) {
            System.out.println("Error al ejecutar el proceso: " + e.getMessage());

        } catch (InterruptedException e) {
            System.out.println("Se ha interrumpido la espera del proceso: "
                    + e.getMessage());

            Thread.currentThread().interrupt();
        }
    }
}
