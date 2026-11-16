public class Cliente implements Runnable {

    @Override
    public void run() {
        //Todos los hilos de vendedores pertenecerán a un mismo ThreadGroup
        ThreadGroup grupoVendedores = new ThreadGroup("Grupo Vendedores");

        Vendedor[] vendedores = {
                new Vendedor("Vendedor 1"),
                new Vendedor("Vendedor 2"),
                new Vendedor("Vendedor 3")
        };

        Thread[] hilosVendedores = new Thread[vendedores.length];

        //Iniciar todos los vendedores antes de esperar sus respuestas (en paralelo)
        for (int i = 0; i < vendedores.length; i++) {
            hilosVendedores[i] = new Thread(grupoVendedores, vendedores[i], vendedores[i].getNombre());
            hilosVendedores[i].start();
        }

        //Utilizar join() para esperar a que terminen todos los vendedores
        for (Thread hilo : hilosVendedores) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                //Gestionar InterruptedException y restaurar la señal de interrupción
                Thread.currentThread().interrupt();
                System.err.println("[CLIENTE] >> La espera de ofertas fue interrumpida.");
                return;
            }
        }

        //Mostrar nombre y plazo de cada vendedor, y seleccionar la mejor oferta
        System.out.println("--- Ofertas de entrega recibidas ---");
        Vendedor mejorVendedor = vendedores[0];

        for (Vendedor vendedor : vendedores) {
            System.out.println(vendedor.getNombre() + ": " + vendedor.getPlazoEntrega() + " días");

            //si hay empate conserva el primero que apareció en la lista
            if (vendedor.getPlazoEntrega() < mejorVendedor.getPlazoEntrega()) {
                mejorVendedor = vendedor;
            }
        }

        System.out.println("\nVendedor elegido: " + mejorVendedor.getNombre()
                + " (" + mejorVendedor.getPlazoEntrega() + " días)");
    }
}
