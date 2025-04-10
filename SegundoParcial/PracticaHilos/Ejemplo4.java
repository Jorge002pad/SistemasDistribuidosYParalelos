/*
* Ejemplo4.java
*Tema Hilos
* Ejemplo de implementación con la interface Runnable
*/
public class Ejemplo4 implements Runnable {
    public void run() {
        for (int i = 0; i < 5; i++)
            System.out.println(i + " " + Thread.currentThread().getName());
        System.out.println("Termina thread-hilo " + Thread.currentThread().getName());

    }

    public static void main(String[] args) {
        Ejemplo4 ejemplo = new Ejemplo4();
        Thread thread = new Thread(ejemplo, "Pedro");
        thread.start(); // se llama al método start de la clase thread. // Este método iniciará el nuevo
                        // hilo y llamará al método run() de la clase Ejemplo4
        Ejemplo4 ejemplo1 = new Ejemplo4();
        Thread thread1 = new Thread(ejemplo1, "Juan");
        thread1.start();
        System.out.println("Termina el thread-hilo main");
    }
}