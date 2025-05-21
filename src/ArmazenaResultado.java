
/*
 * 
 * Armazena resultados
 * 
 */
public class ArmazenaResultado {
    private int numComparacoes;
    private int numTrocasPos;

    public ArmazenaResultado(int numComparacoes, int numTrocasPos) {
        this.numComparacoes = numComparacoes;
        this.numTrocasPos = numTrocasPos;
    }

    public int getNumComparacoes() { return numComparacoes; }
    public int getNumTrocasPosicao() { return numTrocasPos; }
}