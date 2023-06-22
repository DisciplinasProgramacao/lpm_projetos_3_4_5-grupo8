import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;

public abstract class Catalogo {

    //#region ATRIBUTOS
    private static int parseId;
    private String nome;
    private int id;
    private EnumGeneros genero;
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
    public Catalogo(int id, String nome, String dataLancamento, String genero, String idioma, boolean Lancamento) {
        if (id > parseId) {
            parseId = id;
        }
        init(id, nome, dataLancamento, genero, idioma, Lancamento);
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
    public Catalogo(String nome, String dataLancamento, String genero, String idioma, Boolean lancamento) throws IllegalArgumentException {
        String generoEncontrado = EnumGeneros.valueOf(genero).getDescricao();

        init(++parseId, nome, dataLancamento, generoEncontrado, idioma, lancamento);
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
    public void init(int id, String nome, String dataLancamento, String genero, String idioma, boolean lancamento) {
        this.genero = EnumGeneros.valueOf(genero.toUpperCase());
        this.nome = nome;
        this.idioma = idioma;
        this.audiencia = 0;
        this.dataLancamento = dataLancamento;
        this.avaliacaoMedia = new BigDecimal(0.0);
        this.listaAvaliacoes = new LinkedList<Avaliacao>();
        this.id = id;
        this.lancamento = lancamento;
    }
    //#endregion

    /**
     * Método que retorna a audiência de uma mídia
     * 
     * @return audiencia da midia (int)
     */
    public int getAudiencia() {
        return this.audiencia;
    }

    /**
     * Método que retorna o gênero da mídia
     * 
     * @return genero da midia (string)
     */
    public String getGenero() {
        return this.genero.getDescricao();
    }

    /**
     * Método que retorna idioma da mídia
     * 
     * @return idioma (string)
     */
    public String getIdioma() {
        return this.idioma;
    }

    /**
     * Método que retorna o nome da mídia
     * 
     * @return nome (string) 
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Método que retorna o id da mídia
     * 
     * @return id (int)
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Método que incrementa a audiência de uma midia assistida
     */
    public void registrarAudiencia() {
        this.audiencia += 1;
    }

    /********************************************Conferir esse metodo null q o prof disse****************************
     * Método que realiza a avaliação de uma mídia
     * 
     * @param avaliacao (Avaliacao)
     * @return avaliação adicionada
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
     * Metodo que calcula a media de avaliacoes de uma midia e retorna seu valor.
     * 
     * @return média de avaliação (BigDecimal)
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

    /**
     * Método que retorna a quantidade de avaliações de uma mídia
     * @return quantidade de avaliações (int)
     */
    public int quantidadeAvaliacoes() {
        return listaAvaliacoes.size();
    }

    /**
     * Método que mostrando todas as avaliações de uma mídia.
     * Caso a midia nao possua avaliação é retornado uma mensagem informando que não existem avaliações para a mídia
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
     * Metodo que formata uma string com os dados do catálogo
     * 
     * @return String formatada
     * 
     */
    @Override
    public String toString() {
        return this.id + ";" + this.nome + ";" + this.dataLancamento;
    }
}
