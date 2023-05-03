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
    public String getClientes() {
        StringBuilder str = new StringBuilder();
        for (String key : clientes.keySet()) {
            str.append(clientes.get(key).getLogin());
            str.append(", ");
        }
        return str.toString().substring(0, str.length() - 2);
    }

    public Cliente getClienteAtual() {
        return this.clienteAtual;
    }

    // metodo para testes
    public String getCatalogo() {
        StringBuilder str = new StringBuilder();
        for (int key : catalogos.keySet()) {
            str.append(catalogos.get(key).getNome());
            str.append(", ");
        }
        return str.toString().substring(0, str.length() - 2);
    }

    public void adicionarCatalogo(Catalogo catalogo) {
        try {
            catalogos.put(catalogo.getId(), catalogo);
        } catch (Exception e) {
        }
    }

    public void adicionarCatalogos(LinkedList<Catalogo> catalogos) {
        for (Catalogo catalogo : catalogos) {
            adicionarCatalogo(catalogo);
        }
    }

    public void adicionarCliente(Cliente cliente) {
        try {
            clientes.put(cliente.getLogin(), cliente);
        } catch (Exception e) {
        }
    }

    public void adicionarCliente(LinkedList<Cliente> clientes) {
        for (Cliente x : clientes) {
            adicionarCliente(x);
        }
    }

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

    public LinkedList<Catalogo> filtrarPorIdioma(String filtrar) {

        LinkedList<Catalogo> filtro = new LinkedList<Catalogo>();
        Catalogo serie;
        for (int key : catalogos.keySet()) {
            serie = catalogos.get(key);
            if (serie.getIdioma().equals(filtrar)) {
                filtro.add(serie);
            }
        }
        return filtro;
    }

    public LinkedList<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
        LinkedList<Serie> filtro = new LinkedList<Serie>();
        Serie serie;
        for (int key : catalogos.keySet()) {
            serie = (Serie) catalogos.get(key);
            if (serie.getEpisodios() == quantEpisodios) {
                filtro.add(serie);
            }
        }
        return filtro;
    }

    public LinkedList<Filme> filtrarPorDuracao(int duracao) {
        LinkedList<Filme> filtro = new LinkedList<Filme>();
        Filme filme;
        for (int key : catalogos.keySet()) {
            filme = (Filme) catalogos.get(key);
            if (filme.getDuracao() == duracao) {
                filtro.add(filme);
            }
        }
        return filtro;
    }

    public void registrarAudiencia(Catalogo midia) {
        midia.registrarAudiencia();
    }

    public void logoff() {
        this.clienteAtual = null;
    }

    public Catalogo buscarCatalogo(String nomeSerie) {
        try {
            for (int key : catalogos.keySet()) {
                if (catalogos.get(key).getNome() == nomeSerie) {
                    return catalogos.get(key);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}