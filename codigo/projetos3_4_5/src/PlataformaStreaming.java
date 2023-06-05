import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

public class PlataformaStreaming {
    //#region ATRIBUTOS
    private String nome;
    private HashMap<Integer, Catalogo> catalogos;
    private HashMap<String, Cliente> clientes;
    private Cliente clienteAtual;
    //#endregion

    //#region CONSTRUTOR
    /**
     * Construtor que cria plataforma de acordo com o nome recebido
     * 
     * @param nome
     * 
     */
    PlataformaStreaming(String nome) {
        this.nome = nome;
        this.catalogos = new HashMap<Integer, Catalogo>(250);
        this.clientes = new HashMap<String, Cliente>(51900);
        this.clienteAtual = null;
    }
    //#endregion

    /**
     * Retorna o nome da plataforma
     * 
     * @return string com o nome da plataforma
     * 
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Retorna o cliente atual, caso possua usuário logado e null pointer caso contrário
     * 
     * @return Cliente atual caso exista ou null pointer caso contrário
     * @throws NullPointerException
     * 
     */
    public Cliente getClienteAtual() throws NullPointerException {
        if (this.clienteAtual != null)
            return this.clienteAtual;
        throw new NullPointerException();
    }

    /**
     * Retorna string formatada com os dados do catálogo
     * 
     * @return String de catalogos
     * 
     */
    public String getCatalogo() {
        int cont = 1;
        StringBuilder str = new StringBuilder();
        for (int key : catalogos.keySet()) {
            str.append("[" + cont + "] ");
            str.append(catalogos.get(key).getNome());
            str.append("\n ");
            cont++;
        }
        return str.toString().substring(0, str.length() - 2);
    }

    /**
     * Realiza o login na plataforma, retorna cliente atual caso login efetuado do sucesso e null caso contrário
     * 
     * @param login login do usuario
     * @param senha senha do usuario
     * 
     * @return cliente se sucesso, cliente nulo caso erro
     */
    public Cliente login(String login, String senha) {
        Cliente cliente = clientes.get(login);

        if (cliente != null && cliente.getSenha().equals(senha)) {
            this.clienteAtual = cliente;
        } else {
            this.clienteAtual = null;
        }

        return this.clienteAtual;
    }

    /**
     * Realiza o logoff do cliente da plataforma, colocando o cliente atual como nulo
     * 
     */
    public void logoff() {
        this.clienteAtual = null;
    }

    /**
     * Adiciona catalogo na plataforma, lança exceção caso ocorra erro ao adicionar catálogo
     * 
     * @param catalogo catalogo a ser adicionado
     * @throws IOException
     * 
     */
    public void adicionarCatalogo(Catalogo catalogo) throws IOException {
        catalogos.put(catalogo.getId(), catalogo);
    }

    /**
     * Adiciona catalogos na plataforma, retorna FileNotFoundException caso não encontre o arquivo
     * 
     * @param catalogos linkedlist de catalogo a ser adicionado
     * @throws FileNotFoundException
     * 
     */
    public void carregarCatalogos() throws FileNotFoundException {
        Function<String, Filme> contrutorFilme = (str -> new Filme(Integer.parseInt(str.split(";")[0]),
                str.split(";")[1],
                str.split(";")[2],
                Integer.parseInt(str.split(";")[3])));

        Function<String, Serie> contrutorSerie = (str -> new Serie(Integer.parseInt(str.split(";")[0]),
                str.split(";")[1],
                str.split(";")[2]));

        LinkedList<Filme> filmes;
        LinkedList<Serie> series;
        filmes = Armazenagem.ler("POO_Filmes", contrutorFilme);
        series = Armazenagem.ler("POO_Series", contrutorSerie);
        this.catalogos.clear();
        for (Filme x : filmes) {
            this.catalogos.put(x.getId(), x);
        }
        for (Serie x : series) {
            this.catalogos.put(x.getId(), x);
        }
    }

    /**
     * adicionar cliente na plataforma, retorna IllegalArgumentException caso cliente já exista
     * 
     * @param cliente cliente a ser adicionado
     * @throws IOException
     * @throws IllegalArgumentException cliente ja existe
     * 
     */
    public void adicionarCliente(Cliente cliente) throws IOException, IllegalArgumentException {
        if (validarLoginCliente(cliente)) {
            this.clientes.put(cliente.getLogin(), cliente);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Adicionar cliente na plataforma, retorna FileNotFoundException caso não encontre o arquivo
     * 
     * @param clientes linkedlist de cliente a ser adicionado
     * @throws FileNotFoundException
     * 
     */
    public void carregarCliente() throws FileNotFoundException {
        Function<String, Cliente> contrutorCliente = (str -> new Cliente(str.split(";")[0], str.split(";")[1],
                str.split(";")[2]));

        LinkedList<Cliente> clientes;
        clientes = Armazenagem.ler("POO_Espectadores", contrutorCliente);
        this.clientes.clear();
        for (Cliente x : clientes) {
            this.clientes.put(x.getLogin(), x);
        }
        this.carregarAudiencia();
    }

    /**
     * Metodo que le um arquivo e atualiza a audiencia
     * 
     * @throws FileNotFoundException erro ao abrir o arquivo
     */
    public void carregarAudiencia() throws FileNotFoundException {
        File file = new File("./codigo/projetos3_4_5/arquivos/POO_Audiencia.csv");
        Scanner entrada = new Scanner(file, "UTF-8");
        String linha;
        String linhaAux[];
        Cliente clienteAux;
        Catalogo catalogoAux;
        while (entrada.hasNext()) {
            linha = entrada.nextLine();
            linhaAux = linha.split(";");
            clienteAux = clientes.get(linhaAux[0]);
            if (clienteAux != null) {
                catalogoAux = catalogos.get(Integer.parseInt(linhaAux[2]));
                if (linhaAux[1].equals("F")) {
                    clienteAux.adicionarNaLista(catalogoAux);
                } else {
                    clienteAux.registrarAudiencia(catalogoAux);
                }
            }
        }
        entrada.close();
    }

    /**
     * valida se o login do cliente existe, true caso exista e false caso contrário
     * 
     * @param cliente cliente a ser validado
     * @return true caso cliente exista e false caso contrário
     * 
     */
    private boolean validarLoginCliente(Cliente cliente) {
        for (String key : clientes.keySet()) {
            if (cliente.getLogin().equals(clientes.get(key).getLogin()))
                return false;
        }

        return true;
    }

    /**
     * filtra catalogo por String
     * 
     * @param filtro genero a ser filtrado
     * @return linked list de catálogos encontrados a partir do filtro
     * 
     */
    public LinkedList<Catalogo> filtrarCatalogo(String filtro) {
        LinkedList<Catalogo> filtrado = new LinkedList<Catalogo>();
        Catalogo midia;
        for (int key : catalogos.keySet()) {
            midia = catalogos.get(key);
            if (midia.getGenero().equals(filtro) || midia.getNome().equals(filtro) || midia.getIdioma().equals(filtro)) {
                filtrado.add(midia);
            }
        }
        return filtrado;
    }

    /**
    * filtra catalogo por quantidade de episodios
    *
    * @param quantEpisodios quantEpisodios a ser filtrado
    * @return linked list de catálogos encontrados a partir do filtro 
    *
    */
    public LinkedList<Catalogo> filtrarPorQtdEpisodios(int quantEpisodios) {
        LinkedList<Catalogo> filtro = new LinkedList<Catalogo>();
        Serie serie;
        for (int key : catalogos.keySet()) {
            serie = (Serie) catalogos.get(key);
            if (serie.getEpisodios() == quantEpisodios) {
                filtro.add(serie);
            }
        }
        return filtro;
    }

    /**
    * filtra catalogo por duracao em minutos
    *
    * @param duracao duracao de midia a ser filtrado
    * @return linked list de catálogos encontrados a partir do filtro 
    *
    */
    public LinkedList<Catalogo> filtrarPorDuracao(int duracao) {
        LinkedList<Catalogo> filtro = new LinkedList<Catalogo>();
        Filme filme;
        for (int key : catalogos.keySet()) {
            filme = (Filme) catalogos.get(key);
            if (filme.getDuracao() == duracao) {
                filtro.add(filme);
            }
        }
        return filtro;
    }

    /**
     * Buscar catalogo por nome
     * 
     * @param midia nome da midia a ser procurada
     * @throws NullPointerException      midia nao encontrada
     * @throws IndexOutOfBoundsException catalogo vazio
     * @return catalogo encontrado e NullPointerException caso não encontre
     */
    public Catalogo buscarCatalogo(String midia) throws NullPointerException, IndexOutOfBoundsException {

        for (int key : catalogos.keySet()) {
            if (catalogos.get(key).getNome().equals(midia)) {
                return catalogos.get(key);
            }
        }
        throw new NullPointerException();
    }

    /**
     * Metodo que adiciona midia na lista de 'Midias para assistir futuramente'
     * 
     * @param midia
     * @throws NullPointerException      midia nao encontrada
     * @throws IndexOutOfBoundsException catalogo vazio
     */
    public void adicionarMidiaNaListaParaVerFuturamente(String midia)
            throws NullPointerException, IndexOutOfBoundsException {
        Catalogo midiaPesquisada = buscarCatalogo(midia);
        this.clienteAtual.adicionarNaLista(midiaPesquisada);
    }

    // Metodo que verifica as midias assistidas pelo cliente
    public String visualizarListaDeAssistidos() {
        return (this.clienteAtual.listarMidiasAssistidas());
    }

    /**
     * Assistir midia
     * 
     * @param nome da midia a ser assistida
     * @throws NullPointerException      cliente invalido
     * @throws IndexOutOfBoundsException midia inexistente ou nao encontrada
     */
    public void assistirMidia(String nomeMidia) throws NullPointerException, IndexOutOfBoundsException {
        Catalogo catalogo = this.getClienteAtual().buscarMidiaNaListaParaVer(nomeMidia);
        if (catalogo != null) {
            this.clienteAtual.registrarAudiencia(catalogo);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Metodo que verifica as midias que o cliente quer ver futuramente 
     */
    public String visualizarListaParaVerFuturamente() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("-------------------");

        int contador = 0;

        for (Catalogo midia : this.clienteAtual.getListaParaVer()) {
            contador++;
            stringBuilder.append("\n[" + contador + "] " + midia.getNome());
        }

        stringBuilder.append("\n-------------------");

        return stringBuilder.toString();
    }

    /**
     * Metodo que adiciona avaliacao em uma midia
     * 
     * @param nota
     * @param comentario
     * @param catalogo
     * @return avaliação adicionada
     */
    public Avaliacao adicionarAvaliacao(int nota, String comentario, Catalogo catalogo) {
        return clienteAtual.adicionarAvaliacao(nota, comentario, catalogo);
    }

    /**
     * Metodo que adiciona comentario em uma avaliacao ja existente
     * 
     * @param avaliacao
     * @param comentario
     */
    public void adicionarComentarioAvaliacaoExistente(Avaliacao avaliacao, String comentario) {
        this.clienteAtual.adicionarComentarioAvaliacaoExistente(avaliacao, comentario);
    }

    /**
     * Retorna a avaliação média do catalogo sendo recebido como parametro
     * 
     * @param catalogo a retornar a avaliação média
     * @return avaliação média do catálogo
     */
    public BigDecimal mediaAvaliacao(Catalogo catalogo) {
        return catalogo.mediaAvaliacao();
    }

    public LinkedList<Catalogo> midiaMaisAvaliadas() {
        LinkedList<Catalogo> maisAvaliadas = new LinkedList<Catalogo>();
  
        // primeiro pega as midias com mais de 100 avaliacoes
        for (int key : catalogos.keySet()) {
            if(catalogos.get(key).quantidadeAvaliacoes() >= 100){
                maisAvaliadas.add(catalogos.get(key));
            }
        }

        // depois orgena pelas mais avaliadas
        Collections.sort(maisAvaliadas, (a, b) -> { return a.mediaAvaliacao().compareTo(b.mediaAvaliacao()); });        

        return maisAvaliadas;
    }

    public LinkedList<Catalogo> midiaComMaisVisualizacao() {
        LinkedList<Catalogo> midiaComMaisVisualizacao = new LinkedList<Catalogo>();
  
        // primeiro pega as midias e coloca na lista
        for (int key : catalogos.keySet()) {
                midiaComMaisVisualizacao.add(catalogos.get(key));
        }

        // depois orgena pelas mais avaliadas
        Collections.sort(midiaComMaisVisualizacao, (a, b) -> { return Integer.compare(a.getAudiencia(), b.getAudiencia()); });        

        return midiaComMaisVisualizacao;
    }

}