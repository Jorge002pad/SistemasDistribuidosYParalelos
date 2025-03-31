package Versiones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Algo{
    int algo3;

    Algo(int dato3){
        this.algo3 = dato3;
    }
    public int getAlgo3(){
        return algo3;
    }
        
};
public class programa1Iterativo {
   
    public static void main(String[] args) {
        Random rand = new Random();
        
        List<Integer> valores = new ArrayList<>();
        for(int x=0; x<10; x++){
            int numeroAleatorio = rand.nextInt(500); // Número entero aleatorio
            valores.add(numeroAleatorio);
        } 
        
        valores.stream().forEach(System.out::println);
        // List <Algo> objeto1 = Arrays.asList(new Algo(2020),
        //                                      new Algo(2000),
        //                                      new Algo(1999),
        //                                      new Algo(2021));
                                             
        
    }
}
