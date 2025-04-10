/*
* TestThreadEjemplo5.java
*Tema Hilos
* Ejemplo de implementación con la interface Runnable
*/
class ClaseHilo implements Runnable {
    Thread hilo;
    private String hilonomb;

    ClaseHilo(String nomb) {
        hilonomb = nomb;
    }

    public void run() {
        System.out.println("Thread-hilo corriendo " + hilonomb);
        for (int i = 0; i < 4; i++) {
            System.out.println(i);
            System.out.println(hilonomb);
            try {
                Thread.sleep(1000); // pone a dormir una thread por un tiempo mínimo especificado en milisegundos
            } catch (InterruptedException e) {
                System.out.println("Thread ha sido interrumpido");
            }
        }
    }

    public void start() {
        System.out.println("Thread-hilo iniciado");
        if (hilo == null) {
            hilo = new Thread(this, hilonomb);
            hilo.start();
        }
    }
}

public class Ejemplo5 {
    public static void main(String[] args) {
        ClaseHilo threadguru1 = new ClaseHilo("Hilo1");
        threadguru1.start();
        ClaseHilo threadguru2 = new ClaseHilo("Hilo2");
        threadguru2.start();
    }
}