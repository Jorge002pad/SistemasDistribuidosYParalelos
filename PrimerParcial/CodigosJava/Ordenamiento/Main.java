import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public static void main(String[] args) {
        String archivo = "datos.txt"; // Ruta del archivo

        try (Stream<String> stream = Files.lines(Paths.get(archivo))) {
            // Convertimos el stream en un array de Strings
            String[] array = stream.toArray(String[]::new);

            medirTiempo("Merge Sort Ascendente", array.clone(), true, true);
            medirTiempo("Merge Sort Descendente", array.clone(), true, false);
            medirTiempo("Quick Sort Ascendente", array.clone(), false, true);
            medirTiempo("Quick Sort Descendente", array.clone(), false, false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void imprimirArray(String[] array) {
        for (String str : array) {
            System.out.println(str);
        }
        System.out.println();
    }
}

