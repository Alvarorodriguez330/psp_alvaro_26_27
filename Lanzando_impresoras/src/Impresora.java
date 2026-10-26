public class Impresora implements Runnable {

    // Definición de variables
    private String nombre;
    private int totalPaginas;

    // Constructor que recibe el nombre y el número de páginas
    public Impresora(String nombre, int totalPaginas) {
        this.nombre = nombre;
        this.totalPaginas = totalPaginas;
    }

    @Override
    public void run() {
        // Bucle que recorre las páginas desde 1 hasta el total indicado
        for (int i = 1; i <= totalPaginas; i++) {
            // Muestra el nombre de la impresora y la página actual sobre el total
            System.out.println("Impresora " + nombre + ": página " + i + " de " + totalPaginas);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Impresora " + nombre + " ha TERMINADO de imprimir.");
    }
}