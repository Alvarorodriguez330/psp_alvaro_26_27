public class Contador {
    private int cuenta;
    // Variable booleana para controlar la alternancia
    private boolean turnoSumador;

    public Contador(int cuentaInicial) {
        this.cuenta = cuentaInicial;
        this.turnoSumador = true; // Empieza el sumador
    }

    /**
     * Incrementa el contador cuando es el turno del sumador.
     * Si no es su turno, espera hasta ser notificado.
     */
    public synchronized void incrementar() {
        // Mientras NO sea el turno del sumador, el hilo espera
        while (!turnoSumador) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("[CONTADOR] >> Hilo sumador interrumpido en espera.");
                return;
            }
        }

        // Modifica el valor y muestra el estado actual de forma atómica
        this.cuenta++;
        System.out.println("[" + Thread.currentThread().getName() + "] >> Valor actual: " + this.cuenta);

        // Cambia el turno al restador y despierta a todos los hilos en espera
        this.turnoSumador = false;
        notifyAll();
    }

    /**
     * Resta el contador cuando es el turno del restador.
     * Si no es su turno, espera hasta ser notificado.
     */
    public synchronized void decrementar() {
        // Mientras sea el turno del sumador (NO el del restador), el hilo espera
        while (turnoSumador) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("[CONTADOR] >> Hilo restador interrumpido en espera.");
                return;
            }
        }

        //Modifica el valor y muestra el estado actual de forma atomica
        this.cuenta--;
        System.out.println("[" + Thread.currentThread().getName() + "] >> Valor actual: " + this.cuenta);

        // Cambia el turno al sumador y despierta a todos los hilos en espera
        this.turnoSumador = true;
        notifyAll();
    }

    /**
     * Devuelve el valor actual de forma sincronizada.
     */
    public synchronized int valor() {
        return this.cuenta;
    }
}