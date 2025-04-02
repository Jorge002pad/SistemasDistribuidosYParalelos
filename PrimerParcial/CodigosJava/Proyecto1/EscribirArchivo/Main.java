import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Main {
    public class Ordenamientos {

    public static void ordenarMergeSort(String[] array, boolean ascendente) {
        ForkJoinPool pool = new ForkJoinPool();
        MergeSortTask task = new MergeSortTask(array, 0, array.length - 1, ascendente);
        pool.invoke(task);
    }

    public static void ordenarQuickSort(String[] array, boolean ascendente) {
        ForkJoinPool pool = new ForkJoinPool();
        QuickSortTask task = new QuickSortTask(array, 0, array.length - 1, ascendente);
        pool.invoke(task);
    }

    // MergeSort Paralelo
    static class MergeSortTask extends RecursiveAction {
        private String[] array;
        private int left, right;
        private boolean ascendente;

        MergeSortTask(String[] array, int left, int right, boolean ascendente) {
            this.array = array;
            this.left = left;
            this.right = right;
            this.ascendente = ascendente;
        }

        @Override
        protected void compute() {
            if (left < right) {
                int mid = (left + right) / 2;
                MergeSortTask leftTask = new MergeSortTask(array, left, mid, ascendente);
                MergeSortTask rightTask = new MergeSortTask(array, mid + 1, right, ascendente);
                invokeAll(leftTask, rightTask);
                merge(array, left, mid, right, ascendente);
            }
        }

        private void merge(String[] array, int left, int mid, int right, boolean ascendente) {
            int n1 = mid - left + 1;
            int n2 = right - mid;
            String[] L = new String[n1];
            String[] R = new String[n2];

            System.arraycopy(array, left, L, 0, n1);
            System.arraycopy(array, mid + 1, R, 0, n2);

            int i = 0, j = 0, k = left;
            while (i < n1 && j < n2) {
                if (compararAlfanumerico(L[i], R[j], ascendente) <= 0) {
                    array[k++] = L[i++];
                } else {
                    array[k++] = R[j++];
                }
            }

            while (i < n1) array[k++] = L[i++];
            while (j < n2) array[k++] = R[j++];
        }
    }

    // QuickSort Paralelo
    static class QuickSortTask extends RecursiveAction {
        private String[] array;
        private int left, right;
        private boolean ascendente;

        QuickSortTask(String[] array, int left, int right, boolean ascendente) {
            this.array = array;
            this.left = left;
            this.right = right;
            this.ascendente = ascendente;
        }

        @Override
        protected void compute() {
            if (left < right) {
                int pivotIndex = partition(array, left, right, ascendente);
                QuickSortTask leftTask = new QuickSortTask(array, left, pivotIndex - 1, ascendente);
                QuickSortTask rightTask = new QuickSortTask(array, pivotIndex + 1, right, ascendente);
                invokeAll(leftTask, rightTask);
            }
        }

        private int partition(String[] array, int left, int right, boolean ascendente) {
            String pivot = array[right];
            int i = left - 1;

            for (int j = left; j < right; j++) {
                if (compararAlfanumerico(array[j], pivot, ascendente) <= 0) {
                    i++;
                    swap(array, i, j);
                }
            }

            swap(array, i + 1, right);
            return i + 1;
        }

        private void swap(String[] array, int i, int j) {
            String temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    // Comparación alfanumérica: Números primero, luego letras
    private static int compararAlfanumerico(String a, String b, boolean ascendente) {
        boolean aEsNumero = Character.isDigit(a.charAt(0));
        boolean bEsNumero = Character.isDigit(b.charAt(0));

        if (aEsNumero && !bEsNumero) return ascendente ? -1 : 1; // Números primero
        if (!aEsNumero && bEsNumero) return ascendente ? 1 : -1;  // Letras después

        if (aEsNumero && bEsNumero) { // Comparar números numéricamente
            int numA = extraerNumero(a);
            int numB = extraerNumero(b);
            return ascendente ? Integer.compare(numA, numB) : Integer.compare(numB, numA);
        }

        // Si ambos son letras, comparar alfabéticamente
        return ascendente ? a.compareToIgnoreCase(b) : b.compareToIgnoreCase(a);
    }

    private static int extraerNumero(String s) {
        StringBuilder numero = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                numero.append(c);
            } else {
                break;
            }
        }
        return numero.length() > 0 ? Integer.parseInt(numero.toString()) : 0;
    }
}

    private static void medirTiempo(String metodo, String[] array, boolean esMerge, boolean ascendente) {
        System.out.println("Ejecutando " + metodo + "...");

        long inicio = System.nanoTime(); // Iniciar medición de tiempo
        if (esMerge) {
            Ordenamientos.ordenarMergeSort(array, ascendente);
        } else {
            Ordenamientos.ordenarQuickSort(array, ascendente);
        }
        long fin = System.nanoTime(); // Finalizar medición de tiempo

        long tiempoTotalMs = (fin - inicio) / 1_000_000; // Convertir a milisegundos

        imprimirArray(array);

        System.out.println(metodo + " completado en " + tiempoTotalMs + " ms.");
        
    }

    private static void imprimirArray(String[] array) {
        for (String str : array) {
            System.out.println(str);
        }
        System.out.println();
    }

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
            stream
            .filter(palabra -> contarVocales(palabra) == 3)
            .forEach(System.out::println); // Imprime cada línea en consola
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private static long contarVocales(String palabra) {
        return palabra.toLowerCase().chars()
                .filter(c -> "aeiou".indexOf(c) != -1)
                .count();
    }
    
    /*La funcion realiza un conteo de las palabras que 
     *inicien con T y terminen con 9.
     *archivo: Archivo donde tomaremos las palabras para la busqueda*/
    public static void buscarIniciaYTermina(String archivo){
        // Leer archivo línea por línea usando Streams
        try (Stream<String> stream = Files.lines(Paths.get(archivo))) {
            
            System.out.println(stream
                                .filter(palabra -> palabra.toLowerCase()
                                                            .startsWith("t") && 
                                                    palabra.endsWith("9"))
                                .count()
                            );
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void ordenarMergeAscendente(String archivo){
        // Leer archivo línea por línea usando Streams
        System.out.println("111111111111111");
        try (Stream<String> stream = Files.lines(Paths.get(archivo))) {
            System.out.println("2222222222222222222222");
            // Convertimos el stream en un array de Strings
            String[] array = stream.toArray(String[]::new);
            medirTiempo("Merge Sort Ascendente", array.clone(), true, true);

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


