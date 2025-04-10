/*
*Tema Hilos
* Ejemplo de implementación con la clase Thread
* Puede observarse en cada ejecución que la salida es diferente, * eso depende de la cola de atención del procesador de ahi que atienda en diferente orden
* a
*/

public class Ejemplo2 extends Thread {
    public Ejemplo2(String str) {
        super(str);
    }

    public void run() {
        for (int i = 0; i < 10; i++)
            System.out.println(i + " " + getName());
        System.out.println("Termina thread-hilo " + getName());

    }

    public static void main(String[] args) {
        new Ejemplo2("Pedro").start();
        new Ejemplo2("Juan").start();
        System.out.println("Termina thread-main");
    }
}