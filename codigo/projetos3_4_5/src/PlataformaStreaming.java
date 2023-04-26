import java.util.*;

public class PlataformaStreaming {
    private String nome;
    private HashMap<Integer, Serie> series;
    private HashMap<String, Cliente> clientes;
    private Cliente clienteAtual;

    PlataformaStreaming(String nome) {
        this.nome = nome;
        this.series = new HashMap<Integer, Serie>();
        this.clientes = new HashMap<String, Cliente>();
        this.clienteAtual = new Cliente(null, null, null);
    }

    public Cliente login(String nomeUsuario, String senha) {
        Cliente cliente = clientes.get(nomeUsuario);

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
    public String getSeries() {
        StringBuilder str = new StringBuilder();
        for (int key : series.keySet()) {
            str.append(series.get(key).getNome());
            str.append(", ");
        }
        return str.toString().substring(0, str.length() - 2);
    }

    public void adicionarSerie(Serie serie) {
        try {
            series.put(serie.getId(), serie);
        } catch (Exception e) {
        }
    }

    public void adicionarSerie(LinkedList<Serie> series) {
        for (Serie x : series) {
            adicionarSerie(x);
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

    public LinkedList<Serie> filtrarPorGenero(String genero) {

        LinkedList<Serie> filtro = new LinkedList<Serie>();
        Serie serie;
        try {
            for (int key : series.keySet()) {
                serie = series.get(key);
                if (serie.getGenero().equals(genero)) {
                    filtro.add(serie);
                }
            }
            return filtro;
        } catch (Exception e) {
            return null;
        }
    }

    public LinkedList<Serie> filtrarPorIdioma(String filtrar) {

        LinkedList<Serie> filtro = new LinkedList<Serie>();
        Serie serie;
        try {
            for (int key : series.keySet()) {
                serie = series.get(key);
                if (serie.getIdioma().equals(filtrar)) {
                    filtro.add(serie);
                }
            }
            return filtro;
        } catch (Exception e) {
            return null;
        }
    }

    public LinkedList<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
        LinkedList<Serie> filtro = new LinkedList<Serie>();
        Serie serie;
        try {
            for (int key : series.keySet()) {
                serie = series.get(key);
                if (serie.getEpisodios() == quantEpisodios) {
                    filtro.add(serie);
                }
            }
            return filtro;
        } catch (Exception e) {
            return null;
        }
    }

    public void registrarAudiencia(Serie serie) {
        Serie serieAux = new Serie(null, null, null, 0);
        try {
            serieAux = series.get(serie.getNome());
            serieAux.registrarAudiencia();
            clienteAtual.registrarAudiencia(serieAux);
        } catch (Exception e) {
        }
    }

    public void logoff() {
        this.clienteAtual = null;
    }

    public Serie buscarSerie(String nomeSerie) {
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