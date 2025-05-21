import java.io.*;
import java.util.*;
/*
 * 
 * Executa testes e gerar resultados
 * 
 */
public class Comparador {
    public static void main(String[] args) throws IOException {
        String[] algorithms = {"BubbleSort", "InsertionSort", "SelectionSort", "MergeSort",
                "QuickSort", "HeapSort", "CountingSort", "RadixSort"};
        String[] types = {"sorted", "reverse", "random"};
        int[] sizes = {10000, 20000, 40000, 80000, 100000};

        List<String> results = new ArrayList<>();
        results.add("Algorithm, Type, Size, Time (ns), Comparisons, Swaps");

        for (String type : types) {
            for (int size : sizes) {
                String filename = type + "_" + size + ".txt";
                int[] data = LeitorArquivos.readDataset(filename);

                for (String algo : algorithms) {
                    long totalTime = 0;
                    int totalComp = 0, totalSwaps = 0;
                    int runs = 3;

                    for (int i = 0; i < runs; i++) {
                        int[] copy = Arrays.copyOf(data, data.length);
                        long start = System.nanoTime();
                        ArmazenaResultado result = Algoritmos.runAlgorithm(algo, copy);
                        long end = System.nanoTime();
                        totalTime += (end - start);
                        totalComp += result.getComparisons();
                        totalSwaps += result.getSwaps();
                    }

                    results.add(String.format("%s, %s, %d, %d, %d, %d",
                            algo, type, size,
                            totalTime / runs,
                            totalComp / runs,
                            totalSwaps / runs));
                }
            }
        }

        try (PrintWriter pw = new PrintWriter("results.csv")) {
            results.forEach(pw::println);
        }
    }
}