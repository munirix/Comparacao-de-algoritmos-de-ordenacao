import java.io.*;
/*
 * 
 * Executa testes e gera resultados
 * 
 */
public class Comparador {
    public static void main(String[] args) throws IOException {
        String[] algoritmos = {"BubbleSort", "InsertionSort", "SelectionSort", "MergeSort",
                "QuickSort", "HeapSort", "CountingSort", "RadixSort"};
        String[] tipos = {"sorted", "reverse", "random"};
        int[] N = {10000, 20000, 40000, 80000, 100000};

        String[] results = new String[1 + tipos.length * N.length * algoritmos.length];
        results[0] = "Algoritmo, Tipo, Tamanho, Tempo (ns), Comparacoes, Trocas de Posicao";

        int indice = 1;
        for (String tipo : tipos) {
            for (int tamanho : N) {
                String filename = tipo + "_" + tamanho + ".txt";
                int[] data = LeitorArquivos.readDataset(filename);

                for (String algoritmo : algoritmos) {
                    long totalTempo = 0;
                    long totalComp = 0, totalTrocasPos = 0;
                    int runs = 3;

                    for (int i = 0; i < runs; i++) {

                        int[] copy = new int[data.length];
                        for (int j = 0; j < data.length; j++) {
                            copy[j] = data[j];
                        }

                        long start = System.nanoTime();
                        ArmazenaResultado result = Algoritmos.runAlgorithm(algoritmo, copy);
                        long end = System.nanoTime();
                        totalTempo += (end - start); //ns
                        totalComp += result.getNumComparacoes();
                        totalTrocasPos += result.getNumTrocasPosicao();
                    }

                    results[indice++] = (String.format("%s, %s, %d, %d, %d, %d",
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
            for (String line : results) {
                if (line != null) {
                    pw.println(line);
                }
            }

        }
    }
}