import java.util.LinkedList;

public class Cliente {

    // Atributos
    private String nomeDeUsuario;
    private String senha;
    private String login;
    private LinkedList<Catalogo> listaParaVer;
    private LinkedList<Catalogo> listaJaVistas;
    private Catalogo serie;

    // Construtor
    public Cliente(String nomeDeUsuario, String login, String senha) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.login = login;
        this.listaParaVer = new LinkedList<Catalogo>();
        this.listaJaVistas = new LinkedList<Catalogo>();
    }

    // Metodos 
    //QUE MÉTODO É ESSE?
    // public Serie getSerie() {
    //     return this.serie;
    // }

    public String getSenha() {
        return this.senha;
    }

    public void CatalogoJaVisto(){
        for (Catalogo catalogo : this.listaJaVistas){
            catalogo.toString();
        }
    }

    public Catalogo EscolherCatalogo(int posicao){
        return this.listaParaVer.get(posicao);
    }

    public String getNomeUsuario() {
        return this.nomeDeUsuario;
    }

    public LinkedList<Catalogo> getListaParaVer() {
        return this.listaParaVer;
    }

    public String getLogin() {
        return this.login;
    }

    /*
     * Adiciona uma serie para ser assistida na lista Para ver
     * 
     * @param serie
     */
    public void adicionarNaLista(Catalogo serie) {
        listaParaVer.add(serie);
    }

    /*
     * Remove uma serie da lista Para ver
     * 
     * @param nomeSerie
     */
    public void retirarDaLista(String nomeMidia) {
        Catalogo midiaAhSerRemovida = null;
        for (Catalogo catalogo : listaParaVer) {
            if (catalogo.getNome().equals(nomeMidia)) {
                midiaAhSerRemovida = serie;
                break; //olhar melhor forma de fazer isso sem precisar do break
            }
        }
        listaParaVer.remove(midiaAhSerRemovida);
    }

    /**
     * Retorna, em um vetor/array, todos os elementos da lista. O vetor passado como
     * parâmetro deve ser criado previamente.
     * O retorno contém os elementos da lista.
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
    public LinkedList<Catalogo> filtrarPorGenero(String genero) {
        LinkedList<Catalogo> listaGenero = new LinkedList<Catalogo>();

        for (Catalogo serie : listaParaVer) {
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
    public LinkedList<Catalogo> filtrarPorIdioma(String idioma) {
        LinkedList<Catalogo> listaIdioma = new LinkedList<Catalogo>();

        for (Catalogo serie : listaParaVer) {
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
    // public LinkedList<Serie> filtrarPorQtdEpisodios(int qtdEpisodios) {
    //     LinkedList<Serie> listaqtdEpisodios = new LinkedList<Serie>();

    //     for (Serie serie : listaParaVer) {
    //         if (serie.getEpisodios() == qtdEpisodios) {
    //             listaqtdEpisodios.add(serie);
    //         }
    //     }

    //     return listaqtdEpisodios;
    // }

    /*
     * Registra a audiencia de uma serie e a adiciona na lista de ja vistas
     */
    public void registrarAudiencia(Catalogo serie) {
        listaJaVistas.add(serie);
        listaParaVer.remove(serie);
    }

    @Override
    public int hashCode(){
        return this.nomeDeUsuario.hashCode();
    }
    @Override
    public String toString() {
        return this.nomeDeUsuario + ";" + this.login + ";" + this.senha;
    }
}
