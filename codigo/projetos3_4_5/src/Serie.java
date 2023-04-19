public class Serie {

    private static final String[] GENEROS = new String[8];
    private String nome;
    private String idSerie;
    private String genero;
    private String idioma;
    private int quantidadeEpisodios;
    private int audiencia;
    private String dataLancamento;

    // Construtor
    public Serie(String nome, String genero, String idioma, int quantidadeEpisodios) {
        this.nome = nome;
        this.genero = genero;
        this.idioma = idioma;
        this.quantidadeEpisodios = quantidadeEpisodios < 0 ? 0 : quantidadeEpisodios;
        this.audiencia = 0;
    }

    public Serie(String idSerie, String nome, String dataLancamento) {
        this.idSerie = idSerie;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
    }

    private int validarQuantidadeEpisodios(int quantidadeEpisodios2) {

        if (this.quantidadeEpisodios < 3) {
            return 0; // caso quantidade de episódios seja inválida retorna 0 e não preenche o
                      // atributo quantidade de episódios
        }
        return this.quantidadeEpisodios;

    }

    /**
     * Metodo que registra a quantidade de pessoas que assistiram a série
     */
    public void registrarAudiencia() {
        this.audiencia += 1;
    }

    public int getAudiencia() {
        return this.audiencia;
    }

    public String getGenero() {
        return this.genero;
    }

    public String getIdioma() {
        return this.idioma;
    }

    public int getEpisodios() {
        return this.quantidadeEpisodios;
    }

    public String getNome() {
        return this.nome;
    }

    public String getId() {
        return this.idSerie;
    }

    @Override
    public String toString() {
        return this.idSerie + ";" + this.nome + ";" + this.dataLancamento;
    }
}
