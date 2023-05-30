import java.time.LocalDate;

public class Assistido {
    // Atributos
    private LocalDate data;
    private Catalogo catalogo;
    private Avaliacao avaliacao;

    // Construtor
    public Assistido(Catalogo catalogo, LocalDate data) {
        this.catalogo = catalogo;
        this.data = data;
        this.avaliacao = null;
    }

    public LocalDate getData() {
        return this.data;
    }

    public Catalogo getCatalogo() {
        return this.catalogo;
    }

    /**
     * Metodo que adiciona uma avaliacao a uma midia assitida
     * @param avaliacao
     * @return
     */
    public Avaliacao adicionarAvaliacao(Avaliacao avaliacao){
        this.avaliacao = avaliacao;
        this.catalogo.avaliarMidia(avaliacao);
        return this.avaliacao;
    }

    /**
     * Metodo que retorna uma midia assistida com sua data de visualizacao
     * @return
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
