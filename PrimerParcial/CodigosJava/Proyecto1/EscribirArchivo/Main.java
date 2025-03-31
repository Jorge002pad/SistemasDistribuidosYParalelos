package Proyecto1.EscribirArchivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class Main {

    public static String generarPalabras(int size){
        String valoresAlfanumericos = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
        StringBuilder s = new StringBuilder(size);
        for ( int i=0; i<size; i++) {
            // se genera un numero aleatorio para tomar el caracter
             int ch = (int)(valoresAlfanumericos.length() * Math.random());
            //agrega uno a uno un caracter a la cadena s
            s.append(valoresAlfanumericos.charAt(ch));
        }
        return s.toString();
    }

    public static void escribirCadenasEnArchivo(File archivo, int cantidadPalabras) {
        try {
            // Crea un FileWriter para escribir en el archivo
            FileWriter escritor = new FileWriter(archivo);
            for (int i=0; i<cantidadPalabras; i++){
                // Escribe algo de contenido en el archivo
                escritor.write(generarPalabras(10) + "\n");
            }
            // Cierra el escritor
            escritor.close();
            System.out.println("\nSi jalo");

        } catch (IOException e) {
            // Manejo de excepciones en caso de error al crear el archivo
            System.out.println("\nNo jalo");
            e.printStackTrace();
        }
    }
 // Submenú para la opción 1
    public static void menuGenerarCadena(Scanner scanner, File archivo, int cantidadPalabras) {
        int opcionSubmenu;
        do {
            System.out.println("\n=== SUBMENÚ: Generar Cadena ===");
            System.out.println("1. Mostrar cadena en consola");
            System.out.println("2. Guardar cadena en un archivo");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcionSubmenu = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcionSubmenu) {
                case 1:
                    String cadena = generarPalabras(10);
                    System.out.println("Cadena generada: " + cadena);
                    break;
                case 2:
                    System.out.print("Ingrese el tamaño de la cadena: ");
                    int sizeArchivo = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea
                    String cadenaArchivo = generarPalabras(sizeArchivo);
                    escribirCadenasEnArchivo(archivo, 15, cadenaArchivo);
                    System.out.println("Cadena guardada en 'cadena_unica.txt'");
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcionSubmenu != 3);
    }

    public static void main(String[] args) {
        // Especifica la ruta del archivo
        File archivo = new File("palabras.txt");
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
                    // Mostrar el menú
                    System.out.println("\n=== MENÚ PRINCIPAL ===");
                    System.out.println("1. Generar Cadenas Aleatorias");
                    System.out.println("2. Guardar 15 cadenas en un archivo");
                    System.out.println("3. Salir");
                    System.out.print("Seleccione una opción: ");
        
                    // Leer la opción del usuario
                    opcion = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea
        
                    switch (opcion) {
                        case 1:
                            System.out.print("Ingrese el tamaño de la cadena: ");
                            int size = scanner.nextInt();
                            scanner.nextLine(); // Consumir la nueva línea
                            String cadena = generarPalabras(size);
                            System.out.println("Cadena generada: " + cadena);
                            break;
                        case 2:
                            escribirCadenasEnArchivo(archivo,15, 15);
                            System.out.println("Se han guardado 15 cadenas en " + archivo.toString());
                            break;
                        case 3:
                            System.out.println("Saliendo del programa...");
                            break;
                        default:
                            System.out.println("Opción no válida. Intente de nuevo.");
                    }
                } while (opcion != 3);
            
                scanner.close(); 
    }
}


