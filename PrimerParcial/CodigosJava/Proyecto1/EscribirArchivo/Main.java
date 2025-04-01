package Proyecto1.EscribirArchivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

import Dispositivo.Dispositivo;



public class Main {
    /*La funcion se encarga de generar cadenas alfanumericas aleatorias
     de tamaño "n" tomando elementos alfanumericos de un arreglo
     size: tamaño de la cadena
     return: la palabra generada */
    public static String generarPalabras(int size){
        String valoresAlfanumericos = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
        StringBuilder palabra = new StringBuilder(size);
        for ( int i=0; i<size; i++) {
            // se genera un numero aleatorio para tomar el caracter
             int indice = (int)(valoresAlfanumericos.length() * Math.random());
            //agrega uno a uno un caracter a la cadena palabra
            palabra.append(valoresAlfanumericos.charAt(indice));
        }
        return palabra.toString();
    }

    /*La funcion se encarga de escribir cadenas obtenidas de la funcion 
    generarPalabras() dentro de un archivo dado en los parametros
    archivo: archivo en el que se quiere escribir
    cantidadPalabras: cantidad de palabras que se escribiran en el archivo*/
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

    public static void buscarVocales(File archivo){
        try (Stream<String> stream = Files.lines(Paths.get(archivo))) {
            /*  Una vez que se accede a cada linea, se realizan dos operaciones de mapeo, se convertimos cada linea en un objeto
        y posteriormente  en un número.
        */
             stream.map(linea -> linea.split(","))
                  .onClose(() -> System.out.println("\nFinalizando"));   //Double::sum es una función que se utiliza para sumar dos números de punto flotante.
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 // Submenú para la opción 1
    public static void menuGenerarCadena(Scanner scanner, File archivo) {
        int opcionSubmenu;
        do {
            System.out.println("\n=== SUBMENÚ: Generar Cadena ===");
            System.out.println("1. Generar 1000 datos");
            System.out.println("2. Generar 10000 datos");
            System.out.println("3. Generar 100000 datos");
            System.out.println("4. Regresar");
            System.out.print("Seleccione una opción: ");

            opcionSubmenu = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcionSubmenu) {
                case 1:
                    escribirCadenasEnArchivo(archivo, 1000);
                    break;
                case 2:
                    escribirCadenasEnArchivo(archivo, 10000);
                    break;
                case 3:
                    escribirCadenasEnArchivo(archivo, 100000);
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcionSubmenu != 4);
    }

    public static void menuBuscar(Scanner scanner, File archivo) {
        int opcionSubmenu;
        do {
            System.out.println("\n=== SUBMENÚ: Generar Cadena ===");
            System.out.println("1. Palabras que contengas 3 veces una vocal");
            System.out.println("2. Palabras que inicien con F y terminen con 9");
            System.out.println("3. Regresar");
            System.out.print("Seleccione una opción: ");

            opcionSubmenu = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcionSubmenu) {
                case 1:
                    System.out.println("Buscar vocales");
                break;
                case 2:
                    System.out.println("Buscar palabras con F");
                break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcionSubmenu != 3);
    }

    public static void menuOrdenamiento(Scanner scanner, File archivo) {
        int opcionSubmenu;
        do {
            System.out.println("\n=== SUBMENÚ: Generar Cadena ===");
            System.out.println("1. Metodo Merge Ascendente");
            System.out.println("2. Metodo Merge Descendente");
            System.out.println("3. Metodo QuickShort Ascendente");
            System.out.println("4. Metodo QuickShort Descendente");
            System.out.println("5. Regresar");
            System.out.print("Seleccione una opción: ");

            opcionSubmenu = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcionSubmenu) {
                case 1:
                    System.out.println("Metodo Seleccionado: Merge Ascendente");
                    break;
                case 2:
                    System.out.println("Metodo Seleccionado: Merge Descendente");
                    break;
                case 3:
                    System.out.println("Metodo Seleccionado: QuickShort Ascendente");
                    break;
                case 4:
                    System.out.println("Metodo Seleccionado: QuickShort Descendente");
                    break;
                case 5:
                    System.out.println("Regresando al menu principal");
                break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcionSubmenu != 5);
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
                    System.out.println("2. Realizar busqueda");
                    System.out.println("3. Realizar Ordenamiento");
                    System.out.println("4. Salir");
                    System.out.print("Seleccione una opción: ");
        
                    // Leer la opción del usuario
                    opcion = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea
        
                    switch (opcion) {
                        case 1:
                            menuGenerarCadena(scanner, archivo);
                            break;
                        case 2:
                            System.out.println("Buscando algo ");
                            break;
                        case 3:
                            System.out.println("Ordenando");
                            break;
                        case 4:
                            System.out.println("Saliendo del programa...");
                        break;
                        default:
                            System.out.println("Opción no válida. Intente de nuevo.");
                    }
                } while (opcion != 4);
            
                scanner.close(); 
    }
}


