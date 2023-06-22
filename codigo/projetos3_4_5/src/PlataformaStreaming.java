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
    private Relatorio relatorio = new Relatorio();
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

    //Metodos testes
    public HashMap<Integer, Catalogo> getCatalogos() {
        return this.catalogos;
    }

    public HashMap<String, Cliente> getClientes() {
        return this.clientes;
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
            str.append(" | " + catalogos.get(key).getGenero());
            str.append(" | " + catalogos.get(key).getIdioma());
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

        StringBuilder espec = new StringBuilder("#Especialistas");
        StringBuilder prof = new StringBuilder("#Profissionais");

        for(Cliente client : clientes.values()){

            if(client.getEstadoCliente().podeAssistirLancamento()){
                prof.append("\n");
                prof.append(client.getLogin());
            } else if(client.getEstadoCliente().podeComentar()){
                espec.append("\n");
                espec.append(client.getLogin());
            }   
        }

        try {
            Armazenagem.gravarReescrevendoArquivo("POO_Especialistas", espec.toString());
        } catch (IOException e) {
        }
        try {
            Armazenagem.gravarReescrevendoArquivo("POO_profissionais", prof.toString());
        } catch (IOException e) {
        }
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
     * Método que cadastra uma mídia (filme ou série), recebe todos os dados da midia, cria o objeto e adiciona ao catálogo da plataforma
     * Caso gênero seja inválido lança IllegalArgumentException
     * Caso ocorra erro ao adicionar mídia ao catálogo lança IOException
     * 
     * @param nome
     * @param dataLancamento
     * @param genero
     * @param idioma
     * @param duracao
     * @throws IOException caso ocorra erro ao adicionar filme ao catálogo ou ao gravar no arquivo
     * @throws IllegalArgumentException caso gênero informado inválido
     */
    public void cadastrarMidia(String nome, String dataLancamento, String genero, String idioma, int duracao, int quantidadeEpisodios, boolean lancamento) throws IOException, IllegalArgumentException{
        Catalogo midia;

        if(duracao > 0){
            midia = new Filme(nome, dataLancamento, genero, idioma, duracao, lancamento);
            Armazenagem.gravar("POO_Filmes", midia);
            
        } else {
            midia = new Serie(nome, dataLancamento, genero, idioma, quantidadeEpisodios, lancamento);
            Armazenagem.gravar("POO_Series", midia);
        }

        adicionarCatalogo(midia);
        if(lancamento == true){
            carregarEAdicionarLancamento();
        }
    }

    private void carregarEAdicionarLancamento(){
        StringBuilder a = new StringBuilder("#Lancamentos");
        for(Catalogo cat : catalogos.values()){
            if(cat.lancamento){
                a.append("\n");
                a.append(cat.getId());
            }
        }
        try {
            Armazenagem.gravarReescrevendoArquivo("POO_Lancamento", a.toString());
        } catch (IOException e) {
        }
    }

    /**
     * Adiciona catalogos na plataforma, retorna FileNotFoundException caso não encontre o arquivo
     * Cria filmes com idioma e genêro aleatórios dentro da lista de permitidos
     * 
     * @param catalogos linkedlist de catalogo a ser adicionado
     * @throws FileNotFoundException
     * 
     */
    public void carregarCatalogos() throws FileNotFoundException {
        Random random = new Random();
        EnumGeneros[] generos = EnumGeneros.values();
        String[] idiomas = {"Portugues", "Ingles", "Espanhol", "Japones"};

        Function<String, Filme> contrutorFilme = (str -> new Filme(Integer.parseInt(str.split(";")[0]),
                str.split(";")[1],
                str.split(";")[2],
                Integer.parseInt(str.split(";")[3]),
                generos[random.nextInt(generos.length)].getDescricao(),
                idiomas[random.nextInt(idiomas.length)], false));

        Function<String, Serie> contrutorSerie = (str -> new Serie(Integer.parseInt(str.split(";")[0]),
                str.split(";")[1],
                str.split(";")[2],
                generos[random.nextInt(generos.length)].getDescricao(),
                idiomas[random.nextInt(idiomas.length)], false));

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
        this.carregarLancamentos();
        this.carregarEspeciais();
    }

    private void carregarEspeciais(){
        try {
            Function<String, String> dividirTipo = (str -> str);
            LinkedList<String> data = Armazenagem.ler("POO_Especialistas", dividirTipo);
            Cliente aux;
            for(String str : data){
                aux = this.clientes.get(str);
                if(aux != null){
                    aux.getEstadoCliente().tornarEspecialista();
                }
            }
            LinkedList<String> data2 = Armazenagem.ler("POO_Profissionais", dividirTipo);
            for(String str : data2){
                aux = this.clientes.get(str);
                if(aux != null){
                    aux.getEstadoCliente().tornarProfissional();
                }
            }


        } catch (FileNotFoundException e) {//Se nao existir o arq do lancamento nao tem problema 
        }
    }

    private void carregarLancamentos(){
        Function<String, String> dividirTipo = (str -> str);
        try {
            LinkedList<String> data = Armazenagem.ler("POO_Lancamento", dividirTipo);
            Catalogo aux;
            for(String str : data){
                aux = this.catalogos.get(Integer.parseInt(str));
                if(aux != null){
                    aux.lancamento = true;
                }
            }
        } catch (FileNotFoundException e) {//Se nao existir o arq do lancamento nao tem problema 
        }
    }
    /** 
     * Listar lancamentos
     * 
     * @return String com lancamentos
     * 
    */
    public String lancamentos(){
        StringBuilder x = new StringBuilder();
        for(Catalogo y : catalogos.values()){
            if(y.lancamento == true){
                x.append(y.getId());
                x.append(" - ");
                x.append(y.getNome());
                x.append("\n");
            }
        }
        return x.toString();
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
     * Método que cadastra um cliente, recebe todos os dados do cliente, cria o objeto e adiciona a lista de clientes da plataforma
     * Caso já exista cliente com o login informado lança IllegalArgumentException
     * Caso ocorra erro ao adicionar cliente a plataforma lança IOException
     * @param nome
     * @param nomeUsuario
     * @param senha
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public void cadastrarCliente(String nome, String nomeUsuario, String senha) throws IOException, IllegalArgumentException{
        Cliente novoCliente = new Cliente(nome, nomeUsuario, senha);

        adicionarCliente(novoCliente);
        Armazenagem.gravar("POO_Espectadores", novoCliente);
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
    public String filtrarCatalogo(String filtro) {
        LinkedList<Catalogo> filtrado = new LinkedList<Catalogo>();
        Catalogo midia;
        for (int key : catalogos.keySet()) {
            midia = catalogos.get(key);
            if (midia.getGenero().toUpperCase().equals(filtro) || midia.getNome().equals(filtro) || midia.getIdioma().equals(filtro)) {
                filtrado.add(midia);
            }
        }
        if(filtrado.size() > 0){
            return formatarRetornoFiltro(filtrado);
        }
        return "Nao foram encontradas midias correspondentes ao filtro";
    }

    /**
     * Metodo que formata as midias filtradas
     */
    private String formatarRetornoFiltro(LinkedList<Catalogo> listaFiltros) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("-------------------");

        int contador = 0;

        for (Catalogo midia : listaFiltros) {
            contador++;
            stringBuilder.append("\n[" + contador + "] " + midia.getNome());
            stringBuilder.append(" | " + midia.getGenero());
            stringBuilder.append(" | " + midia.getIdioma());
        }

        stringBuilder.append("\n-------------------");

        return stringBuilder.toString();
    }

    /**
    * filtra catalogo por quantidade de episodios
    *
    * @param quantEpisodios quantEpisodios a ser filtrado
    * @return linked list de catálogos encontrados a partir do filtro 
    *
    */
    public String filtrarPorQtdEpisodios(int quantEpisodios) {
        LinkedList<Catalogo> filtro = new LinkedList<Catalogo>();
        Serie serie;
        for (int key : catalogos.keySet()) {
            if(catalogos.get(key) instanceof Serie){
                serie = (Serie) catalogos.get(key);
                if (serie.getEpisodios() == quantEpisodios) {
                    filtro.add(serie);
                }
            }
        }

        if(filtro.size() > 0){
            return formatarRetornoFiltro(filtro);
        }
        return "Nao foram encontradas midias correspondentes ao filtro";
    }

    /**
    * filtra catalogo por duracao em minutos
    *
    * @param duracao duracao de midia a ser filtrado
    * @return linked list de catálogos encontrados a partir do filtro 
    *
    */
    public String filtrarPorDuracao(int duracao) {
        LinkedList<Catalogo> filtro = new LinkedList<Catalogo>();
        Filme filme;
        for (int key : catalogos.keySet()) {
            if(catalogos.get(key) instanceof Filme){
                filme = (Filme) catalogos.get(key);
                if (filme.getDuracao() == duracao) {
                    filtro.add(filme);
                }
            }
        }

        if(filtro.size() > 0){
            return formatarRetornoFiltro(filtro);
        }
        return "Nao foram encontradas midias correspondentes ao filtro";        
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
     * @throws IllegalArgumentException Nao pode assistir lancamentos
     */
    public void assistirMidia(String nomeMidia) throws NullPointerException, IndexOutOfBoundsException, IllegalArgumentException {
        Catalogo catalogo = this.getClienteAtual().buscarMidiaNaListaParaVer(nomeMidia);
        if (catalogo != null) {
            if(!catalogo.lancamento){
                this.clienteAtual.registrarAudiencia(catalogo);
                if(!this.clienteAtual.podeComentar())
                    tornarClienteEspecialista();
            }else{
                if(clienteAtual.podeVerLancamento()){
                    this.clienteAtual.registrarAudiencia(catalogo);
                }
                else throw new IllegalArgumentException();
            }
            
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



     /**************************** RELATORIOS ************************/
    /**
     * Metodo que retorna qual cliente assistiu mais midias
     * @return
     */
    public String clienteQueMaisAssistiu(){
        return relatorio.criarRelatorioClienteQueMaisAssistiu(clientes);
    }

     /**
     * Metodo que retorna qual cliente possui mais avaliacoes
     * @return
     */
    public void clienteQueMaisAvaliou(){
        relatorio.criarRelatorioClienteComMaisAvaliacoes(clientes);
    }

     /**
     * Metodo que retorna a porcentagem de clientes com pleo menos 15 avaliacoes 
     * @return
     */
    public String porcentagemClientes(){
        return relatorio.criarRelatorioPorcentagemDeClienteNoMinQuinzeAvaliacoes(clientes);
    }
    
    /**
     * Metodo que retorna as 10 midias de melhor avaliacao do catalogo, ordenada de modo decrescente
     * @return
     */
    public void midiasMelhorAvaliadas() {
       relatorio.criarRelatorioMidiasComMelhoresAvaliacoes(catalogos);
    }

    /**
     * Metodo que retorna as 10 midias com mais visualizações do catalogo, ordenada de modo decrescente
     * @return
     */
    public void midiaComMaisVisualizacao() {
       relatorio.criarRelatorioMidiasComMaisVisualizacoes(catalogos); 
    }
   
    /**
     * Método que retorna relatório por genero com 10 midias
     * @param genero
     * @return String
     */
    public String relatorioPorGeneroAudiencia(String genero) {
       return relatorio.relatorioPorGeneroAudiencia(genero, catalogos);
    }
    /**
     * Metodo que retorna relatorio por genero com 10 midias mais bem avaliadas
     * @return String
     * @param genero
     */
    public void relatorioPorGeneroAvaliacao(String genero) {
        relatorio.relatorioPorGeneroAvaliacao(genero, catalogos);
    }

   
    /**
     * Método que torna um cliente como especialista
     *  (o cliente é capaz de incluir um comentário para as mídias assistidas. Além disso, o cliente deve ter
     *  visto/avaliado pelo menos 5 mídias no mês passado)
     */
    public void tornarClienteEspecialista(){
        clienteAtual.tornarEspecialista();
    }

    /**
     *  Método que torna um cliente como profissional
     * (o cliente é capaz de incluir um comentário para as mídias assistidas. Além disso, o cliente é capaz de operar nas mídias que serão lançamentos)
     */
    public void tornarClienteProfissional(){
        clienteAtual.tornarProfissional();
    }

    /**
     * Método que torna um cliente standart (normal)
     * (O cliente nao pode avaliar e incluir um comentário para as mídias assistidas)
     */
    public void tornarClienteStandart(){
        clienteAtual.criarStandart();
    }
    

}