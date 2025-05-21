
/*
 * 
 * Armazena resultados
 * 
 */
public class ArmazenaResultado {
    private int comparisons;
    private int swaps;

    public ArmazenaResultado(int comparisons, int swaps) {
        this.comparisons = comparisons;
        this.swaps = swaps;
    }

    public int getComparisons() { return comparisons; }
    public int getSwaps() { return swaps; }
}