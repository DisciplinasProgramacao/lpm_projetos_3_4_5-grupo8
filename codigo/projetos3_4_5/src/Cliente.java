import java.util.LinkedList;

public class Cliente {

    // Atributos
    private String nomeDeUsuario;
    private String senha;
    private LinkedList<Serie> listaParaVer;
    private LinkedList<Serie> listaJaVistas;
    private Serie serie;

    // Construtor
    public Cliente(String nomeDeUsuario, String senha) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.listaParaVer = new LinkedList<Serie>();
        this.listaJaVistas = new LinkedList<Serie>();
    }

    // Metodos
    public Serie getSerie() {
        return this.serie;
    }

    public String getSenha() {
        return this.senha;
    }

    public String getNomeUsuario() {
        return this.nomeDeUsuario;
    }

    public LinkedList getListaParaVer() {
        return this.listaParaVer;
    }

    /*
     * Adiciona uma serie para ser assistida na lista Para ver
     * 
     * @param serie
     */
    public void adicionarNaLista(Serie serie) {
        listaParaVer.add(serie);
    }

    /*
     * Remove uma serie da lista Para ver
     * 
     * @param nomeSerie
     */
    public void retirarDaLista(String nomeSerie) {
        for (Serie serie : listaParaVer) {
            if (serie.getNome().equals(nomeSerie)) {
                listaParaVer.remove(serie);
            }
        }
    }

    /**
     * Retorna, em um vetor/array, todos os elementos da lista. O vetor passado
     * como parâmetro deve ser criado previamente. O retorno contém os elementos da
     * lista.
     * 
     * @param array Vetor/array para abrigar os elementos da lista. Deve ser
     *              previamente criado.
     * @return Outro vetor/array com os elementos da lista.
     */
    public Serie[] allElements(Serie[] array) {
        Serie[] allData = this.listaParaVer.toArray(array);
        return allData;
    }

    /**
     * Filtra as series da lista Para ver de acordo com o genero
     * 
     * @param genero
     * @return lista de serie pelo genero requisitado
     */
    public LinkedList<Serie> filtrarPorGenero(String genero) {
        LinkedList<Serie> listaGenero = new LinkedList<Serie>();

        for (Serie serie : listaParaVer) {
            if (serie.getGenero().equals(genero)) {
                listaGenero.add(serie);
            }
        }

        return listaGenero;
    }

    /**
     * Filtra as series da lista Para ver de acordo com o idioma
     * 
     * @param idioma
     * @return lista de serie pelo idioma requisitado
     */
    public LinkedList<Serie> filtrarPorIdioma(String idioma) {
        LinkedList<Serie> listaIdioma = new LinkedList<Serie>();

        for (Serie serie : listaParaVer) {
            if (serie.getIdioma().equals(idioma)) {
                listaIdioma.add(serie);
            }
        }

        return listaIdioma;
    }

    /**
     * Filtra as series da lista Para ver de acordo com a quantidade de episodios
     * 
     * @param qtdEpisodios
     * @return lista de de serie pela quantidade de episodios requisitada
     */
    public LinkedList<Serie> filtrarPorQtdEpisodios(int qtdEpisodios) {
        LinkedList<Serie> listaqtdEpisodios = new LinkedList<Serie>();

        for (Serie serie : listaParaVer) {
            if (serie.getEpisodios() == qtdEpisodios) {
                listaqtdEpisodios.add(serie);
            }
        }

        return listaqtdEpisodios;
    }

    /*
     * Registra a audiencia de uma serie e a adiciona na lista de ja vistas
     */
    public void registrarAudiencia(Serie serie) {
        listaJaVistas.add(serie);
        listaParaVer.remove(serie);
    }

}
