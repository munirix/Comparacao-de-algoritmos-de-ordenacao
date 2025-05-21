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
        int numComparacoes = 0, numTrocasPos = 0;
        boolean houveTroca;
        for (int i = 0; i < arr.length - 1; i++) {
            houveTroca = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                numComparacoes++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    numTrocasPos++;
                    houveTroca = true;
                }
            }
            if (!houveTroca) break;
        }
        return new ArmazenaResultado(numComparacoes, numTrocasPos);
    }

    // Insertion Sort
    public static ArmazenaResultado insertionSort(int[] arr) {
        int numComparacoes = 0, numTrocasPos = 0;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                numComparacoes++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    numTrocasPos++;
                    j--;
                } else break;
            }
            arr[j + 1] = key;
            if (j + 1 != i) numTrocasPos++;
        }
        return new ArmazenaResultado(numComparacoes, numTrocasPos);
    }

    // Selection Sort
    public static ArmazenaResultado selectionSort(int[] arr) {
        int numComparacoes = 0, numTrocasPos = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                numComparacoes++;
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            if (minIdx != i) {
                int temp = arr[minIdx];
                arr[minIdx] = arr[i];
                arr[i] = temp;
                numTrocasPos++;
            }
        }
        return new ArmazenaResultado(numComparacoes, numTrocasPos);
    }

    // Merge Sort
    public static ArmazenaResultado mergeSort(int[] arr) {
        int[] comp_ = {0}, trocas_ = {0};
        merge_sort(arr, 0, arr.length - 1, comp_, trocas_);
        return new ArmazenaResultado(comp_[0], trocas_[0]);
    }

    private static void merge_sort(int[] arr, int l, int r, int[] comp_, int[] trocas_) {
        if (l < r) {
            int m = (l + r) / 2;
            merge_sort(arr, l, m, comp_, trocas_);
            merge_sort(arr, m + 1, r, comp_, trocas_);
            merge(arr, l, m, r, comp_, trocas_);
        }
    }

    private static void merge(int[] arr, int l, int m, int r, int[] comp_, int[] trocas_) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] esq = new int[n1];
        int[] dir = new int[n2];

        for (int i = 0; i < n1; ++i)
            esq[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            dir[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        while (i < esq.length && j < dir.length) {
            comp_[0]++;
            if (esq[i] <= dir[j]) {
                arr[k] = esq[i];
                trocas_[0]++;
                i++;
            } else {
                arr[k] = dir[j];
                trocas_[0]++;
                j++;
            }
            k++;
        }

        while (i < esq.length) {
            arr[k] = esq[i];
            i++;
            k++;
            trocas_[0]++;
        }
        while (j < dir.length) {
            arr[k] = dir[j];
            j++;
            k++;
            trocas_[0]++;
        }
    }

    // Quick Sort
    public static ArmazenaResultado quickSort(int[] arr) {
        int[] comp_ = {0}, trocas_ = {0};
        quick_sort(arr, 0, arr.length - 1, comp_, trocas_);
        return new ArmazenaResultado(comp_[0], trocas_[0]);
    }

    private static void quick_sort(int[] arr, int baixo, int alto, int[] comp_, int[] trocas_) {
        if (baixo < alto) {
            // int pi = partition(arr, baixo, alto, comp_, trocas_);
            // quick_sort(arr, baixo, pi - 1, comp_, trocas_);
            // quick_sort(arr, pi + 1, alto, comp_, trocas_);
            //Overflow

            // Escolhe o pivô como a mediana entre primeiro, último e meio
            int meio = baixo + (alto - baixo) / 2;
            if (arr[meio] < arr[baixo]) trocar(arr, baixo, meio, trocas_);
            if (arr[alto] < arr[baixo]) trocar(arr, baixo, alto, trocas_);
            if (arr[meio] < arr[alto]) trocar(arr, meio, alto, trocas_);

            int pi = partition(arr, baixo, alto, comp_, trocas_);
            quick_sort(arr, baixo, pi - 1, comp_, trocas_);
            quick_sort(arr, pi + 1, alto, comp_, trocas_);
        }
    }

    private static int partition(int[] arr, int baixo, int alto, int[] comp_, int[] trocas_) {
        int pivo = arr[alto];
        int i = baixo - 1;
        for (int j = baixo; j < alto; j++) {
            comp_[0]++;
            if (arr[j] < pivo) {
                i++;
                trocar(arr, i, j, trocas_);
            }
        }
        trocar(arr, i + 1, alto, trocas_);
        return i + 1;
    }

    private static void trocar(int[] arr, int i, int j, int[] trocas_) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        trocas_[0]++;
    }

    // Heap Sort
    public static ArmazenaResultado heapSort(int[] arr) {
        int numComparacoes = 0, numTrocasPos = 0;
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            ArmazenaResultado res = heapify(arr, n, i);
            numComparacoes += res.getNumComparacoes();
            numTrocasPos += res.getNumTrocasPosicao();
        }

        for (int i = n - 1; i > 0; i--) {
            trocar(arr, 0, i, new int[]{numTrocasPos});
            numTrocasPos++;
            ArmazenaResultado res = heapify(arr, i, 0);
            numComparacoes += res.getNumComparacoes();
            numTrocasPos += res.getNumTrocasPosicao();
        }
        return new ArmazenaResultado(numComparacoes, numTrocasPos);
    }

    private static ArmazenaResultado heapify(int[] arr, int n, int i) {
        int numComparacoes = 0, numTrocasPos = 0;
        int largest = i, esq = 2 * i + 1, dir = 2 * i + 2;

        if (esq < n) {
            numComparacoes++;
            if (arr[esq] > arr[largest]) largest = esq;
        }
        if (dir < n) {
            numComparacoes++;
            if (arr[dir] > arr[largest]) largest = dir;
        }

        if (largest != i) {
            trocar(arr, i, largest, new int[]{numTrocasPos});
            numTrocasPos++;
            ArmazenaResultado res = heapify(arr, n, largest);
            numComparacoes += res.getNumComparacoes();
            numTrocasPos += res.getNumTrocasPosicao();
        }
        return new ArmazenaResultado(numComparacoes, numTrocasPos);
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

        int numTrocasPos = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
            numTrocasPos++;
        }
        return new ArmazenaResultado(0, numTrocasPos);
    }

    // Radix Sort
    public static ArmazenaResultado radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int exp = 1, totalNumTrocasPos = 0;

        while (max / exp > 0) {
            totalNumTrocasPos += countingSortByDigit(arr, exp).getNumTrocasPosicao();
            exp *= 10;
        }
        return new ArmazenaResultado(0, totalNumTrocasPos);
    }

    /*
     * ordenação estável de números com base em um dígito específico (unidades, dezenas, centenas, etc.)
     */
    private static ArmazenaResultado countingSortByDigit(int[] arr, int exp) {
        int[] output = new int[arr.length];
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (int num : arr) count[(num / exp) % 10]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];
        for (int i = arr.length - 1; i >= 0; i--) {
            int digito = (arr[i] / exp) % 10;
            output[count[digito] - 1] = arr[i];
            count[digito]--;
        }

        int numTrocasPos = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
            numTrocasPos++;
        }
        return new ArmazenaResultado(0, numTrocasPos);
    }
}