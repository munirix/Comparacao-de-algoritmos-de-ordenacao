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
        switch (type) {
            case "sorted":
                for (int i = 0; i < size; i++) data[i] = i;
                break;
            case "reverse":
                for (int i = 0; i < size; i++) data[i] = size - i - 1;
                break;
            case "random":
                Random rand = new Random();
                for (int i = 0; i < size; i++) data[i] = rand.nextInt(size * 10);
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



// public class GeradorArquivos {
//     public static void generateDataset(int size, String type, String filename) throws IOException {
//         int[] data = new int[size];
//         switch (type) {
//             case "sorted":
//                 for (int i = 0; i < size; i++) data[i] = i;
//                 break;
//             case "reverse":
//                 for (int i = 0; i < size; i++) data[i] = size - i - 1;
//                 break;
//             case "random":
//                 Random rand = new Random();
//                 for (int i = 0; i < size; i++) data[i] = rand.nextInt(size * 10);
//                 break;
//         }

//         try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
//             for (int num : data) pw.println(num);
//         }
//     }

//     public static void main(String[] args) throws IOException {
//         int[] sizes = {10000, 20000, 40000, 80000, 100000};
//         String[] types = {"sorted", "reverse", "random"};
//         for (String type : types) {
//             for (int size : sizes) {
//                 generateDataset(size, type, type + "_" + size + ".txt");
//             }
//         }
//     }
// }