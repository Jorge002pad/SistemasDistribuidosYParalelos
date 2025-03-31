package Aleatorio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio {
    public static void main(String[] args) {
        // Especifica la ruta del archivo
        File archivo = new File("archivo.txt");

        try {
            // Crea un FileWriter para escribir en el archivo
            FileWriter escritor = new FileWriter(archivo);
            String valoresAlfanumericos = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";

           StringBuilder s = new StringBuilder(15);

           for(int x=0; x<10;x++){
                for ( int i=0; i<15; i++) {
                    // se genera un numero aleatorio para tomar el caracter
                     int ch = (int)(valoresAlfanumericos.length() * Math.random());
                   //agrega uno a uno un caracter a la cadena s
                   s.append(valoresAlfanumericos.charAt(ch));
                }
            }
            // Escribe algo de contenido en el archivo
            escritor.write(s.toString());
            // Cierra el escritor
            escritor.close();
            System.out.println("Archivo creado y contenido escrito correctamente.");

        } catch (IOException e) {
            // Manejo de excepciones en caso de error al crear el archivo
            System.out.println("Ocurrió un error al crear el archivo.");
            e.printStackTrace();
        }
    }
}


