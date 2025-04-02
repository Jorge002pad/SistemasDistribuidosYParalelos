package Ordenamiento;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

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

    // Clase MergeSort Paralelo
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
                if ((L[i].compareTo(R[j]) <= 0) == ascendente) {
                    array[k++] = L[i++];
                } else {
                    array[k++] = R[j++];
                }
            }

            while (i < n1) array[k++] = L[i++];
            while (j < n2) array[k++] = R[j++];
        }
    }

    // Clase QuickSort Paralelo
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
                if ((array[j].compareTo(pivot) <= 0) == ascendente) {
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
}

