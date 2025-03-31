import java.util.*;
import java.util.concurrent.ForkJoinPool;
;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List <Integer> lista = new ArrayList<>();
        for (int i=0;i<100;i++)
            lista.add(i);
        ForkJoinPool fj = new ForkJoinPool(5);
        long tiempoinicial = System.currentTimeMillis();
        System.out.println();

        Thread t1 = new Thread(()->fj.submit(
            ()->lista.parallelStream().forEach(a ->{
                try {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread());
                } catch (Exception e) {
                    // TODO: handle exception
                }
            })).invoke());

        t1.start();
        t1.join();

        long tiempofinal = System.currentTimeMillis();
        System.out.println("Tiempo: " +(tiempoinicial+tiempofinal));
    }
}
