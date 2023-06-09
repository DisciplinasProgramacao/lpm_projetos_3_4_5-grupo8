import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class App {
    static Scanner teclado = new Scanner(System.in);
    static PlataformaStreaming plataforma;

    public static void main(String[] args) {
        int opcao;
        plataforma = new PlataformaStreaming("JoaoCaramflix");
        carregarArquivos();
        do {
            opcao = menuFlix();
            limparTela();
            switch (opcao) {
                case 1:
                    try {
                        System.out.println("---Exibindo catalogo---");
                        System.out.println(plataforma.getCatalogo());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Erro. Nao existem midias cadastradas no catalogo. " + e);
                    }
                    break;
                case 2:
                    cadastrarFilme();
                    break;
                case 3:
                    cadastrarSerie();
                    break;
                case 4:
                    cadastrarCliente();
                    break;
                case 5:
                    if (realizarLogin()) {
                        menuCliente();
                    } else {
                        System.out.println("Login invalido. Favor tentar logar novamente ou realizar seu cadastro.");
                    }
                    break;
                case 6:
                    String nomeMidia;
                    System.out.println("Digite o nome da midia que deseja verificar a audiencia: ");
                    nomeMidia = teclado.nextLine();

                    try {
                        Catalogo catalogoVerificado = plataforma.buscarCatalogo(nomeMidia);
                        System.out
                                .println("A audiencia de '" + nomeMidia + "' e: " + catalogoVerificado.getAudiencia());
                    } catch (NullPointerException e) {
                        System.out.println("\nMidia nao encontrada na plataforma. Verique se voce digitou o nome da midia corretamente.\n" + e);
                    }

                    break;
                case 7:
                    /*
                     * System.out.println("---Exibindo catalogo---");
                     * System.out.println(plataforma.getCatalogo());
                     */
                    try {
                        exibirMediaAvaliacaoCatalogo();
                    } catch (NullPointerException e) {
                        System.out.println("\nMidia nao encontrada na plataforma. Verique se voce digitou o nome corretamente.\n" + e);
                    }

                    break;
                case 8:
                   menuRelatorio();
                    break;
                default:
                    break;
            }
            pausa();
        } while (opcao != 0);
        System.out.println("Obrigado por utilizar o " + plataforma.getNome() + "! Ate breve :)");
    }

    // Menu geral da plataforma que oferece serviços de streaming
    public static int menuFlix() {
        limparTela();
        int opcao=-1;
        do{
            System.out.println("Menu " + plataforma.getNome());
            System.out.println("=================================================");
            System.out.println("1 - Exibir Catalogo");
            System.out.println("2 - Cadastrar e Salvar Filme");
            System.out.println("3 - Cadastrar e Salvar Serie");
            System.out.println("4 - Cadastrar Cliente");
            System.out.println("5 - Realizar login de um cliente");
            System.out.println("6 - Verificar audiencia de uma midia");
            System.out.println("7 - Verificar avaliacao de uma midia");
            System.out.println("8 - Visualizar Relatorios");
            System.out.println("0 - Sair");
            System.out.println("=================================================");
            System.out.print("\nDigite sua opção: ");
            try {
                opcao = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida.");
            }
        }while(!(opcao>=0  && opcao <=8));
        return opcao;
    }

    // Opcoes para o cliente (após logar)
    public static int opcoesCliente() {
        limparTela();
        int opcao = -1;
        do{
            System.out.println("Ola, bem vindo ao " + plataforma.getNome());
            System.out.println("==========================================================");
            System.out.println("1 - Exibir Catalogo");
            System.out.println("2 - Assistir uma midia");
            System.out.println("3 - Verificar midias assistidas");
            System.out.println("4 - Verificar midias para assistir futuramente");
            System.out.println("5 - Adicionar midia na lista para assistir futuramente");
            System.out.println("6 - Filtrar catalogo");
            System.out.println("7 - Filtrar midias assistidas");
            System.out.println("8 - Filtrar midias para assistir futuramente");
            System.out.println("9 - Avaliar uma midia");
            System.out.println("10 - Alterar seu estado");
            System.out.println("11 - Assistir Lançamento");
            System.out.println("0 - Sair");
            System.out.println("==========================================================");
            System.out.print("\nDigite sua opção: ");
            try {
                opcao = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida.");
            }
        }while(!(opcao>=0  && opcao <=11));

        return opcao;
    }

    // Menu para o cliente
    public static void menuCliente() {
        int op = opcoesCliente();
        do {
            switch (op) {
                case 1:
                    try {
                        System.out.println("---Exibindo catalogo---");
                        System.out.println(plataforma.getCatalogo());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Carregue o arquivo Catalogo antes de solicitar sua exibicao.");
                    }
                    pausa();
                    break;
                case 2:
                    System.out.println("\n---Exibindo catalogo para ser assistido---");
                    System.out.println(plataforma.visualizarListaParaVerFuturamente());
                    System.out.println("\nDigite o nome da midia que deseja assistir: ");
                    String midiaEscolhida = teclado.nextLine();

                    try {
                        plataforma.assistirMidia(midiaEscolhida);
                        System.out.println("\nAssistindo midia... \nMidia assistida.");
                        System.out
                                .println("\nDeseja avaliar essa midia assistida? Digite '1' para sim e '2' para nao: ");
                        int opcao = -1;
                        while (opcao != 1 && opcao != 2) {
                            try {
                                opcao = Integer.parseInt(teclado.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Opcao invalida.");
                                System.out.println("Digite '1' para sim e '2' para nao: ");
                            }  
                        }
                        if (opcao == 1) {
                            avaliarMidia();
                        }
                        if (opcao == 2) {
                            System.out.println("\nObrigado por assistir!");
                        }
                    } catch (NullPointerException e) {
                        System.out.println("Erro no usuário");
                    } catch (IndexOutOfBoundsException a) {
                        System.out.println(
                                "Midia nao encontrada. Para conseguir assistir, adicione uma midia na lista para assistir futuramente.");
                    } catch (IllegalArgumentException b){
                        System.out.println("Nao pode assistir lancamento");
                    }

                    pausa();
                    break;
                case 3:
                    System.out.println(plataforma.visualizarListaDeAssistidos());
                    pausa();
                    break;
                case 4:
                    System.out.println(plataforma.visualizarListaParaVerFuturamente());
                    pausa();
                    break;
                case 5:
                    System.out.println("---Exibindo catalogo---");
                    System.out.println(plataforma.getCatalogo());

                    System.out.println("\nDigite o nome da midia que deseja assistir futuramente: ");
                    String midia = teclado.nextLine();

                    try {
                        plataforma.adicionarMidiaNaListaParaVerFuturamente(midia);
                    } catch (NullPointerException e) {
                        System.out.println("\nMidia nao encontrada.");
                    } catch (IndexOutOfBoundsException a) {
                        System.out.println("\nCatalogo vazio.");
                    }
                    pausa();
                    break;
                case 6:
                    menuFiltro(); 
                    pausa();
                    break;
                case 7:
                    menuFiltro();
                    pausa();
                    break;
                case 8:
                    menuFiltro();
                    pausa();
                    break;
                case 9:
                    System.out.println("---Midias assistidas---");
                    System.out.println(plataforma.visualizarListaDeAssistidos());
                    avaliarMidia();
                    pausa();
                    break;
                case 10:
                    alterarEstadoCliente();
                    break;
                case 11:
                    System.out.println("--Exibindo Lançamentos--");
                    System.out.println(plataforma.lancamentos());
                    System.out.println("\nDigite o nome da midia que deseja assistir: ");
                    String midiaEscolhidaLancamento = teclado.nextLine();

                    try {
                        plataforma.adicionarMidiaNaListaParaVerFuturamente(midiaEscolhidaLancamento);
                        plataforma.assistirMidia(midiaEscolhidaLancamento);
                        System.out.println("\nAssistindo midia... \nMidia assistida.");
                        System.out
                                .println("\nDeseja avaliar essa midia assistida? Digite '1' para sim e '2' para nao: ");
                        int opcao = -1;
                        while (opcao != 1 && opcao != 2) {
                            try {
                                opcao = Integer.parseInt(teclado.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Opcao invalida.");
                                System.out.println("Digite '1' para sim e '2' para nao: ");
                            }  
                        }
                        if (opcao == 1) {
                            avaliarMidia();
                        }
                        if (opcao == 2) {
                            System.out.println("\nObrigado por assistir!");
                        }
                    } catch (NullPointerException e) {
                        System.out.println("Erro no usuário");
                    } catch (IndexOutOfBoundsException a) {
                        System.out.println(
                                "Midia nao encontrada. Para conseguir assistir, adicione uma midia na lista para assistir futuramente.");
                    } catch (IllegalArgumentException b){
                        System.out.println("Nao pode assistir lancamento");
                    }
                    pausa();
                    break;
                default:
                    break;
            }
            op = opcoesCliente();
        } while (op != 0);
        realizarLogoff();
    }

    // Opcoes filtros
    public static int opcoesFiltro() {
        limparTela();
        int opcao=-1;
        do{
            System.out.println("Menu Filtros. Escolha sua opcao: ");
            System.out.println("==========================================================");
            System.out.println("1 - Filtrar por genero");
            System.out.println("2 - Filtrar por idioma");
            System.out.println("3 - Filtrar por quantidade de episodios");
            System.out.println("4 - Filtrar por duracao em minutos");
            System.out.println("5 - Filtrar por nome");
            System.out.println("0 - Sair");
            System.out.println("==========================================================");
            System.out.print("\nDigite sua opção: ");
        try {
            opcao = Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opcao invalida.");
        }
        }while(!(opcao>=0  && opcao <=5));
        return opcao;
    }

    // Menu para os filtros
    public static void menuFiltro() {
        int op = opcoesFiltro();
        do {
            switch (op) {
                case 1:
                    opcoesGeneros();
                    System.out.println("Para filtrar digite o número correspondente ao genero: ");
                    int opcaoGenero;
                    String genero = "";
                    try{
                        opcaoGenero = Integer.parseInt(teclado.nextLine());
                        genero = EnumGeneros.values()[opcaoGenero-1].toString();
                    }catch(NumberFormatException e){
                        System.out.println("Input invalido. " + e);
                        return;
                    }catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("Opcao invalida, digite um numero valido. " + e);
                        return;
                    }
                    System.out.println(plataforma.filtrarCatalogo(genero));
                    pausa();
                    break;
                case 2:
                    System.out.println("Para filtrar digite o idioma: ");
                    String idioma = teclado.nextLine();
                    System.out.println(plataforma.filtrarCatalogo(idioma));
                    pausa();
                    break;
                case 3:
                    System.out.println("Para filtrar digite a quantidade de episodios: ");
                    int qtd;
                    try {
                        qtd = Integer.parseInt(teclado.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Opcao invalida.");
                        qtd = -1;
                    }
                    if(qtd >= 0)
                        System.out.println(plataforma.filtrarPorQtdEpisodios(qtd));
                    pausa();
                    break;
                case 4:
                    int duracao;
                    try {
                        duracao = Integer.parseInt(teclado.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Opcao invalida.");
                        duracao = -1;
                    }
                    if(duracao >= 0)
                    System.out.println(plataforma.filtrarPorDuracao(duracao));
                    pausa();
                    break;
                case 5:
                    System.out.println("Para filtrar digite o nome: ");
                    String nome = teclado.nextLine();

                    try {
                        plataforma.buscarCatalogo(nome);
                        System.out.println("A Midia [" + plataforma.buscarCatalogo(nome) + "] existe no catalogo.");
                    } catch (NullPointerException e) {
                        System.out.println("Midia nao encontrada.");
                    } catch (IndexOutOfBoundsException a) {
                        System.out.println("Catalogo vazio.");
                    }

                    pausa();
                    break;
                default:
                    break;
            }
            op = opcoesFiltro();
        } while (op != 0);

    }

    //Opcoes relatorios
    public static int opcoesRelatorio() {
        limparTela();
        int opcao = -1;
        do{
            System.out.println("Menu Relatorios. Escolha sua opcao: ");
            System.out.println("==========================================================");
            System.out.println("1 - Relatorio: Cliente que assistiu mais midias e o total");
            System.out.println("2 - Relatorio: Cliente que possui mais avaliacoes");
            System.out.println("3 - Relatorio: Porcentagem de clientes com pelo menos 15 avaliacoes");
            System.out.println("4 - Relatorio: 10 midias com melhor avalicao");
            System.out.println("5 - Relatorio: 10 midias com mais visualizacoes");
            System.out.println("6 - Relatorio: 10 midias com melhor avalicao - por genero");
            System.out.println("7 - Relatorio: 10 midias com mais visualizacoes - por genero");
            System.out.println("0 - Sair");
            System.out.println("==========================================================");
            System.out.print("\nDigite sua opção: ");
        try {
            opcao = Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opcao invalida.");
        }
        }while(!(opcao>=0  && opcao <=7));

        return opcao;
    }
        
    //Menu relatorios
     public static void menuRelatorio() {
        int op = opcoesRelatorio();
        do {
            switch (op) {
                case 1:
                    System.out.println("---Relatorio 1---");
                    plataforma.clienteQueMaisAssistiu();
                    pausa();
                    break;
                case 2:
                    System.out.println("---Relatorio 2---");
                    plataforma.clienteQueMaisAvaliou();
                    pausa();
                    break;
                case 3:
                    System.out.println("---Relatorio 3---");
                    plataforma.porcentagemClientes();
                    pausa();
                    break;
                case 4:
                    System.out.println("---Relatorio 4---");
                    plataforma.midiasMelhorAvaliadas();
                    pausa();
                    break;
                case 5:
                    System.out.println("---Relatorio 5---");
                    plataforma.midiaComMaisVisualizacao();
                    pausa();
                    break;
                case 6:
                    System.out.println("---Relatorio 6---");
                    System.out.println("Digite o numero do genero que deseja obter o relatório?");
                    int opcaoGenero;
                    String genero = "";
                    try{
                        opcaoGenero = Integer.parseInt(teclado.nextLine());
                        genero = EnumGeneros.values()[opcaoGenero-1].toString();
                    }catch(NumberFormatException e){
                        System.out.println("Input invalido. " + e);
                        return;
                    }catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("Opcao invalida, digite um numero valido. " + e);
                        return;
                    }
                    plataforma.relatorioPorGeneroAvaliacao(genero);
                    pausa();
                    break;
                case 7:
                    System.out.println("---Relatorio 7---");
                    System.out.println("Digite o numero do genero que deseja obter o relatório?");
                    int opcao;
                    String generoFiltro = "";
                    try{
                        opcao = Integer.parseInt(teclado.nextLine());
                        generoFiltro = EnumGeneros.values()[opcao-1].toString();
                    }catch(NumberFormatException e){
                        System.out.println("Input invalido. " + e);
                        return;
                    }catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("Opcao invalida, digite um numero valido. " + e);
                        return;
                    }
                    plataforma.relatorioPorGeneroAudiencia(generoFiltro);
                    pausa();
                    break;
                default:
                    break;
            }
            op = opcoesRelatorio();
        } while (op != 0);
    }

    // metodo que limpa a tela no terminal
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // metodo que faz uma pausa
    static void pausa() {
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }

    // Metodo para cadastrar um filme na plataforma
    private static void cadastrarFilme() {
        System.out.println("==========================");
        System.out.println("--Cadastro de Filme--");
        System.out.print("Por gentileza, digite o nome do filme: ");
        String nome = teclado.nextLine();
        System.out.print("Digite a data de lançamento: ");
        String dataLancamento = teclado.nextLine();
        
        opcoesGeneros();
        System.out.println("Digite o número correspondente ao genero: ");
        int opcaoGenero;
        String genero = "";
        try{
            opcaoGenero = Integer.parseInt(teclado.nextLine());
            genero = EnumGeneros.values()[opcaoGenero-1].toString();
            System.out.print("Digite o Idioma: ");
            String idioma = teclado.nextLine();
            System.out.print("Digite a duracao: ");
            int duracao = Integer.parseInt(teclado.nextLine());
            System.out.print("Lancamento? se sim digite 1, se não digite 2: ");
            int lancamento = Integer.parseInt(teclado.nextLine());
            plataforma.cadastrarMidia(nome, dataLancamento, genero, idioma, duracao, 0, lancamento == 1 ? true : false);
            System.out.println("Filme cadastrado com sucesso!");
        }catch(NumberFormatException e){
            System.out.println("Input invalido. " + e);
            return;
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Opcao invalida, digite um numero valido. " + e);
            return;
        } catch (IOException e) {
            System.out.println("Erro ao adicionar filme ao catalogo ou ao gravar no arquivo\n" + e);
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar filme\n" + e);
            return;
        }
    }

    private static void opcoesGeneros(){
        System.out.println("Generos: ");
        int contador = 0;
        for(EnumGeneros generos : EnumGeneros.values()){
            System.out.println("[" + ++contador + "] " + generos.getDescricao());
        }
    }
    
    // Metodo para cadastrar uma serie na plataforma
    private static void cadastrarSerie() {
        System.out.println("==========================");
        System.out.println("--Cadastro de Serie--");
        System.out.print("Digite o nome da serie: ");
        String nome = teclado.nextLine();
        System.out.print("Digite a data de lançamento: ");
        String dataLancamento = teclado.nextLine();
        
        opcoesGeneros();
        System.out.println("Digite o número correspondente ao genero: ");
        int opcaoGenero;
        String genero = "";
        try{
            opcaoGenero = Integer.parseInt(teclado.nextLine());
            genero = EnumGeneros.values()[opcaoGenero-1].toString();
            System.out.print("Digite o idioma: ");
            String idioma = teclado.nextLine();
            System.out.print("Digite a quantidade de episodios: ");
            int quantidadeEpisodios = Integer.parseInt(teclado.nextLine());
            System.out.print("Lancamento? se sim digite 1, se não digite 2: ");
            int lancamento = Integer.parseInt(teclado.nextLine());
            plataforma.cadastrarMidia(nome, dataLancamento, genero, idioma, 0, quantidadeEpisodios, lancamento == 1 ? true : false);
            System.out.println("Serie cadastrada com sucesso!");
        }catch(NumberFormatException e){
            System.out.println("Input invalido. " + e);
            return;
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Opcao invalida, digite um numero valido. " + e);
            return;
        }catch (IOException e) {
            System.out.println("Erro ao adicionar serie ao catalogo ou ao gravar no arquivo\n" + e);
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar serie\n" + e);
            return;
        }
    }

    // Metodo para cadastrar um cliente na plataforma
    public static void cadastrarCliente() {
        String nome, nomeUsuario, senha;
        System.out.println("==========================");
        System.out.println("--Cadastro de Cliente--");

        System.out.println("Nome: ");
        nome = teclado.nextLine();
        System.out.println("login: ");
        nomeUsuario = teclado.nextLine();
        System.out.println("Senha: ");
        senha = teclado.nextLine();
        // mudar, app nao pode ter acesso a classe Cliente

        try {
            plataforma.cadastrarCliente(nome, nomeUsuario, senha);
            System.out.println("\nCliente adicionado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("\nLogin invalido, já existe cliente cadastrado com esse login\n" + e);
        } catch (IOException e) {
            System.out.println("Erro na criação do cliente no arquivo\n" + e);
        }

    }

    // Metodo para realizar o login de um cliente na plataforma
    public static boolean realizarLogin() {
        System.out.println("==========================");
        System.out.println("--Realizando login--");
        System.out.println("Digite seu login: ");
        String login = teclado.nextLine();
        System.out.println("Digite sua senha: ");
        String senha = teclado.nextLine();
        return ((plataforma.login(login, senha) != null) ? true : false);
    }

    // Metodo para realizar o logoff de um cliente na plataforma
    public static void realizarLogoff() {
        plataforma.logoff();
        System.out.println("==========================");
        System.out.println("--Realizado logoff--");
        pausa();
    }

    // Metodo para avaliar uma midia
    public static void avaliarMidia() {

        System.out.println("==========================");
        System.out.println("\nDigite o nome da midia que deseja avaliar: ");
        String nomeMidia = teclado.nextLine();

        try {
            String texto = "";
            plataforma.buscarCatalogo(nomeMidia);
            if(plataforma.getClienteAtual().podeComentar()){
                System.out.println("Deixe seu comentario sobre a midia: ");
                texto = teclado.nextLine();
            }
            System.out.println("Digite sua avaliacao com estrelas de 1 a 5: ");
            int avaliacao = Integer.parseInt(teclado.nextLine());

            while (avaliacao > 5 || avaliacao < 1) {
                System.out.println("Numero invalido para a avaliacao. Digite sua avaliacao novamente: ");
                avaliacao = Integer.parseInt(teclado.nextLine());
            }            

            if(plataforma.adicionarAvaliacao(avaliacao, texto, plataforma.buscarCatalogo(nomeMidia)) == null){
                System.out.println("Midia ja avaliada, avaliacao nao adicionada ou não pode comentar");
            }else{
                System.out.println("Midia: '" + nomeMidia + "', avaliada com " + avaliacao + "estrelas.");
                System.out.println("Avaliacao realizada com sucesso");
            };
        } catch (NullPointerException e) {
            System.out.println("Midia não encontrada");
        } catch (IndexOutOfBoundsException a) {
            System.out.println("Catalogo vazio");
        }catch(NumberFormatException e){
            System.out.println("Input invalido. " + e);
            return;
        }
    }

    // Metodo para exibira media de avaliacao de uma midia
    public static void exibirMediaAvaliacaoCatalogo() {
        System.out.println("=======================================");
        System.out.println("--Verificando media de avaliacao--");
        System.out.println("\nDigite o nome da midia que deseja verificar media: ");
        String texto = teclado.nextLine();
        try {
            BigDecimal media = plataforma.mediaAvaliacao(plataforma.buscarCatalogo(texto));
            System.out.println(
                    "\nA midia '" + plataforma.buscarCatalogo(texto).getNome() + "' possui avaliacao media de: " + media
                            + " estrelas \n");
        } catch (NullPointerException e) {
            System.out.println("Midia nao encontrada. Verifique se digitou o nome corretamente.");
        }

    }

    public static void carregarArquivos(){
        System.out.println("Carregando arquivo Catalogo com séries e filmes...");
        try {
            plataforma.carregarCatalogos();
        } catch (FileNotFoundException e) {
            System.out.println("Erro na leitura dos arquivos de catalogo. " + e);
        }

        System.out.println("Carregando arquivo de Usuarios...");
        try {
            plataforma.carregarCliente();
        } catch (FileNotFoundException e) {
            System.out.println("Erro na leitura do arquivo de clientes. " + e);
        }
    }

    public static void alterarEstadoCliente(){
        System.out.println("=======================================");
        System.out.println("--Alterar estado cliente--");
        System.out.println("1- Tornar standart");
        System.out.println("2- Tornar especialista");
        System.out.println("3- Tornar profissional");
        System.out.print("\nDigite sua opção: ");
        int opcao = 0;
        try {
            opcao = Integer.parseInt(teclado.nextLine());
        } catch(NumberFormatException e){
            System.out.println("Numero invalido " + e);
            return;
        }
        
        switch(opcao){
            case 1:
                plataforma.tornarClienteStandart();
                break;
            case 2:
                plataforma.tornarClienteEspecialista();
                break;
            case 3:
                plataforma.tornarClienteProfissional();
                break;
        }
    }
}
