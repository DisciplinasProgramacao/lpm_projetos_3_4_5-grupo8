import java.time.LocalDate;

public class Assistido {

    //#region ATRIBUTOS
    private LocalDate data;
    private Catalogo catalogo;
    private Avaliacao avaliacao;
    //#endregion

    //#region CONSTRUTOR
    
    /**
     * Construtor da classe assistido, recebendo o catalogo e a data em que a midia foi assistida
     * 
     * @param catalogo
     * @param data
     * 
     */
    public Assistido(Catalogo catalogo, LocalDate data) {
        this.catalogo = catalogo;
        this.data = data;
        this.avaliacao = null;
    }
    //#endregion

    /**
     * Método que retorna a data que a midia foi assistida
     * 
     * @return data (LocalDate)
     * 
     */
    public LocalDate getData() {
        return this.data;
    }

    /**
     * Método que retorna a midia assistida
     * 
     * @return catalogo assistido (Catalogo)
     * 
     */    
    public Catalogo getCatalogo() {
        return this.catalogo;
    }

    /**
     * Metodo que adiciona uma avaliacao e uma midia que foi assitida
     * 
     * @param avaliacao (Avaliacao)
     * @return Avaliacao efetuada (Avaliacao)
     * 
     */
    public Avaliacao adicionarAvaliacao(Avaliacao avaliacao){
        Avaliacao avaliacaoEfetuada = this.catalogo.avaliarMidia(avaliacao);

        if(avaliacaoEfetuada != null)
            this.avaliacao = avaliacao;

        return avaliacaoEfetuada;
    }

    /**
     * Metodo que formata uma string com a midia assistida e sua data de visualizacao
     * 
     * @return String com os dados formatados
     * 
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("- " + this.catalogo.toString() + 
            "\n   Assistido em: " + this.data.toString() );

        if(this.avaliacao != null){
            stringBuilder.append("\n   " + this.avaliacao.toString() + "\n");
        }

        return stringBuilder.toString();
    }
}
