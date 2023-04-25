import java.util.*;

public class PlataformaStreaming{
    private String nome;
    private HashMap<Integer,Catalogo> series;
    private HashMap<String, Cliente> clientes;
    private Cliente clienteAtual;

    PlataformaStreaming(String nome) {
        this.nome = nome;
        this.series = new HashMap<Integer, Catalogo>();
        this.clientes = new HashMap<String, Cliente>();
        this.clienteAtual = new Cliente(null, null, null);
    }
    
    public Cliente login(String nomeUsuario, String senha) {
        Cliente cliente = clientes.get(nomeUsuario);

         if(cliente == null || cliente.getSenha() != senha){
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
        for (int key : series.keySet()) {
            str.append(series.get(key).getNome());
            str.append(", ");
        }
        return str.toString().substring(0, str.length() - 2);
    }

    public void adicionarCatalogo(Catalogo serie) {
        try {
            series.put(serie.getId(), serie);
        } catch (Exception e) {
        }
    }

    public void adicionarCliente(Cliente cliente) {
        try {
            clientes.put(cliente.getLogin(), cliente);
        } catch (Exception e) {
        }
    }

    public LinkedList<Catalogo> filtrarPorGenero(String genero) {
        
        LinkedList<Catalogo> filtro = new LinkedList<Catalogo>();
        Catalogo serie; 
        for (int key : series.keySet()) {
            serie = series.get(key);
        if (serie.getGenero().equals(genero)) {
            filtro.add(serie);
        }
        }
        return filtro;
    }

    public LinkedList<Catalogo> filtrarPorIdioma(String filtrar) {
         
        LinkedList<Catalogo> filtro = new LinkedList<Catalogo>();
        Catalogo serie;
        for (int key : series.keySet()) {
                serie = series.get(key);
            if (serie.getIdioma().equals(filtrar)) {
                filtro.add(serie);
            }
        }
        return filtro;
    }

    public LinkedList<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
        LinkedList<Serie> filtro = new LinkedList<Serie>();
        Serie serie;
        for (int key : series.keySet()) {
            serie = (Serie) series.get(key);
            if (serie.getEpisodios() == quantEpisodios) {
                filtro.add(serie);
            }
        }
        return filtro;
    }
    
    public LinkedList<Filme> filtrarPorDuracao(int duracao) {
        LinkedList<Filme> filtro = new LinkedList<Filme>();
        Filme filme;
        for (int key : series.keySet()) {
            filme = (Filme) series.get(key);
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
            for (int key : series.keySet()) {
                if (series.get(key).getNome() == nomeSerie) {
                    return series.get(key);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }


}