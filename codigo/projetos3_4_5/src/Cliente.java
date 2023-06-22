import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;

public class Cliente {

    // #region ATRIBUTOS
    private String nomeDeUsuario;
    private String senha;
    private String login;
    private LinkedList<Catalogo> listaParaVer;
    private LinkedList<Assistido> listaJaVistas;
    private LinkedList<Avaliacao> listaDeAvaliacoes;
    private State estadoCliente;

    // #endregion

    // #region CONSTRUTOR
    /**
     * Cria um cliente recebendo o nome de usuário, login e senha
     * 
     * @param nomeDeUsuario
     * @param login
     * @param senha
     * 
     */
    public Cliente(String nomeDeUsuario, String login, String senha) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.login = login;
        this.listaParaVer = new LinkedList<Catalogo>();
        this.listaJaVistas = new LinkedList<Assistido>();
        this.listaDeAvaliacoes = new LinkedList<Avaliacao>();
        this.estadoCliente = new StateStandart();
    }
    // #endregion

    /**
     * Metodo que retorna a senha do usuário
     * 
     * @return senha do usuario (string)
     * 
     */
    public String getSenha() {
        return this.senha;
    }

    /**
     * Metodo que retorna o nome do usuário
     * 
     * @return nome do usuario (string)
     * 
     */
    public String getNomeUsuario() {
        return this.nomeDeUsuario;
    }

    /**
     * Metodo que retorna  uma lista com todos os catálogos adicionados na lista para ver do cliente
     * 
     * @return lista para ver (LinkedList)
     */
    public LinkedList<Catalogo> getListaParaVer() {
        return this.listaParaVer;
    }

    /**
     * Metodo que retorna  o login do usuário
     * 
     * @return login do usuario
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Metodo que retorna uma lista com todos as mídias assistidas pelo cliente
     * 
     * @return lista já vistas (LinkedList)
     */
    public LinkedList<Assistido> getListaJaVistas() {
        return this.listaJaVistas;
    }

    /**
     * Metodo que retorna uma lista com todas as avaliações que o cliente já fez
     * 
     * @return lista de avaliações (LinkedList)
     */
    public LinkedList<Avaliacao> getListaDeAvaliacoes() {
        return this.listaDeAvaliacoes;
    }

    public int quantidadeDeAvaliacoes() {
        return listaDeAvaliacoes.size();
    }

    /**
     * Metodo que escolhe um catalogo da lista para ver e retorna a midia escolhida
     * 
     * @param posicao (int)
     * @return catálogo escolhido (Catalogo)

     */
    public Catalogo escolherCatalogo(int posicao) {
        return this.listaParaVer.get(posicao);
    }

    /**
     * Metodo que adiciona uma midia na lista para ver futuramente
     * 
     * @param midia para adicionar na lista (Catalogo)
     */
    public void adicionarNaLista(Catalogo midia) {
        listaParaVer.add(midia);
    }

    /**
     * Metodo que recebe uma midia e realiza a remoção dela na lista Para ver.
     * Pois caso o cliente já tenha assistido a midia, ela é removida.
     * 
     * @param nomeMidia (string)
     * 
     */
    public void retirarDaLista(String nomeMidia) {
        Catalogo midiaAhSerRemovida = null;
        for (Catalogo catalogo : listaParaVer) {
            if (catalogo == null) {
                break;
            } else if (catalogo.getNome().equals(nomeMidia)) {
                midiaAhSerRemovida = catalogo;
                break; // olhar melhor forma de fazer isso sem precisar do break
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
     * Método que realiza a filtragem de um catalogo na lista para ver, de acordo com o gênero fornecido.
     * 
     * @param genero (string)
     * @return lista de midias filtrado pelo gênero  (LinkedList<Catalogo>)
     */
    public LinkedList<Catalogo> filtrarPorGenero(String genero) {
        LinkedList<Catalogo> listaGenero = new LinkedList<Catalogo>();

        for (Catalogo catalogo : listaParaVer) {
            if (catalogo.getGenero().equals(genero)) {
                listaGenero.add(catalogo);
            }
        }
        for (Assistido catalogo : listaJaVistas) {
            if (catalogo.getCatalogo().getGenero().equals(genero)) {
                listaGenero.add(catalogo.getCatalogo());
            }
        }
        return listaGenero;
    }

    /**
     * Método que realiza a filtragem de um catalogo na lista para ver, de acordo com o idioma fornecido.
     * 
     * @param idioma (string)
     * @return lista de midias filtrada pelo idioma (LinkedList<Catalogo>)
     */
    public LinkedList<Catalogo> filtrarPorIdioma(String idioma) {
        LinkedList<Catalogo> listaIdioma = new LinkedList<Catalogo>();

        for (Catalogo catalogo : listaParaVer) {
            if (catalogo.getIdioma().equals(idioma)) {
                listaIdioma.add(catalogo);
            }
        }
        for (Assistido catalogo : listaJaVistas) {
            if (catalogo.getCatalogo().getIdioma().equals(idioma)) {
                listaIdioma.add(catalogo.getCatalogo());
            }
        }
        return listaIdioma;
    }

    /**
     * Método que realiza a filtragem de series na lista para ver, de acordo com a quantidade de episodios fornecidos.
     * 
     * @param quantidade de episodios (int)
     * 
     * @return lista de serie filtrada pela qtd de episodios (LinkedList<Catalogo>)
     * 
     */
    public LinkedList<Catalogo> filtrarPorQtdEpisodios(int qtdEpisodios) {
        LinkedList<Catalogo> listaqtdEpisodios = new LinkedList<Catalogo>();
        Serie filtrada = null;
        for (Catalogo serie : listaParaVer) {
            if (serie instanceof Serie) {
                filtrada = (Serie) serie;
                if (filtrada.getEpisodios() == qtdEpisodios) {
                    listaqtdEpisodios.add(serie);
                }
            }
        }
        for (Assistido serie : listaJaVistas) {
            if (serie.getCatalogo() instanceof Serie) {
                filtrada = (Serie) serie.getCatalogo();
                if (filtrada.getEpisodios() == qtdEpisodios) {
                    listaqtdEpisodios.add(serie.getCatalogo());
                }
            }
        }
        return listaqtdEpisodios;
    }

    /**
     * Método que registra a audiencia de uma midia. 
     * Apartir da midia assistida, é realizado o registro da audiencia daquela midia. Em seguida, é feita a remoção da midia na 'lista para ver', e adicionada na lista de 'ja assisitidos'.
     * 
     * @param midia a ser registrada audiencia (Catalogo)
     */
    public void registrarAudiencia(Catalogo midia) {
        LocalDate hoje = LocalDate.now();
        Assistido assistido = new Assistido(midia, hoje);
        midia.registrarAudiencia();
        listaJaVistas.add(assistido);
        listaParaVer.remove(midia);
    }

    /**
     * Metodo que verifica se o cliente pode comentar na avaliacao
     * Ele só poderá comentar se for um cliente for um cliente especialista ou profissional
     * @return TRUE (caso possa comentar) ou FALSE (caso nao possa)
     * 
     */
    public boolean podeComentar() {
        return this.getEstadoCliente().podeComentar();
    }

    /**
     * Metodo que verifica se o cliente é profissional para poder assistir uma midia de lançamento
     * 
     * @return TRUE caso possa assistir
     * 
     */
    public boolean podeVerLancamento() {
        return this.getEstadoCliente().podeAssistirLancamento();
    }

    /**
     * Metodo que avalia uma midia com nota e comentario. Em seguida retorna a avaliação
     * efetuada
     * 
     * @param nota (int)
     * @param comentario (string)
     * @return Avaliação efetuada (Avaliacao)
     */
    private Avaliacao avaliar(int nota, String comentario) {
        if (podeComentar() && !comentario.isEmpty()) {
            return new Avaliacao(login, nota, comentario);
        }

        return new Avaliacao(login, nota);
    }

    /**
     * Metodo que adiciona um comentario em uma avaliacao existente
     * 
     * @param avaliacao (Avaliacao)
     * @param comentario (string)
     */
    public void adicionarComentarioAvaliacaoExistente(Avaliacao avaliacao, String comentario) {
        if (avaliacao != null && (podeComentar() && !comentario.isEmpty())) {
            avaliacao.adicionarComentario(comentario);
        }
    }

    /**
     * Metodo que adiciona uma avaliacao em uma midia e retorna a avaliação feita
     * 
     * @param nota (int)
     * @param comentario (string)
     * @param catalogo (Catalogo)
     * @return Avaliação adicionada (Avaliacao)
     */
    public Avaliacao adicionarAvaliacao(int nota, String comentario, Catalogo catalogo) {
        Avaliacao avaliacaoSendoFeita = avaliar(nota, comentario);
        Avaliacao avaliacaoFeita;

        avaliacaoFeita = listaJaVistas.stream()
                            .filter(assistido -> assistido.getCatalogo() == catalogo)
                            .findFirst()
                            .map(assistido -> assistido.adicionarAvaliacao(avaliacaoSendoFeita))
                            .orElse(null);

        if(avaliacaoFeita != null){
            listaDeAvaliacoes.add(avaliacaoSendoFeita);
        }

        return avaliacaoFeita;
    }

    /**
     * Metodo que retorna uma lista com todas as midias assistidas
     * 
     * @return string formatada com todas as midias assistidas (string)
     */
    public String listarMidiasAssistidas() {
        String midias = "";
        for (Assistido assistido : listaJaVistas) {
            midias += assistido.toString();
        }

        return midias;
    }

    /**
     * Metodo que lista todas as midias para serem assistidas futuramente
     * 
     * @return string formatadas com todas as midias para serem assistidas (string)
     * 
     */
    public String listarMidiasParaSeremAssistidas() {
        String midias = "";
        for (Catalogo midiaParaSerVista : listaParaVer) {
            midias += midiaParaSerVista.toString();
        }

        return midias;
    }

    /**
     * Método que realiza uma pesquisa para encontrar uma mídia pelo nome na lista para ver.
     * Caso encontre a mídia, retorna a mesma. Caso não, retorna null.
     * 
     * @param nome da midia (string)
     * @return mídia encontrada (Catalogo) e null caso contrário
     * 
     */
    public Catalogo buscarMidiaNaListaParaVer(String nomeMidia) {
        for (Catalogo catalogo : listaParaVer) {
            if (catalogo.getNome().equals(nomeMidia)) {
                return catalogo;
            }
        }
        return null;
    }

    /**
     * Método que retorna o hashCode de um usuario
     * 
     * @return hashCode do usuario (int)
     */
    @Override
    public int hashCode() {
        return this.nomeDeUsuario.hashCode();
    }

    /**
     * Método que retorna uma string formatada com dados do cliente como nome de usuario, login e senha
     *
     * @return string formatada
     */
    @Override
    public String toString() {
        return this.nomeDeUsuario + ";" + this.login + ";" + this.senha;
    }

    /**
     * Método que retorna o estado de um cliente: normal, especialista ou profissional
     * @param estadoCliente
     */
    private void changeState(State estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

     /**
     * Método que cria estado de um cliente como normal
     * 
     */
    public void criarStandart(){
        this.changeState(this.estadoCliente.tornarStandart());
    }

    /**
     * Metodo que gera o cliente como especialista. Ele verifica se o viu/avaliou pelo menos 5 mídias no mês passado
     */
    public void tornarEspecialista(){
        LocalDate hoje = LocalDate.now();
        if(this.getListaJaVistas().stream().filter(x -> x.getData().until(hoje, ChronoUnit.DAYS) <= 30).count() >= 5)
            this.changeState(this.estadoCliente.tornarEspecialista());
    }

    /**
     * Metodo que gera o cliente como profissional.
     */
    public void tornarProfissional(){
        this.changeState(this.estadoCliente.tornarProfissional());
    }

    
    /**
     * Metodo que retorna o estado de um cliente (normal-Standart, especialista ou profissional)
     * @return estado (State)
     */
    public State getEstadoCliente() {
        return this.estadoCliente;
    }

}
