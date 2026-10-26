public class Main {

    public static void main(String[] args) {

        // Crea tres Thread asociados a cada Impresora con sus páginas correspondientes
        Thread impresoraA = new Thread(new Impresora("A", 3));
        Thread impresoraB = new Thread(new Impresora("B", 5));
        Thread impresoraC = new Thread(new Impresora("C", 4));

        // Inicia los tres hilos con start()
        impresoraA.start();
        impresoraB.start();
        impresoraC.start();

    }
}