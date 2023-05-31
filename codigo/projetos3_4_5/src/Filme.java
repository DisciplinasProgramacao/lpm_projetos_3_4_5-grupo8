public class Filme extends Catalogo {

    //ATRIBUTOS
    private int duracao;

    //#region CONSTRUTORES

    /**
     * Construtor de filme que recebe id, nome, data em que foi lançado e a duração
     * @param id
     * @param nome
     * @param dataLancamento
     * @param duracao
     * 
     */
    public Filme(int id, String nome, String dataLancamento, int duracao) {
        super(id, nome, dataLancamento);
        this.duracao = duracao;
    }

    /**
     * Construtor de filme que recebe id, nome, data em que foi lançado, a duração e o genero
     * 
     * @param nome
     * @param dataLancamento
     * @param genero
     * @param idioma
     * @param duracao
     * 
     */
    public Filme(String nome, String dataLancamento, String genero, String idioma, int duracao) {
        super(nome, dataLancamento, genero, idioma);
        this.duracao = duracao;
    }
    //#endregion

    /**
     * Retorna a duração do film
     * 
     * @return inteiro com a duracao do filme
     */
    public int getDuracao() {
        return this.duracao;
    }

    /**
     * Metodo que registra a quantidade de pessoas que assistiram a série
     */
    public void registrarAudiencia() {
        super.registrarAudiencia();
    }

    /**
     * Retorna string formatada com os dados do filme e sua duração
     * 
     * @return string formatada
     * 
     */
    @Override
    public String toString() {
        return super.toString() + ";" + this.duracao;
    }

}
