package Aleatorio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CrearArchivo {
    public static void main(String[] args) {
        // Especifica la ruta del archivo
        File archivo = new File("archivo.txt");

        try {
            // Crea un FileWriter para escribir en el archivo
            FileWriter escritor = new FileWriter(archivo);

            // Escribe algo de contenido en el archivo
            escritor.write("¡Hola, este es un archivo de texto!");

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

