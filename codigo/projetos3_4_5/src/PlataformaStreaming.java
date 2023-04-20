import java.util.*;

public class PlataformaStreaming{
    private String nome;
    private HashMap<String, Serie> series;
    private HashMap<String, Cliente> clientes;
    private Cliente clienteAtual;

    PlataformaStreaming(String nome) {
        this.nome = nome;
        this.series = new HashMap<String, Serie>();
        this.clientes = new HashMap<String, Cliente>();
        this.clienteAtual = new Cliente(null, null, null);
    }

    public Cliente login(String login, String senha) {
        Cliente cliente = new Cliente(null, null, null);
        try {
            cliente = clientes.get(login);
            if (cliente.getSenha() == senha) {
                this.clienteAtual = cliente;
                return cliente;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
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
        for (String key : series.keySet()) {
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

    public void adicionarCliente(Cliente cliente) {
        try {
            clientes.put(cliente.getLogin(), cliente);
        } catch (Exception e) {
        }
    }

    public LinkedList<Serie> filtrarPorGenero(String genero) {
        
        LinkedList<Serie> filtro = new LinkedList<Serie>();
        Serie serie; 
        try {
          for (String key : series.keySet()) {
                serie = series.get(key);
            if (serie.getGenero().equals(genero)) {
                filtro.add(serie);
            }
        }
            return filtro;
        }
        catch (Exception e) {
          return null;
        }
    }

    public LinkedList<Serie> filtrarPorIdioma(String filtrar) {
         
        LinkedList<Serie> filtro = new LinkedList<Serie>();
        Serie serie;
        try {
          for (String key : series.keySet()) {
                serie = series.get(key);
            if (serie.getIdioma().equals(filtrar)) {
                filtro.add(serie);
            }
        }
            return filtro;
        }catch (Exception e) {
          return null;
        }
    }
    
    public LinkedList<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
        LinkedList<Serie> filtro = new LinkedList<Serie>();
        Serie serie;
        try {
            for (String key : series.keySet()) {
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
            for (String key : series.keySet()) {
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