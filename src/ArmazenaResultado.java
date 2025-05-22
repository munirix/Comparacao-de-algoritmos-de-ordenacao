/*
 * 
 * Armazena resultados
 * 
 */
public class ArmazenaResultado {
    private long numComparacoes;
    private long numTrocasPos;

    public ArmazenaResultado(long numComparacoes, long numTrocasPos) {
        this.numComparacoes = numComparacoes;
        this.numTrocasPos = numTrocasPos;
    }

    public long getNumComparacoes() { return numComparacoes; }
    public long getNumTrocasPosicao() { return numTrocasPos; }
}