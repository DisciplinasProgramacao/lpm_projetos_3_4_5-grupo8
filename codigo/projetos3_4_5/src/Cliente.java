import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.HashSet;

public class Cliente {

    // #region ATRIBUTOS
    private String nomeDeUsuario;
    private String senha;
    private String login;
    private LinkedList<Catalogo> listaParaVer;
    private HashSet<Catalogo> listaDeMidiasAvaliadas;
    private LinkedList<Assistido> listaJaVistas;
    private LinkedList<Avaliacao> listaDeAvaliacoes;

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
        listaDeMidiasAvaliadas = new HashSet<Catalogo>();
        this.listaDeAvaliacoes = new LinkedList<Avaliacao>();
    }
    // #endregion

    /**
     * Retorna a senha do usuário
     * 
     * @return senha do usuario
     * 
     */
    public String getSenha() {
        return this.senha;
    }

    /**
     * Retorna o nome do usuário
     * 
     * @return nome do usuario
     * 
     */
    public String getNomeUsuario() {
        return this.nomeDeUsuario;
    }

    /**
     * Retorna uma linked list com todos os catálogos adicionados na lista para ver
     * do cliente
     * 
     * @return lista para ver
     */
    public LinkedList<Catalogo> getListaParaVer() {
        return this.listaParaVer;
    }

    /**
     * Retorna o login do usuário
     * 
     * @return login do usuario
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Retorna uma linked list com todos as mídias que o cliente já assistiu
     * 
     * @return lista já vistas
     * 
     */
    public LinkedList<Assistido> getListaJaVistas() {
        return this.listaJaVistas;
    }

    /**
     * Retorna uma linked list com todas as avaliações que o cliente já fez
     * 
     * @return lista de avaliações
     * 
     */
    public LinkedList<Avaliacao> getListaDeAvaliacoes() {
        return this.listaDeAvaliacoes;
    }

    public int quantidadeDeAvaliacoes() {
        return listaDeAvaliacoes.size();
    }

    /**
     * Metodo que escolhe um catalogo da lista para ver e retorna o mesmo
     * 
     * @param posicao
     * @return catálogo escolhido
     * 
     */
    public Catalogo escolherCatalogo(int posicao) {
        return this.listaParaVer.get(posicao);
    }

    /**
     * Adiciona uma serie para ser assistida na lista Para ver
     * 
     * @param midia para adicionar na lista
     * 
     */
    public void adicionarNaLista(Catalogo midia) {
        listaParaVer.add(midia);
    }

    /**
     * Remove uma serie da lista Para ver
     * 
     * @param nomeMidia nome da midia para retirar
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
     * Filtra as series da lista Para ver de acordo com o genero
     * 
     * @param genero
     * @return lista de serie encontradas com o gênero sendo passado
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
     * Filtra as series da lista Para ver de acordo com o idioma
     * 
     * @param idioma
     * @return lista de serie pelo idioma requisitado
     * 
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
     * Filtra as series da lista Para ver de acordo com a quantidade de episodios
     * 
     * @param qtdEpisodios
     * 
     * @return lista de de serie pela quantidade de episodios requisitada
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
     * Registra a audiencia de uma serie, adiciona na lista de ja vistas e remove da
     * lista para ver
     * 
     * @param midia a ser registrada audiencia
     */
    public void registrarAudiencia(Catalogo midia) {
        LocalDate hoje = LocalDate.now();
        Assistido assistido = new Assistido(midia, hoje);
        midia.registrarAudiencia();
        listaJaVistas.add(assistido);
        listaParaVer.remove(midia);
    }

    /**
     * Metodo que verifica se o cleinte eh especialista, retorna true caso seja e
     * false caso não seja
     * 
     * @return true para cliente especialista e false caso contrario
     * 
     */
    public boolean ehEspecialista() {
        LocalDate hoje = LocalDate.now();
        return listaJaVistas.stream()
                .filter(x -> x.getData().until(hoje, ChronoUnit.DAYS) <= 30).count() >= 5;
    }

    /**
     * Metodo que verifica se o cliente ja avaliou a midia, retorna true caso já
     * tenha avaliado e false caso seja a primeira ver que ele esteja avaliando
     * 
     * @param catalogo
     * @return true caso cliente já tenha avaliado a mídia e false caso contrário
     * 
     */
    private boolean clienteJaAvaliouMidia(Catalogo catalogo) {
        if (listaDeMidiasAvaliadas.add(catalogo)) {
            return false;
        }
        return true;
    }

    /**
     * Metodo que avalia uma midia com nota e comentario e retorna a avaliação
     * efetuada
     * 
     * @param nota
     * @param comentario
     * @return Avaliação efetuada
     */
    private Avaliacao avaliar(int nota, String comentario) {
        if (ehEspecialista() && !comentario.isEmpty()) {
            return new Avaliacao(login, nota, comentario);
        }

        return new Avaliacao(login, nota);
    }

    /**
     * Metodo que adiciona um comentario a uma avaliacao existente
     * 
     * @param avaliacao
     * @param comentario
     */
    public void adicionarComentarioAvaliacaoExistente(Avaliacao avaliacao, String comentario) {
        if (avaliacao != null && (ehEspecialista() && !comentario.isEmpty())) {
            avaliacao.adicionarComentario(comentario);
        }
    }

    /**
     * Metodo que adiciona avaliacao em uma midia e retorna a avaliação adicionada
     * 
     * @param nota
     * @param comentario
     * @param catalogo
     * @return Avaliação adicionada
     * 
     */
    public Avaliacao adicionarAvaliacao(int nota, String comentario, Catalogo catalogo) {
        if (!clienteJaAvaliouMidia(catalogo)) {
            Avaliacao avaliacaoSendoFeita = avaliar(nota, comentario);
            Avaliacao avaliacaoFeita;
            for (Assistido assistido : listaJaVistas) {
                if (assistido.getCatalogo() == catalogo) {
                    avaliacaoFeita = assistido.adicionarAvaliacao(avaliacaoSendoFeita);
                    listaDeAvaliacoes.add(avaliacaoSendoFeita);
                    return avaliacaoFeita;
                }
            }
        }
        return null;
    }

    /**
     * Metodo que lista todas as midias assistidas
     * 
     * @return string formatada com todas as midias assistidas
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
     * @return string formatadas com todas as midias para serem assistidas
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
     * Busca mídia pelo nome na lista para ver, retorna a mídia caso seja encontrada
     * e null caso contrário
     * 
     * @param nomeMidia
     * @return mídia encontrada e false caso contrário
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
     * Retorna o hashCode do usuario
     * 
     * @return hashCode do usuario
     */
    @Override
    public int hashCode() {
        return this.nomeDeUsuario.hashCode();
    }

    /**
     * Retorna string formatada com dados do cliente
     *
     * @return string formatada
     */
    @Override
    public String toString() {
        return this.nomeDeUsuario + ";" + this.login + ";" + this.senha;
    }
}
