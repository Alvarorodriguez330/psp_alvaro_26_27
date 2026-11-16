public class Main {
    public static void main(String[] args) {
        // Crea una instancia de Contador con un valor inicial de 100
        Contador contador = new Contador(100);
        System.out.println("[MAIN] >> Valor inicial: " + contador.valor());

        // Crea los objetos Sumador y Restador compartiendo el mismo Contador
        Sumador sumador = new Sumador("Sumador", contador);
        Restador restador = new Restador("Restador", contador);
        Thread hiloRestador = new Thread(restador, "Restador");

        // Inicia ambos hilos
        sumador.start();
        hiloRestador.start();

        // Espera a que ambos hilos finalicen su ejecución
        try {
            sumador.join();
            hiloRestador.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("[MAIN] >> Hilo principal interrumpido.");
        }

        // Con 1500 incrementos y 1500 decrementos alternos, el valor final siempre será 100
        System.out.println("[MAIN] >> Valor final: " + contador.valor());
    }
}