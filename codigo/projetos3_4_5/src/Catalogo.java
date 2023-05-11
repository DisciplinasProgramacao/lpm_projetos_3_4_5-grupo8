public abstract class Catalogo {

    private static final String[] GENEROS = new String[8];
    private String nome;
    private int id;
    private String genero;
    private String idioma;
    private int audiencia;
    private Avaliacao avaliacao;
    private String dataLancamento;

    public Catalogo(int id, String nome, String dataLancamento) {
        this.nome = nome;
        this.audiencia = 0;
        this.avaliacao = new Avaliacao();
        this.dataLancamento = dataLancamento;
        this.id = id;
    }

    public Catalogo(int id, String nome, String dataLancamento, String genero, String idioma) {
        this.nome = nome;
        this.genero = genero; // Ainda não será implementado
        this.idioma = idioma; // Ainda não será implementado
        this.audiencia = 0;
        this.avaliacao = new Avaliacao();
        this.dataLancamento = dataLancamento;
        this.id = id;
    }

    /**
     * Registra audiencia daquele conteudo audiovisual
     */
    public void registrarAudiencia() {
        this.audiencia += 1;
    }

    /**
     * @return audiencia do conteudo
     */
    public int getAudiencia() {
        return this.audiencia;
    }

    public void avaliar(int avaliacao, Cliente cliente) {
        this.avaliacao.avaliar(avaliacao, cliente);
    }

    /**
     * @return genero do conteudo
     */
    public String getGenero() {
        return this.genero;
    }

    /**
     * @return idioma do conteudo
     */
    public String getIdioma() {
        return this.idioma;
    }

    /**
     * @return nome do conteudo
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * @return id do conteudo
     */
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.nome + ";" + this.dataLancamento + ";" + this.idioma + ";" + this.genero;
    }

}
