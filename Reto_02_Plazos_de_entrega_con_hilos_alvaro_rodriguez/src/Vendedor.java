import java.util.concurrent.ThreadLocalRandom;

public class Vendedor implements Runnable {
    private final String nombre;
    private int plazoEntrega;

    public Vendedor(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            //Simula el tiempo necesario para consultar su plazo
            Thread.sleep(500);
            //Días de entrega aleatorios entre 1 y 7, ambos incluidos
            this.plazoEntrega = ThreadLocalRandom.current().nextInt(7) + 1;
        } catch (InterruptedException e) {
            //Gestionar InterruptedException y restaurar la señal de interrupción
            Thread.currentThread().interrupt();
            System.err.println(nombre + " >> Consulta interrumpida.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getPlazoEntrega() {
        return plazoEntrega;
    }
}