import java.io.IOException;
import java.util.*;

public class PlataformaStreaming {
    private String nome;
    private HashMap<Integer, Catalogo> catalogos;
    private HashMap<String, Cliente> clientes;
    private Cliente clienteAtual;

    PlataformaStreaming(String nome) {
        this.nome = nome;
        this.catalogos = new HashMap<Integer, Catalogo>();
        this.clientes = new HashMap<String, Cliente>();
        this.clienteAtual = new Cliente(null, null, null);
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

        if (cliente == null || cliente.getSenha() != senha) {
            this.clienteAtual = null;
        } else {
            this.clienteAtual = cliente;
        }

        return this.clienteAtual;

    }

    // metodo para testes
    /**
     * @return String de clientes
     */
    public String getClientes() {
        StringBuilder str = new StringBuilder();
        for (String key : clientes.keySet()) {
            str.append(clientes.get(key).getLogin());
            str.append(", ");
        }
        return str.toString().substring(0, str.length() - 2);
    }

    /**
     * @return Cliente atual
     */
    public Cliente getClienteAtual() {
        return this.clienteAtual;
    }

    // metodo para testes
    /**
     * @return String de catalogos
     */
    public String getCatalogo() {
        StringBuilder str = new StringBuilder();
        for (int key : catalogos.keySet()) {
            str.append(catalogos.get(key).getNome());
            str.append(", ");
        }
        return str.toString().substring(0, str.length() - 2);
    }

    /**
     * adicionar catalogo na plataforma
     * 
     * @param catalogo catalogo a ser adicionado
     * 
     */
    public void adicionarCatalogo(Catalogo catalogo) {
        try {
            catalogos.put(catalogo.getId(), catalogo);
        } catch (Exception e) {
        }
    }

    /**
     * adicionar catalogo na plataforma
     * 
     * @param catalogos linkedlist de catalogo a ser adicionado
     * 
     */
    public void adicionarCatalogos(LinkedList<Catalogo> catalogos) {
        for (Catalogo catalogo : catalogos) {
            adicionarCatalogo(catalogo);
        }
    }

    /**
     * adicionar cliente na plataforma
     * 
     * @param cliente cliente a ser adicionado
     * 
     */
    public boolean adicionarCliente(Cliente cliente) {
        if (validarLoginCliente(cliente)) {
            clientes.put(cliente.getLogin(), cliente);
            return true;
        }
        return false;
    }

    /**
     * adicionar cliente na plataforma
     * 
     * @param clientes linkedlist de cliente a ser adicionado
     * 
     */
    public void adicionarCliente(LinkedList<Cliente> clientes) {
        for (Cliente x : clientes) {
            adicionarCliente(x);
        }
    }

    /**
     * Salva os clientes em um arquivo
     * @param clientes Lista de clientes a serem salvos no arquivo
     */
    public void salvarClientes(LinkedList<Cliente> clientes) {
        try {
            Armazenagem.gravar("Clientes", clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
     * Registrar audiencia da midia
     * 
     * @param midia midia ser registrada
     * 
     */
    public void registrarAudiencia(Catalogo midia) {
        midia.registrarAudiencia();
    }

    /**
     * logoff do cliente na plataforma
     * 
     */
    public void logoff() {
        this.clienteAtual = null;
    }

    /**
     * Buscar catalogo por nome
     * 
     * @param midia nome da midia a ser procurada
     * 
     */
    public Catalogo buscarCatalogo(String midia) {
        try {
            for (int key : catalogos.keySet()) {
                if (catalogos.get(key).getNome() == midia) {
                    return catalogos.get(key);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}