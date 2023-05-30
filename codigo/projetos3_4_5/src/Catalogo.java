import java.math.BigDecimal;
import java.util.LinkedList;

public abstract class Catalogo {

    private static final String[] GENEROS = new String[8];
    private static int parseId;
    private String nome;
    private int id;
    private String genero;
    private String idioma;
    private int audiencia;
    private LinkedList<Avaliacao> listaAvaliacoes;
    private String dataLancamento;
    private BigDecimal avaliacaoMedia;

    static {
        parseId = 0;
    }

    public Catalogo(int id, String nome, String dataLancamento) {
        if (id > parseId) {
            parseId = id;
        }
        init(id, nome, dataLancamento, null, null);
    }

    public Catalogo(String nome, String dataLancamento, String genero, String idioma) {
        init(++parseId, nome, dataLancamento, genero, idioma);
    }

    /**
     * @param nome
     * @param dataLancamento
     * @param genero
     * @param idioma
     */
    public void init(int id, String nome, String dataLancamento, String genero, String idioma) {
        this.nome = nome;
        this.genero = genero; // Ainda não será implementado
        this.idioma = idioma; // Ainda não será implementado
        this.audiencia = 0;
        this.dataLancamento = dataLancamento;
        this.avaliacaoMedia = new BigDecimal(0.0);
        this.listaAvaliacoes = new LinkedList<Avaliacao>();
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

    public void avaliarMidia(Avaliacao avaliacao) {
        this.listaAvaliacoes.add(avaliacao);
    }

    public BigDecimal mediaAvaliacao() {
        int somaNotas = 0;
        BigDecimal quantidadeAvaliacoes = new BigDecimal(listaAvaliacoes.size());
        BigDecimal mediaAvaliacoes = new BigDecimal(0.0);

        if(quantidadeAvaliacoes.compareTo(BigDecimal.ZERO) > 0){
            for (Avaliacao avaliacao : listaAvaliacoes) {
                somaNotas += avaliacao.getNota();
            }
    
            mediaAvaliacoes = new BigDecimal(somaNotas).divide(quantidadeAvaliacoes);
        }
        

        this.avaliacaoMedia = mediaAvaliacoes;

        return mediaAvaliacoes;
    }

    /**
     * @return id do conteudo
     */
    public int getId() {
        return this.id;
    }

    public int getMesVisto() {
        String[] aux = dataLancamento.split("/");
        return Integer.parseInt(aux[1]);
    }

    @Override
    public String toString() {
        return this.id + ";" + this.nome + ";" + this.dataLancamento;
        /* + "\nAvaliação Média: " + this.avaliacaoMedia */
    }

}
