import java.io.*;

/*
 * 
 * Lê arquivos de dados
 * 
 */
public class LeitorArquivos {
    public static int[] readDataset(String filename) throws IOException {
        // extrai o tipo (ex: "sorted" de "sorted_10000.txt")
        String tipo = filename.split("_")[0]; // Pega tudo antes do primeiro "_"
        
        //"sorted/sorted_10000.txt"
        String caminhoCompleto = tipo + "/" + filename;

        int tamanho = Integer.parseInt(filename.split("[_.]")[1]); // Pega o número após o '_'
        
        //ler os valores
        int[] arr = new int[tamanho];
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCompleto))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null) {
                arr[index++] = Integer.parseInt(line);
            }
        }
        
        return arr;
    }

    //testando leitor: ok
    // public static void main(String[] args) throws IOException {
    //     int[] arr = new int[1000];
    //     arr = readDataset("random_10000.txt");
    //     for (int i = 0; i < arr.length; i++) {
    //         System.out.println(arr[i]);
    //     }
    // }
}