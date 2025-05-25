/*
 * 
 * Armazena resultados
 * 
 */
public class ArmazenaResultado {
    private long numComparacoes;
    private long numTrocasPos;
    private long memoriaUtilizada; // Em bytes
    private long tempoNano; 

    public ArmazenaResultado(long numComparacoes, long numTrocasPos) {
        this.numComparacoes = numComparacoes;
        this.numTrocasPos = numTrocasPos;
    }
    
    public long getNumComparacoes() { return numComparacoes; }
    public long getNumTrocasPosicao() { return numTrocasPos; }
    public long getMemoriaUtilizada() { return memoriaUtilizada; }
    public long getTempo() { return tempoNano; }
    
    public void setMemoriaUtilizada(long memoriaUtilizada){
        this.memoriaUtilizada = memoriaUtilizada;
    }
    public void setTempo(long setTempo){
        this.tempoNano = setTempo;
    }
}