import java.time.LocalDate;

public class Assistido {
    private LocalDate data;
    private Catalogo catalogo;
    private Avaliacao avaliacao;

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

    public void adicionarAvaliacao(Avaliacao avaliacao){
        this.avaliacao = avaliacao;
    }

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
