public class Serie {

    private static final String[] GENEROS = new String[8];
    private String nome;
    private int idSerie;
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
        this.quantidadeEpisodios = quantidadeEpisodios < 2 ? 0 : quantidadeEpisodios;
        this.audiencia = 0;
    }

    public Serie(int idSerie, String nome, String dataLancamento) {
        this.idSerie = idSerie;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
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

    public int getId() {
        return this.idSerie;
    }

    @Override
    public String toString() {
        return this.idSerie + ";" + this.nome + ";" + this.dataLancamento;
    }

    @Override
    public boolean equals(Object o){
        Serie outraSerie = (Serie)o;
        return this.nome.equals(outraSerie.nome);
    }
}
