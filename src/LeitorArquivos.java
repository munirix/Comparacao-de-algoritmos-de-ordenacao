import java.io.*;
import java.util.ArrayList;
import java.util.List;
/*
 * 
 * Lê arquivos de dados
 * 
 */
public class LeitorArquivos {
    public static int[] readDataset(String filename) throws IOException {
        // Extrai o tipo (ex: "sorted" de "sorted_10000.txt")
        String tipo = filename.split("_")[0]; // Pega tudo antes do primeiro "_"
        
        // Monta o caminho completo: "tipo/nomeDoArquivo"
        String caminhoCompleto = tipo + File.separator + filename;
        
        // Lê o arquivo normalmente
        List<Integer> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCompleto))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        }
        
        // Converte List<Integer> para int[]
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}



 
// public class LeitorArquivos {
//     public static int[] readDataset(String filename) throws IOException {
//         List<Integer> list = new ArrayList<>();
//         try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
//             String line;
//             while ((line = br.readLine()) != null) {
//                 list.add(Integer.parseInt(line));
//             }
//         }
//         int[] arr = new int[list.size()];
//         for (int i = 0; i < arr.length; i++) arr[i] = list.get(i);
//         return arr;
//     }
// }