import java.math.BigDecimal;
import java.util.LinkedList;

public abstract class Catalogo {

    //#region ATRIBUTOS
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
    //#endregion

    //#region CONSTRUTOR

    /**
     * Construtor que cria um catálogo apenas com id, nome e data em que foi lançado
     * @param id
     * @param nome
     * @param dataLancamento
     */
    public Catalogo(int id, String nome, String dataLancamento) {
        if (id > parseId) {
            parseId = id;
        }
        init(id, nome, dataLancamento, null, null);
    }

    /**
     * Construtor que cria um catálogo com nome, data em que foi lançado, genero e idioma
     * 
     * @param nome
     * @param dataLancamento
     * @param genero
     * @param idioma
     */
    public Catalogo(String nome, String dataLancamento, String genero, String idioma) {
        init(++parseId, nome, dataLancamento, genero, idioma);
    }

    /**
     * Método inicializador para ser utilizado pelos construtores
     * 
     * @param id
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
    //#endregion

    /**
     * Obtém a audiência da mídia
     * 
     * @return audiencia do conteudo
     * 
     */
    public int getAudiencia() {
        return this.audiencia;
    }

    /**
     * Obtém string do gênero da mídia
     * 
     * @return genero do conteudo
     */
    public String getGenero() {
        return this.genero;
    }

    /**
     * Obtém o idioma da mídia
     * 
     * @return idioma do conteudo
     */
    public String getIdioma() {
        return this.idioma;
    }

    /**
     * Obtém o nome da mídia
     * 
     * @return string com o nome da mídia
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Obtém o id da mídia
     * 
     * @return id do conteudo
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Incrementa audiência da midia
     */
    public void registrarAudiencia() {
        this.audiencia += 1;
    }

    /**
     * Adiciona a avaliação a mídia
     * 
     * @param avaliacao a ser armazenada
     * 
     */
    public void avaliarMidia(Avaliacao avaliacao) {
        this.listaAvaliacoes.add(avaliacao);
    }

    /**
     * Metodo que retorna a media de avaliacoes de uma midia
     * 
     * @return avaliação média
     * 
     */
    public BigDecimal mediaAvaliacao() {
        int somaNotas = 0;
        BigDecimal quantidadeAvaliacoes = new BigDecimal(quantidadeAvaliacoes());
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

    public int quantidadeAvaliacoes() {
        return listaAvaliacoes.size();
    }

    /**
     * Cria uma string com todas as avaliações da mídia ou com mensagem caso mídia não possua avaliações
     * 
     * @return String formatada de avaliações
     * 
     */
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

    /**
     * Metodo que retorna uma string formatada com os dados do catálogo
     * 
     * @return String formatada
     * 
     */
    @Override
    public String toString() {
        return this.id + ";" + this.nome + ";" + this.dataLancamento;
    }
}
