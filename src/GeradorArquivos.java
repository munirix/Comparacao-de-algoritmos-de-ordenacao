import java.io.*;
import java.util.Random;
/*
 * 
 * Gerar arquivos de dados
 * 
 */
public class GeradorArquivos {

    public static void generateDataset(int size, String type, String filename) throws IOException {
        // Cria a pasta correspondente ao tipo, se não existir
        File folder = new File(type);
        if (!folder.exists()) {
            folder.mkdir(); // Cria a pasta
        }

        int[] data = new int[size];
        Random rand = new Random();
        switch (type) {
            case "sorted":
                data[0] = rand.nextInt(10000);
                for (int i = 1; i < size; i++) { 
                    data[i] = data[i - 1] + rand.nextInt(10000) + 1;
                }
                break;
            case "reverse":
                data[0] = Integer.MAX_VALUE - rand.nextInt(10000);
                for (int i = 1; i < size; i++) {
                    data[i] = Math.max(data[i-1] - rand.nextInt(10000), 0);
                }
                break;
            case "random":
                for (int i = 0; i < size; i++) {
                    data[i] = rand.nextInt(Integer.MAX_VALUE);
                }
                break;
        }

        // Salva o arquivo dentro da pasta correspondente
        String filePath = type + File.separator + filename;
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (int num : data) pw.println(num);
        }
    }

    public static void main(String[] args) throws IOException {
        int[] sizes = {10000, 20000, 40000, 80000, 100000};
        String[] types = {"sorted", "reverse", "random"};

        for (String type : types) {
            for (int size : sizes) {
                generateDataset(size, type, type + "_" + size + ".txt");
            }
        }
        System.out.println("Arquivos gerados com sucesso nas pastas: sorted/, reverse/, random/");
    }
}