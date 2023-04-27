public abstract class Catalogo {

    private static final String[] GENEROS = new String[8];
    private String nome;
    private int id;
    private String genero;
    private String idioma;
    private int audiencia;
    private String dataLancamento;

    public Catalogo(int id, String nome,String dataLancamento) {
        this.nome = nome;
        this.audiencia = 0;
        this.dataLancamento = dataLancamento;
        this.id = id;
    }

    public Catalogo( int id, String nome,String dataLancamento, String genero, String idioma) {
        this.nome = nome;
        this.genero = genero; //Ainda não será implementado
        this.idioma = idioma; //Ainda não será implementado
        this.audiencia = 0;
        this.dataLancamento = dataLancamento;
        this.id = id;
    }

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

    public String getNome() {
        return this.nome;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.nome + ";" + this.dataLancamento + ";" + this.idioma + ";" + this.genero;
    }

}
