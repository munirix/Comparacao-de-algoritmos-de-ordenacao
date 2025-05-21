import java.util.Arrays;
/*
 * 
 * Implementações dos algoritmos
 * 
 */
public class Algoritmos {

    // Método para executar o algoritmo especificado e retornar o resultado
    public static ArmazenaResultado runAlgorithm(String algorithmName, int[] arr) {
        switch (algorithmName) {
            case "BubbleSort":
                return bubbleSort(arr);
            case "InsertionSort":
                return insertionSort(arr);
            case "SelectionSort":
                return selectionSort(arr);
            case "MergeSort":
                return mergeSort(arr);
            case "QuickSort":
                return quickSort(arr);
            case "HeapSort":
                return heapSort(arr);
            case "CountingSort":
                return countingSort(arr);
            case "RadixSort":
                return radixSort(arr);
            default:
                throw new IllegalArgumentException("Algoritmo desconhecido: " + algorithmName);
        }
    }

    // Bubble Sort
    public static ArmazenaResultado bubbleSort(int[] arr) {
        int comparisons = 0, swaps = 0;
        boolean swapped;
        for (int i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return new ArmazenaResultado(comparisons, swaps);
    }

    // Insertion Sort
    public static ArmazenaResultado insertionSort(int[] arr) {
        int comparisons = 0, swaps = 0;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    swaps++;
                    j--;
                } else break;
            }
            arr[j + 1] = key;
            if (j + 1 != i) swaps++;
        }
        return new ArmazenaResultado(comparisons, swaps);
    }

    // Selection Sort
    public static ArmazenaResultado selectionSort(int[] arr) {
        int comparisons = 0, swaps = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                comparisons++;
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            if (minIdx != i) {
                int temp = arr[minIdx];
                arr[minIdx] = arr[i];
                arr[i] = temp;
                swaps++;
            }
        }
        return new ArmazenaResultado(comparisons, swaps);
    }

    // Merge Sort
    public static ArmazenaResultado mergeSort(int[] arr) {
        int[] comp = {0}, swp = {0};
        mergeSort(arr, 0, arr.length - 1, comp, swp);
        return new ArmazenaResultado(comp[0], swp[0]);
    }

    private static void mergeSort(int[] arr, int l, int r, int[] comp, int[] swp) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m, comp, swp);
            mergeSort(arr, m + 1, r, comp, swp);
            merge(arr, l, m, r, comp, swp);
        }
    }

    private static void merge(int[] arr, int l, int m, int r, int[] comp, int[] swp) {
        int[] left = Arrays.copyOfRange(arr, l, m + 1);
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);
        int i = 0, j = 0, k = l;

        while (i < left.length && j < right.length) {
            comp[0]++;
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
                swp[0]++;
            } else {
                arr[k++] = right[j++];
                swp[0]++;
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
            swp[0]++;
        }
        while (j < right.length) {
            arr[k++] = right[j++];
            swp[0]++;
        }
    }

    // Quick Sort
    public static ArmazenaResultado quickSort(int[] arr) {
        int[] comp = {0}, swp = {0};
        quickSort(arr, 0, arr.length - 1, comp, swp);
        return new ArmazenaResultado(comp[0], swp[0]);
    }

    private static void quickSort(int[] arr, int low, int high, int[] comp, int[] swp) {
        if (low < high) {
            int pi = partition(arr, low, high, comp, swp);
            quickSort(arr, low, pi - 1, comp, swp);
            quickSort(arr, pi + 1, high, comp, swp);
        }
    }

    private static int partition(int[] arr, int low, int high, int[] comp, int[] swp) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            comp[0]++;
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j, swp);
            }
        }
        swap(arr, i + 1, high, swp);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j, int[] swp) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        swp[0]++;
    }

    // Heap Sort
    public static ArmazenaResultado heapSort(int[] arr) {
        int comparisons = 0, swaps = 0;
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            ArmazenaResultado res = heapify(arr, n, i);
            comparisons += res.getComparisons();
            swaps += res.getSwaps();
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i, new int[]{swaps});
            swaps++;
            ArmazenaResultado res = heapify(arr, i, 0);
            comparisons += res.getComparisons();
            swaps += res.getSwaps();
        }
        return new ArmazenaResultado(comparisons, swaps);
    }

    private static ArmazenaResultado heapify(int[] arr, int n, int i) {
        int comparisons = 0, swaps = 0;
        int largest = i, left = 2 * i + 1, right = 2 * i + 2;

        if (left < n) {
            comparisons++;
            if (arr[left] > arr[largest]) largest = left;
        }
        if (right < n) {
            comparisons++;
            if (arr[right] > arr[largest]) largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest, new int[]{swaps});
            swaps++;
            ArmazenaResultado res = heapify(arr, n, largest);
            comparisons += res.getComparisons();
            swaps += res.getSwaps();
        }
        return new ArmazenaResultado(comparisons, swaps);
    }

    // Counting Sort
    public static ArmazenaResultado countingSort(int[] arr) {
        if (arr.length == 0) return new ArmazenaResultado(0, 0);
        int max = Arrays.stream(arr).max().getAsInt();
        int[] count = new int[max + 1];
        Arrays.fill(count, 0);

        for (int num : arr) count[num]++;
        for (int i = 1; i <= max; i++) count[i] += count[i - 1];

        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        int swaps = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
            swaps++;
        }
        return new ArmazenaResultado(0, swaps);
    }

    // Radix Sort
    public static ArmazenaResultado radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int exp = 1, totalSwaps = 0;

        while (max / exp > 0) {
            totalSwaps += countingSortByDigit(arr, exp).getSwaps();
            exp *= 10;
        }
        return new ArmazenaResultado(0, totalSwaps);
    }

    private static ArmazenaResultado countingSortByDigit(int[] arr, int exp) {
        int[] output = new int[arr.length];
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (int num : arr) count[(num / exp) % 10]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];
        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        int swaps = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
            swaps++;
        }
        return new ArmazenaResultado(0, swaps);
    }
}