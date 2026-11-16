public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        Thread hiloCliente = new Thread(cliente, "Cliente");

        // Estado antes de iniciarlo
        System.out.println("MAIN >> Estado del hilo cliente antes de iniciar: " + hiloCliente.getState());

        hiloCliente.start();

        try {
            hiloCliente.join();
        } catch (InterruptedException e) {
            //Gestionar InterruptedException y restaurar la señal de interrupción
            Thread.currentThread().interrupt();
            System.err.println("MAIN]>> El hilo principal fue interrumpido.");
        }

        // Estado después de que termine
        System.out.println("MAIN >> Estado del hilo cliente después de terminar: " + hiloCliente.getState());
    }
}
