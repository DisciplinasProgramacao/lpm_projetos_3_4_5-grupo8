import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
public class PlataformaStreaming {
    private String nome;
    private HashMap<Integer, Catalogo> catalogos;
    public HashMap<String, Cliente> clientes;
    private Cliente clienteAtual;

    PlataformaStreaming(String nome) {
        this.nome = nome;
        this.catalogos = new HashMap<Integer, Catalogo>(250);
        this.clientes = new HashMap<String, Cliente>(51900);
        this.clienteAtual = null;
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

    // metodo para testes
    /**
     * @return String de clientes
     */
    public String getClientes() {
        StringBuilder str = new StringBuilder();
        for (String key : clientes.keySet()) {
            str.append(key);
            str.append(", ");
        }
        return str.toString().substring(0, str.length() - 2);
    }

    public String getNome() {
        return this.nome;
    }

    /**
     * @return Cliente atual
     * @throws IndexOutOfBoundsException analisar essa exceção aqui
     */
    public Cliente getClienteAtual() /*throws Exception*/ {
        return this.clienteAtual;
    }

    // metodo para testes
    /**
     * @return String de catalogos
     */
    public String getCatalogo() {
        int cont=1;
        StringBuilder str = new StringBuilder();
        for (int key : catalogos.keySet()) {
            str.append(cont + ",");
            str.append(catalogos.get(key).getNome());
            str.append(", ");
            str.append("\n ");
            cont++;
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
            Armazenagem.gravar("POO_Series", catalogo);
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
    public boolean carregarCatalogos() {
        Function<String, Filme> contrutorFilme = (str -> new Filme(Integer.parseInt(str.split(";")[0]),
                str.split(";")[1],
                str.split(";")[2],
                Integer.parseInt(str.split(";")[3])));

        Function<String, Serie> contrutorSerie = (str -> new Serie(Integer.parseInt(str.split(";")[0]),
                str.split(";")[1],
                str.split(";")[2]));

        LinkedList<Filme> filmes;
        LinkedList<Serie> series;
        try {
            filmes = Armazenagem.ler("POO_Filmes", contrutorFilme);
            series = Armazenagem.ler("POO_Series", contrutorSerie);
            this.catalogos.clear();
            for (Filme x : filmes) {
                this.catalogos.put(x.getId(), x);
            }
            for (Serie x : series) {
                this.catalogos.put(x.getId(), x);
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * adicionar cliente na plataforma
     * 
     * @param cliente cliente a ser adicionado
     * 
     */
    public boolean adicionarCliente(Cliente cliente) {
        if (validarLoginCliente(cliente)) {
            this.clientes.put(cliente.getLogin(), cliente);
            try {
                Armazenagem.gravar("POO_Espectadores", cliente);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
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