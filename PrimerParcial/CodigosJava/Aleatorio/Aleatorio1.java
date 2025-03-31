package Aleatorio;

public class Aleatorio1 {
    //Genera una cadena de 10 caracteres
    public static void main(String[] args) {
         String valoresAlfanumericos = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";

           StringBuilder s = new StringBuilder(10);

         for ( int i=0; i<10; i++) {
             // se genera un numero aleatorio para tomar el caracter
              int ch = (int)(valoresAlfanumericos.length() * Math.random());
            //agrega uno a uno un caracter a la cadena s
            s.append(valoresAlfanumericos.charAt(ch));

         }

         System.out.println(s.toString()); // cadena de 10 caracteres
    }
}
