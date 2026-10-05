import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        String sistemaOperativo = System.getProperty("os.name");

        String command;
        String directorio;

        // Windows
        if (sistemaOperativo.toLowerCase().contains("win")) {
            command = "cmd /c dir";
            directorio = "c:/temp";
        } else {
            // Linux
            command = "sh -c ls";
            directorio = "/tmp";
        }

        try {
            ProcessBuilder pBuilder = new ProcessBuilder(command.split("\\s"));
            pBuilder.directory(new File(directorio));

            //creo un nuevo proceso
            Process process = pBuilder.start();

            //stream del proceso
            BufferedReader reader= new BufferedReader(new InputStreamReader(process.getInputStream()));

            reader.lines().forEach(System.out::println);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}