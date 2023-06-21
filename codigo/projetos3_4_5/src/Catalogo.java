import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;

public abstract class Catalogo {

    //#region ATRIBUTOS
    private static int parseId;
    private String nome;
    private int id;
    private String genero;
    private String idioma;
    private int audiencia;
    private LinkedList<Avaliacao> listaAvaliacoes;
    private String dataLancamento;
    private BigDecimal avaliacaoMedia;
    public boolean lancamento;

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
     * @param genero
     * @param idioma
     */
    public Catalogo(int id, String nome, String dataLancamento, String genero, String idioma) {
        if (id > parseId) {
            parseId = id;
        }
        init(id, nome, dataLancamento, genero, idioma);
    }

    /**
     * Construtor que cria um catálogo com nome, data em que foi lançado, genero e idioma
     * Valida o genero que recebe como parâmetro, deve ser um dos valores definidos no enum, caso receba valor inválido lança exceção
     * 
     * @param nome
     * @param dataLancamento
     * @param genero
     * @param idioma
     * @throws IllegalArgumentException caso genero informado seja inválido
     */
    public Catalogo(String nome, String dataLancamento, String genero, String idioma) throws IllegalArgumentException {
        EnumGeneros generoEncontrado = Arrays.stream(EnumGeneros.values())
            .filter(g -> g.getDescricao().equals(genero))
            .findAny()
            .orElseThrow(() -> {throw new IllegalArgumentException("Genero " + genero + " nao esta dentro dos valores permitidos");});

        init(++parseId, nome, dataLancamento, generoEncontrado.getDescricao(), idioma);
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
        this.genero = genero;
        this.nome = nome;
        this.idioma = idioma;
        this.audiencia = 0;
        this.dataLancamento = dataLancamento;
        this.avaliacaoMedia = new BigDecimal(0.0);
        this.listaAvaliacoes = new LinkedList<Avaliacao>();
        this.id = id;
        this.lancamento = false;
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
    public Avaliacao avaliarMidia(Avaliacao avaliacao) {
        String loginCliente = this.listaAvaliacoes.stream()
                                                .filter(c -> c.getLoginCliente().equals(avaliacao.getLoginCliente()))
                                                .findFirst()
                                                .map(Avaliacao::getLoginCliente)
                                                .orElse(null);
        if(loginCliente == null){
            this.listaAvaliacoes.add(avaliacao);
            return avaliacao;
        }
        
        return null;
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
