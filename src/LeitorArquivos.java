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
        List<Integer> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) arr[i] = list.get(i);
        return arr;
    }
}