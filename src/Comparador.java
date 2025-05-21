import java.io.*;
import java.util.*;
/*
 * 
 * Executa testes e gerar resultados
 * 
 */
public class Comparador {
    public static void main(String[] args) throws IOException {
        String[] algoritmos = {"BubbleSort", "InsertionSort", "SelectionSort", "MergeSort",
                "QuickSort", "HeapSort", "CountingSort", "RadixSort"};
        String[] tipos = {"sorted", "reverse", "random"};
        int[] N = {10000, 20000, 40000, 80000, 100000};

        List<String> results = new ArrayList<>();
        results.add("Algoritmo, Tipo, Tamanho, Tempo (ns), Comparacoes, Trocas de Posicao");

        for (String tipo : tipos) {
            for (int tamanho : N) {
                String filename = tipo + "_" + tamanho + ".txt";
                int[] data = LeitorArquivos.readDataset(filename);

                for (String algoritmo : algoritmos) {
                    long totalTempo = 0;
                    int totalComp = 0, totalTrocasPos = 0;
                    int runs = 3;

                    for (int i = 0; i < runs; i++) {
                        int[] copy = Arrays.copyOf(data, data.length);
                        long start = System.nanoTime();
                        ArmazenaResultado result = Algoritmos.runAlgorithm(algoritmo, copy);
                        long end = System.nanoTime();
                        totalTempo += (end - start);
                        totalComp += result.getNumComparacoes();
                        totalTrocasPos += result.getNumTrocasPosicao();
                    }

                    results.add(String.format("%s, %s, %d, %d, %d, %d",
                            (algoritmo), 
                            (tipo), 
                            (tamanho),
                            (totalTempo / runs),
                            (totalComp / runs),
                            (totalTrocasPos / runs)
                        ));
                }
            }
        }

        try (PrintWriter pw = new PrintWriter("resultados.csv")) {
            results.forEach(pw::println);

        }
    }
}