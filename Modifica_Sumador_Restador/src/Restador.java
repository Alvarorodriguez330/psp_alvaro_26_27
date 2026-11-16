import java.util.concurrent.ThreadLocalRandom;

public class Restador implements Runnable {
    private final Contador contador;
    private final String nombre;
    private static final int ITERACIONES = 1500;

    public Restador(String nombre, Contador contador) {
        this.nombre = nombre;
        this.contador = contador;
    }

    @Override
    public void run() {
        for (int i = 0; i < ITERACIONES; i++) {
            contador.decrementar();
            try {
                Thread.sleep(ThreadLocalRandom.current().nextLong(10, 150));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("[" + nombre + "] >> Hilo interrumpido.");
                break;
            }
        }
    }
}