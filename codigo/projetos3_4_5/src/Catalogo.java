public class Catalogo {

    private static final String[] GENEROS = new String[8];
    private String nome;
    private int id;
    private String genero;
    private String idioma;
    private int audiencia;
    private String dataLancamento;

    public Catalogo(String nome, String genero, String idioma) {
        this.nome = nome;
        this.genero = genero;
        this.idioma = idioma;
        this.audiencia = 0;
    }
    

    @Override
    public String toString() {
        return this.id + ";" + this.nome + ";" + this.dataLancamento;
    }
}
