public class Serie extends Catalogo {

    //ATRIBUTOS
    private int quantidadeEpisodios;

    //#region

    /**
     * Construtor de série que cria série com nome, data de lançamento, genero, idioma e a quantidade de episódios
     * 
     * @param nome
     * @param dataLancamento
     * @param genero
     * @param idioma
     * @param quantidadeEpisodios
     * 
     */
    public Serie(String nome, String dataLancamento, String genero, String idioma, int quantidadeEpisodios, boolean lancamento) {
        super(nome, dataLancamento, genero, idioma, lancamento);
        this.quantidadeEpisodios = quantidadeEpisodios < 2 ? 0 : quantidadeEpisodios;
    }

    /**
     * Construtor de série que cria série com id, nome e data de lançamento
     * 
     * @param idSerie
     * @param nome
     * @param dataLancamento
     * @param genero
     * @param idioma
     */
    public Serie(int idSerie, String nome, String dataLancamento, String genero, String idioma, boolean lancamento) {
        super(idSerie, nome, dataLancamento, genero, idioma, lancamento);
    }
    //#endregion

    /**
     * Metodo que registra a quantidade de pessoas que assistiram a série
     */
    public void registrarAudiencia() {
        super.registrarAudiencia();
    }

    /**
     * Método que retorna a quantidade de episódios da série
     * 
     * @return quantidade de episodios que a serie possui (int)
     */
    public int getEpisodios() {
        return this.quantidadeEpisodios;
    }
}
