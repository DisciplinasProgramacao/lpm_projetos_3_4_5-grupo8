import java.math.BigDecimal;
import java.util.LinkedList;

public abstract class Catalogo {

    //Atributos
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

    //Construtor
    public Catalogo(int id, String nome, String dataLancamento) {
        if (id > parseId) {
            parseId = id;
        }
        init(id, nome, dataLancamento, null, null);
    }

    public Catalogo(String nome, String dataLancamento, String genero, String idioma) {
        init(++parseId, nome, dataLancamento, genero, idioma);
    }

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

    /**
     * @return id do conteudo
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Registra audiencia daquele conteudo audiovisual
     */
    public void registrarAudiencia() {
        this.audiencia += 1;
    }

    /**
     * Adiciona a avaliação a mídia
     * @param avaliacao
     */
    public void avaliarMidia(Avaliacao avaliacao) {
        this.listaAvaliacoes.add(avaliacao);
    }

    /**
     * Metodo que retorna a media de avaliacoes de uma midia
     * @return
     */
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

    public String mostrarAvaliacoes(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("-----AVALIACOES-----");
        if(listaAvaliacoes.size() == 0){
            stringBuilder.append("\nAinda nao existem avaliacoes para essa midia");
            stringBuilder.append("\n--------------------");
        } else {
            for(Avaliacao avaliacao : listaAvaliacoes){
                stringBuilder.append(avaliacao.toString());
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return this.id + ";" + this.nome + ";" + this.dataLancamento;
    }
}
