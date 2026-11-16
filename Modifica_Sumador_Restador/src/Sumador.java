import java.util.concurrent.ThreadLocalRandom;

public class Sumador extends Thread {
    private final Contador contador;
    private static final int ITERACIONES = 1500;

    public Sumador(String nombre, Contador contador) {
        super(nombre);
        this.contador = contador;
    }

    @Override
    public void run() {
        for (int i = 0; i < ITERACIONES; i++) {
            contador.incrementar();
            try {
                Thread.sleep(ThreadLocalRandom.current().nextLong(10, 150));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("[" + getName() + "] >> Hilo interrumpido.");
                break;
            }
        }
    }
}