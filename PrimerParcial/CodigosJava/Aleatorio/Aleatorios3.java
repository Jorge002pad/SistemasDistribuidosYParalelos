package Aleatorio;

import java.util.Random;
public class Aleatorios3{
        public static void main(String[] args) {
            Random generador = new Random();
            for (int i = 0; i< 100; i++){
              int numRandom = generador.nextInt(1000);
              System.out.println("Valor random: " + numRandom);
            }
        }
}
