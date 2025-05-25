/*
 * 
 * Implementações dos algoritmos
 * 
 */

public class Algoritmos {

    public static ArmazenaResultado executarAlgoritmo(String nomeAlgoritmo, int[] arr) {
        long startTime = System.nanoTime();
        long memoriaAlocada = 0L;

        // Executa o algoritmo
        ArmazenaResultado resultado = null;
        // Adiciona memória teórica para estruturas temporárias
        switch (nomeAlgoritmo) {
            case "BubbleSort":
                memoriaAlocada += arr.length * 4L + 12; 
                resultado = bubbleSort(arr);
                break;
            case "InsertionSort":
                memoriaAlocada += arr.length * 4L + 12; 
                resultado = insertionSort(arr);
                break;
            case "SelectionSort":
                memoriaAlocada += arr.length * 4L + 12; 
                resultado = selectionSort(arr);
                break;
            case "MergeSort":
                memoriaAlocada += (arr.length * 4L * 2 )+ (12 * 3); //dois arrays temporários com tamanho de arr
                resultado = mergeSort(arr);
                break;
            case "QuickSort":
                memoriaAlocada += arr.length * 4L + 12; 
                resultado = quickSort(arr);
                break;
            case "HeapSort":
                memoriaAlocada += arr.length * 4L + 12; 
                resultado = heapSort(arr);
                break;
            case "CountingSort":
                int max = 0;
                for (int i = 0; i < arr.length; i++) {
                    max = Math.max(max, arr[i]);
                }
                memoriaAlocada += (12 + ((max + 1) * 4L)) + (12 + (arr.length * 4L)); // count[] + output[]
                resultado = countingSort(arr);
                break;
            case "RadixSort":
                memoriaAlocada += (12 + (arr.length * 4L)) * 2 + (12 + (10 * 4L));
                resultado = radixSort(arr);
                break;
            default:
                throw new IllegalArgumentException("Algoritmo desconhecido");
        }

        resultado.setMemoriaUtilizada(memoriaAlocada);
        resultado.setTempo(System.nanoTime() - startTime);

        return resultado;
    }

    // Bubble Sort
    public static ArmazenaResultado bubbleSort(int[] arr) {
        //4 bytes * N
        long numComparacoes = 0, numTrocasPos = 0; // desconciderar
        for (int i = 0; i < arr.length - 1; i++) { //4 bytes
            for (int j = 0; j < arr.length - i - 1; j++) {//4 bytes
                numComparacoes++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j]; //4 bytes
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    numTrocasPos++;
                }
            }
        }
        return new ArmazenaResultado(numComparacoes, numTrocasPos);
    }

    // Insertion Sort
    public static ArmazenaResultado insertionSort(int[] arr) {
        //4 bytes * N
        long numComparacoes = 0, numTrocasPos = 0;// desconciderar
        for (int i = 1; i < arr.length; i++) {//4 bytes
            int key = arr[i];//4 bytes
            int j = i - 1;//4 bytes
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
        //4 bytes * N
        long numComparacoes = 0, numTrocasPos = 0;// desconciderar
        for (int i = 0; i < arr.length - 1; i++) {//4 bytes
            int minIdx = i;//4 bytes
            for (int j = i + 1; j < arr.length; j++) {//4 bytes
                numComparacoes++;
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            if (minIdx != i) {
                int temp = arr[minIdx];//4 bytes
                arr[minIdx] = arr[i];
                arr[i] = temp;
                numTrocasPos++;
            }
        }
        return new ArmazenaResultado(numComparacoes, numTrocasPos);
    }
    
    // Merge Sort
    public static ArmazenaResultado mergeSort(int[] arr) {
        //4 bytes * N
        long[] comp_ = {0}, trocas_ = {0};// desconciderar
        merge_sort(arr, 0, arr.length - 1, comp_, trocas_);

        return new ArmazenaResultado(comp_[0], trocas_[0]);
    }

    private static void merge_sort(int[] arr, int l, int r, long[] comp_, long[] trocas_) {
        if (l < r) { //4 bytes * 2
            int m = (l + r) / 2;//4 bytes
            merge_sort(arr, l, m, comp_, trocas_);
            merge_sort(arr, m + 1, r, comp_, trocas_);
            merge(arr, l, m, r, comp_, trocas_);
        }
    }

    private static void merge(int[] arr, int l, int m, int r, long[] comp_, long[] trocas_) {
        int n1 = m - l + 1;//4 bytes
        int n2 = r - m;//4 bytes

        int[] esq = new int[n1]; //4 bytes * n1
        int[] dir = new int[n2]; //4 bytes * n2

        int i = 0, j = 0, k = l;//4 bytes * 3

        for (i = 0; i < n1; ++i)
            esq[i] = arr[l + i];
        for (j = 0; j < n2; ++j)
            dir[j] = arr[m + 1 + j];

        i = 0; j = 0; k = l;

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
        //4 bytes * N
        long[] comp_ = {0}, trocas_ = {0}; // desconciderar
        quick_sort(arr, 0, arr.length - 1, comp_, trocas_);

        return new ArmazenaResultado(comp_[0], trocas_[0]);
    }

    private static void quick_sort(int[] arr, int baixo, int alto, long[] comp_, long[] trocas_) {
        if (baixo < alto) { //4 bytes * 2
            // int pi = partition(arr, baixo, alto, comp_, trocas_);
            // quick_sort(arr, baixo, pi - 1, comp_, trocas_);
            // quick_sort(arr, pi + 1, alto, comp_, trocas_);
            //Overflow

            // Escolhe o pivô como a mediana entre primeiro, último e meio
            int meio = baixo + (alto - baixo) / 2;//4 bytes
            if (arr[meio] < arr[baixo]) trocar(arr, baixo, meio, trocas_);
            if (arr[alto] < arr[baixo]) trocar(arr, baixo, alto, trocas_);
            if (arr[meio] < arr[alto]) trocar(arr, meio, alto, trocas_);

            int pi = partition(arr, baixo, alto, comp_, trocas_);//4 bytes
            quick_sort(arr, baixo, pi - 1, comp_, trocas_);
            quick_sort(arr, pi + 1, alto, comp_, trocas_);
        }
    }

    private static int partition(int[] arr, int baixo, int alto, long[] comp_, long[] trocas_) {
        int pivo = arr[alto];//4 bytes
        int i = baixo - 1;//4 bytes
        for (int j = baixo; j < alto; j++) {//4 bytes
            comp_[0]++;
            if (arr[j] < pivo) {
                i++;
                trocar(arr, i, j, trocas_);
            }
        }
        trocar(arr, i + 1, alto, trocas_);
        return i + 1;
    }

    private static void trocar(int[] arr, int i, int j, long[] trocas_) {
        int temp = arr[i];//4 bytes
        arr[i] = arr[j];
        arr[j] = temp;
        trocas_[0]++;
    }

    // Heap Sort
    public static ArmazenaResultado heapSort(int[] arr) {
        //4 bytes * N
        long numComparacoes = 0, numTrocasPos = 0;// desconciderar
        int i = 0;//4 bytes
        
        for (i = arr.length / 2 - 1; i >= 0; i--) {
            ArmazenaResultado res = heapify(arr, arr.length, i); //8 bytes * 4
            numComparacoes += res.getNumComparacoes();
            numTrocasPos += res.getNumTrocasPosicao();
        }
        
        for (i = arr.length - 1; i > 0; i--) {
            trocar(arr, 0, i, new long[]{numTrocasPos}); //4 bytes
            numTrocasPos++;
            ArmazenaResultado res = heapify(arr, i, 0);//
            numComparacoes += res.getNumComparacoes();
            numTrocasPos += res.getNumTrocasPosicao();
        }
        return new ArmazenaResultado(numComparacoes, numTrocasPos);
    }
    
    private static ArmazenaResultado heapify(int[] arr, int n, int i) {
        long numComparacoes = 0, numTrocasPos = 0;// desconciderar
        int largest = i, esq = 2 * i + 1, dir = 2 * i + 2;//4 bytes * 3
        
        if (esq < n) {
            numComparacoes++;
            if (arr[esq] > arr[largest]) largest = esq;
        }
        if (dir < n) {
            numComparacoes++;
            if (arr[dir] > arr[largest]) largest = dir;
        }
        
        if (largest != i) {
            trocar(arr, i, largest, new long[]{numTrocasPos});//4 bytes
            numTrocasPos++;
            ArmazenaResultado res = heapify(arr, n, largest);//8 bytes * 4
            numComparacoes += res.getNumComparacoes();
            numTrocasPos += res.getNumTrocasPosicao();
        }
        return new ArmazenaResultado(numComparacoes, numTrocasPos);
    }
    
    // Counting Sort
    public static ArmazenaResultado countingSort(int[] arr) {
        //4 bytes * N
        int max = 0;//4 bytes
        int i = 0;//4 bytes
        for (i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        
        int[] count = new int[max + 1];//4 bytes * max+1
        for (i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        for (i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }
        
        int[] output = new int[arr.length];//4 bytes * N
        for (i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }
        
        long numTrocasPos = 0; // desconciderar
        for (i = 0; i < arr.length; i++) {
            arr[i] = output[i];
            numTrocasPos++;
        }
        return new ArmazenaResultado(0, numTrocasPos);
    }
    
    // Radix Sort
    public static ArmazenaResultado radixSort(int[] arr) {
        //4 bytes * N
        int max = arr[0];//4 bytes
        for (int i = 1; i < arr.length; i++){//4 bytes
            if (arr[i] > max){
                max = arr[i];
            }
        }
        int exp = 1;//4 bytes
        long numTrocasPos = 0;// desconciderar

        for (exp = 1; max / exp > 0; exp *= 10){
            numTrocasPos += countingSortPorDigito(arr, exp).getNumTrocasPosicao();
        }
        return new ArmazenaResultado(0, numTrocasPos);
    }

    /*
     * ordenação estável de números com base em um dígito específico (unidades, dezenas, centenas, etc.)
     */
    private static ArmazenaResultado countingSortPorDigito(int[] arr, int exp) {
        int[] output = new int[arr.length];
        int[] count = new int[10];
        int i = 0;//4 bytes

        for(i = 0; i < count.length; i++){
            count[i] = 0;
        }

        for (int num : arr) //4 bytes
            count[(num / exp) % 10]++;

        for (i = 1; i < 10; i++) 
            count[i] += count[i - 1];

        for (i = arr.length - 1; i >= 0; i--) {
            int digito = (arr[i] / exp) % 10;//4 bytes
            output[count[digito] - 1] = arr[i];
            count[digito]--;
        }

        long numTrocasPos = 0;// desconciderar
        for (i = 0; i < arr.length; i++) {
            arr[i] = output[i];
            numTrocasPos++;
        }
        return new ArmazenaResultado(0, numTrocasPos);
    }

}