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
        results[0] = "Algoritmo; Tipo; Tamanho; Tempo (ns); Tempo (s); Memoria (KB); Comparacoes; Trocas de Posicao";

        int indice = 1;
        for (String tipo : tipos) {
            for (int tamanho : N) {
                String filename = tipo + "_" + tamanho + ".txt";
                int[] data = LeitorArquivos.readDataset(filename);

                for (String algoritmo : algoritmos) {
                    long totalTempoNano = 0;
                    double totalTempoSeg = 0.0;
                    long totalComp = 0, totalTrocasPos = 0, totalMemoria = 0;
                    int runs = 10;
                    int aquecimento = 3; // Execuções iniciais ignoradas

                    for (int i = 0; i < runs+aquecimento; i++) {
                        int[] copy = new int[data.length];
                        for (int j = 0; j < data.length; j++) {
                            copy[j] = data[j];
                        }

                        ArmazenaResultado result = Algoritmos.executarAlgoritmo(algoritmo, copy);
                        
                        if (i >= aquecimento) { // Ignora as primeiras execuções (aquecimento)
                            totalTempoNano += result.getTempo(); //ns
                            totalComp += result.getNumComparacoes();
                            totalTrocasPos += result.getNumTrocasPosicao();
                            totalMemoria += result.getMemoriaUtilizada();
                        }
                    }
                    totalTempoSeg = totalTempoNano / 1000000000.0;
                    results[indice++] = (String.format("%s; %s; %d; %d; %f; %d; %d; %d",
                            (algoritmo), 
                            (tipo), 
                            (tamanho),
                            (totalTempoNano/ runs),
                            (totalTempoSeg/ runs),
                            (totalMemoria / (runs * 1000)),
                            (totalComp/ runs),
                            (totalTrocasPos/ runs)
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