public class Filme extends Catalogo {

    private int duracao;

    public Filme(int id, String nome, String dataLancamento, int duracao) {
        super(id, nome, dataLancamento);
        this.duracao = duracao;
    }

    public Filme(int id, String nome, String dataLancamento, String genero, String idioma, int duracao) {
        super(id, nome, dataLancamento, genero, idioma);
        this.duracao = duracao;
    }

    /**
     * @return duracao do filme
     */
    public int getDuracao() {
        return this.duracao;
    }

    /**
     * Metodo que registra a quantidade de pessoas que assistiram a série
     */
    public void registrarAudiencia() {
        super.registrarAudiencia();
    }

    @Override
    public String toString() {
        return super.toString() + ";" + this.duracao + " min";
    }

}
