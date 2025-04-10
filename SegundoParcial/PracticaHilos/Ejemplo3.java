/*
* TestThreadEjemplo3.java
*Tema Hilos
* Ejemplo de implementación con la interface Runnable
*/
public class Ejemplo3 implements Runnable {
    public static void main(String[] args) {
        Thread guruThread1 = new Thread("N1");
        Thread guruThread2 = new Thread("N2");
        guruThread1.start();
        guruThread2.start();
        System.out.println("Los nombres de los Thread-hilos son los siguientes: ");
        System.out.println(guruThread1.getName());
        System.out.println(guruThread2.getName());
    }

    public void run() {
    }
}