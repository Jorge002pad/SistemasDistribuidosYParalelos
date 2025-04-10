
/*
* TestThreadEjemplo1.java
*Tema Hilos
* Ejemplo de implementación con la clase Thread
* Los métodos se encuentran en el paquete java.lang
* por eso la siguiente sentencia es innecesaria: import java.lang.Thread;
*/
public class Ejemplo1 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()); // Invocación a métodos predefinidos
        // currentThread() obtiene el hilo actual mientras que getName() devuelve el
        // nombre del hilo
        for (int i = 0; i < 10; i++) {
            new Thread("" + i) {
                public void run() {
                    System.out.println("Thread (Hilo): " + getName() + " corriendo");
                }
            }.start(); // Método para iniciar el cuerpo de la thread definido por el método run()
        }
    }
}