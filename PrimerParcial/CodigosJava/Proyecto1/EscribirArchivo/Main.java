package Proyecto1.EscribirArchivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

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

    /*La funcion busca palabras que contengan 3 veces una vocal sin importar que sea mayuscula 
     * o minuscula.
     * archivo: Archivo donde tomaremos las palabras para la busqueda*/
    public static void buscarVocales(String archivo){
        // Leer archivo línea por línea usando Streams
        try (Stream<String> stream = Files.lines(Paths.get(archivo))) {
            stream.forEach(System.out::println); // Imprime cada línea en consola
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void buscarIniciaYTermina(String archivo){
        // Leer archivo línea por línea usando Streams
        try (Stream<String> stream = Files.lines(Paths.get(archivo))) {
            stream.forEach(System.out::println); // Imprime cada línea en consola
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void ordenarMergeAscendente(String archivo){
        // Leer archivo línea por línea usando Streams
        try (Stream<String> stream = Files.lines(Paths.get(archivo))) {
            stream.forEach(System.out::println); // Imprime cada línea en consola
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void ordenarMergeDescendente(String archivo){
        // Leer archivo línea por línea usando Streams
        try (Stream<String> stream = Files.lines(Paths.get(archivo))) {
            stream.forEach(System.out::println); // Imprime cada línea en consola
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void ordenarQuickShortAscendente(String archivo){
        // Leer archivo línea por línea usando Streams
        try (Stream<String> stream = Files.lines(Paths.get(archivo))) {
            stream.forEach(System.out::println); // Imprime cada línea en consola
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void ordenarQuickShortDescendente(String archivo){
        // Leer archivo línea por línea usando Streams
        try (Stream<String> stream = Files.lines(Paths.get(archivo))) {
            stream.forEach(System.out::println); // Imprime cada línea en consola
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
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
 // Submenú para la opción 2
    public static void menuBuscar(Scanner scanner, String archivo) {
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
                    buscarVocales(archivo);
                break;
                case 2:
                    System.out.println("Buscar palabras con F");
                    buscarIniciaYTermina(archivo);
                break;
                case 3:
                    System.out.println("Regresando al menu principal");
                break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcionSubmenu != 3);
    }
 // Submenú para la opción 3
    public static void menuOrdenamiento(Scanner scanner, String archivo) {
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
                    ordenarMergeAscendente(archivo);
                    break;
                case 2:
                    System.out.println("Metodo Seleccionado: Merge Descendente");
                    ordenarMergeDescendente(archivo);
                    break;
                case 3:
                    System.out.println("Metodo Seleccionado: QuickShort Ascendente");
                    ordenarQuickShortAscendente(archivo);
                    break;
                case 4:
                    System.out.println("Metodo Seleccionado: QuickShort Descendente");
                    ordenarQuickShortDescendente(archivo);
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
                    System.out.print("\nSeleccione una opción: ");
        
                    // Leer la opción del usuario
                    opcion = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea
        
                    switch (opcion) {
                        case 1:
                            menuGenerarCadena(scanner, archivo);
                            break;
                        case 2:
                            menuBuscar(scanner, archivo.toString());
                            break;
                        case 3:
                            menuOrdenamiento(scanner, archivo.toString());
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


