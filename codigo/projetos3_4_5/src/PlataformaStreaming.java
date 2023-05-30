import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

public class PlataformaStreaming {
    private String nome;
    private HashMap<Integer, Catalogo> catalogos;
    private HashMap<String, Cliente> clientes;
    private Cliente clienteAtual;

    PlataformaStreaming(String nome) {
        this.nome = nome;
        this.catalogos = new HashMap<Integer, Catalogo>(250);
        this.clientes = new HashMap<String, Cliente>(51900);
        this.clienteAtual = null;
    }

    public String getNome() {
        return this.nome;
    }

    /**
     * @return Cliente atual
     * @throws NullPointerException
     */
    public Cliente getClienteAtual() throws NullPointerException {
        if (this.clienteAtual != null)
            return this.clienteAtual;
        throw new NullPointerException();
    }

    /**
     * @return String de catalogos
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
     * Logar na plataforma
     * 
     * @param login login do usuario
     * @param senha senha do usuario
     * 
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
     * logoff do cliente na plataforma
     * 
     */
    public void logoff() {
        this.clienteAtual = null;
    }

    /**
     * adicionar catalogo na plataforma
     * 
     * @param catalogo catalogo a ser adicionado
     * @throws IOException
     * 
     */
    public void adicionarCatalogo(Catalogo catalogo) throws IOException {
        Armazenagem.gravar("POO_Series", catalogo);
        catalogos.put(catalogo.getId(), catalogo);
    }

    /**
     * adicionar catalogo na plataforma
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
     * adicionar cliente na plataforma
     * 
     * @param cliente cliente a ser adicionado
     * @throws IOException
     * @throws IllegalArgumentException cliente ja existe
     */
    public void adicionarCliente(Cliente cliente) throws IOException, IllegalArgumentException {
        if (validarLoginCliente(cliente)) {
            this.clientes.put(cliente.getLogin(), cliente);
            Armazenagem.gravar("POO_Espectadores", cliente);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * adicionar cliente na plataforma
     * 
     * @param clientes linkedlist de cliente a ser adicionado
     * 
     */
    public boolean carregarCliente() {
        Function<String, Cliente> contrutorCliente = (str -> new Cliente(str.split(";")[0], str.split(";")[1],
                str.split(";")[2]));

        LinkedList<Cliente> clientes;
        try {
            clientes = Armazenagem.ler("POO_Espectadores", contrutorCliente);
            this.clientes.clear();
            for (Cliente x : clientes) {
                this.clientes.put(x.getLogin(), x);
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        try {
            carregarAudiencia("POO_Audiencia");
        } catch (FileNotFoundException e) {
        }
        return true;
    }

    /**
     * Metodo que le um arquivo e atualiza a audiencia
     * 
     * @param nomeArq nome do arquivo .csv
     * 
     */
    public void carregarAudiencia(String nomeArq)
            throws FileNotFoundException {
        File file = new File("./codigo/projetos3_4_5/arquivos/" + nomeArq + ".csv");
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
                if (linhaAux[1].equals("F")) {
                    catalogoAux = catalogos.get(linhaAux[2]);
                    clienteAux.adicionarNaLista(catalogoAux);
                } else {
                    clienteAux.retirarDaLista(linhaAux[2]);
                }
            }
        }
        entrada.close();
    }

    /**
     * valida se o login do cliente existe
     * 
     * @param cliente cliente a ser validado
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
     * filtra catalogo por genero
     * 
     * @param genero genero a ser filtrado
     * 
     */
    public LinkedList<Catalogo> filtrarPorGenero(String genero) {
        LinkedList<Catalogo> filtro = new LinkedList<Catalogo>();
        Catalogo serie;
        for (int key : catalogos.keySet()) {
            serie = catalogos.get(key);
            if (serie.getGenero().equals(genero)) {
                filtro.add(serie);
            }
        }
        return filtro;
    }

    /**
     * filtra catalogo por idioma
     * 
     * @param idioma idioma a ser filtrado
     * 
     */
    public LinkedList<Catalogo> filtrarPorIdioma(String idioma) {

        LinkedList<Catalogo> filtro = new LinkedList<Catalogo>();
        Catalogo serie;
        for (int key : catalogos.keySet()) {
            serie = catalogos.get(key);
            if (serie.getIdioma().equals(idioma)) {
                filtro.add(serie);
            }
        }
        return filtro;
    }

    /**
     * filtra catalogo por quantidade de episodios
     * 
     * @param quantEpisodios quantEpisodios a ser filtrado
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
     */
    public Catalogo buscarCatalogo(String midia) throws NullPointerException, IndexOutOfBoundsException {

        for (int key : catalogos.keySet()) {
            if (catalogos.get(key).getNome().equals(midia)) {
                return catalogos.get(key);
            }
        }
        throw new NullPointerException();
    }

    // CRIAR TESTE
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

    // Metodo que verifica as midias que o cliente quer ver futuramente
    public String visualizarListaParaVerFuturamente() {
        return this.clienteAtual.listarMidiasParaSeremAssistidas();
    }

    /**
     * Metodo que adiciona avaliacao em uma midia
     * 
     * @param nota
     * @param comentario
     * @param catalogo
     * @return
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

    // TESTAR
    public BigDecimal mediaAvaliacao(Catalogo catalogo) {
        return catalogo.mediaAvaliacao();
    }

}