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
        this.clienteAtual = new Cliente(null, null);
    }

    public Cliente login(String nomeUsuario, String senha) {
        Cliente cliente = new Cliente(null, null);
        try {
            cliente = clientes.get(nomeUsuario);
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
            str.append(clientes.get(key).getNomeUsuario());
            str.append(", ");
        }
        return str.toString().substring(0, str.length() - 2);
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
            series.put(serie.getNome(), serie);
        } catch (Exception e) {
        }
    }

    public void adicionarCliente(Cliente cliente) {
        try {
            clientes.put(cliente.getNomeUsuario(), cliente);
        } catch (Exception e) {
        }
    }

    public String filtrarPorGenero(String genero) {
        
        LinkedList<Serie> filtro = new LinkedList<Serie>();
        Serie serie; //= new Serie(null, null, null, 0);
        try {
          for (String key : series.keySet()) {
                serie = series.get(key);
            if (serie.getGenero().equals(genero)) {
                filtro.add(serie);
            }
        }
        String seriesFiltradas = "";
        for (int i = 0; i < filtro.size(); i++) {
            seriesFiltradas = filtro.get(i).getNome() + seriesFiltradas;
        } 
            return seriesFiltradas;
        }

        catch (Exception e) {
          return null;
        }
    }

    public String filtrarPorIdioma(String idioma) {
         
        LinkedList<Serie> filtro = new LinkedList<Serie>();
        Serie serie;
        try {
          for (String key : series.keySet()) {
                serie = series.get(key);
            if (serie.getIdioma().equals(idioma)) {
                filtro.add(serie);
            }
        }
        String seriesFiltradas = "";
        for (int i = 0; i < filtro.size(); i++) {
            seriesFiltradas = filtro.get(i).getNome() + seriesFiltradas;
        } 
            return seriesFiltradas;
        }

        catch (Exception e) {
          return null;
        }
    }

    /* Ainda em desenvolvimento

    public String filtroGeral(T criterio) {
        LinkedList<Serie> filtro = new LinkedList<Serie>();
        Serie serie;
        try {
            for (String key : series.keySet()) {
                serie = series.get(key);
                if (serie.getGenero().equals(criterio)) {
                    filtro.add(serie);
                }
            }
            String seriesFiltradas = "";
            for (int i = 0; i < filtro.size(); i++) {
                seriesFiltradas = filtro.get(i).getNome() + seriesFiltradas;
            }
            return seriesFiltradas;
        } catch (Exception e) {
            return null;
        }
    }
    */
    public String filtrarPorQtdEpisodios(int quantEpisodios) {
        LinkedList<Serie> filtro = new LinkedList<Serie>();
        Serie serie;
        try {
            for (String key : series.keySet()) {
                serie = series.get(key);
                if (serie.getEpisodios() == quantEpisodios) {
                    filtro.add(serie);
                }
            }
            String seriesFiltradas = "";
            for (int i = 0; i < filtro.size(); i++) {
                seriesFiltradas = filtro.get(i).getNome() + seriesFiltradas;
            }
            return seriesFiltradas;
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
        Serie serieAux = new Serie(null, null, null, 0);
        try {
            serieAux = series.get(nomeSerie);
            return serieAux;
        } catch (Exception e) {
            return null;
        }
    }
}