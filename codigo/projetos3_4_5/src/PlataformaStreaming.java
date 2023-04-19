import java.util.*;

public class PlataformaStreaming {
    private String nome;
    private HashMap<String, Serie> series;
    private HashMap<String, Cliente> clientes;
    private Cliente clienteAtual;

    PlataformaStreaming(String nome) {
        this.nome = nome;
        this.series = new HashMap<String, Serie>();
        this.clientes = new HashMap<String, Cliente>();
    }

    public Cliente login(String nomeUsuario, String senha) {
        Cliente cliente;
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

    public LinkedList<Serie> filtrarPorGenero(String genero) {
        /*
         * LinkedList<Serie> filtro = new LinkedList<Serie>();
         * Serie serie = new Serie(null, null, null, 0);
         * try {
         * for (String key : series.keySet()) {
         * serie = series.get(key);
         * if (serie.getGenero().equals(genero)) {
         * filtro.add(serie);
         * }
         * }
         * return filtro;
         * } catch (Exception e) {
         * return null;
         * }
         */
        return this.clienteAtual.filtrarPorGenero(genero);
    }

    public LinkedList<Serie> filtrarPorIdioma(String idioma) {
        /*
         * LinkedList<Serie> filtro = new LinkedList<Serie>();
         * Serie serie = new Serie(null, null, null, 0);
         * try {
         * for (String key : series.keySet()) {
         * serie = series.get(key);
         * if (serie.getIdioma().equals(idioma)) {
         * filtro.add(serie);
         * }
         * }
         * return filtro;
         * } catch (Exception e) {
         * return null;
         * }
         */
        return this.clienteAtual.filtrarPorIdioma(idioma);
    }

    public LinkedList<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
        /*
         * LinkedList<Serie> filtro = new LinkedList<Serie>();
         * Serie serie = new Serie(null, null, null, 0);
         * try {
         * for (String key : series.keySet()) {
         * serie = series.get(key);
         * if (serie.getEpisodios() == quantEpisodios) {
         * filtro.add(serie);
         * }
         * }
         * return filtro;
         * } catch (Exception e) {
         * return null;
         * }
         */
        return clienteAtual.filtrarPorQtdEpisodios(quantEpisodios);
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