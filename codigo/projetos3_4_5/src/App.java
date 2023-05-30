import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Scanner;

public class App {
    static Scanner teclado = new Scanner(System.in);
    static LinkedList<Catalogo> listaDeNovasSeries = new LinkedList<Catalogo>();
    static LinkedList<Catalogo> listaDeNovosFilmes = new LinkedList<Catalogo>();
    static PlataformaStreaming plataforma;

    public static void main(String[] args) throws Exception {
        int opcao;
        plataforma = new PlataformaStreaming("JoaoCaramflix");
        do {
            opcao = menuFlix();
            limparTela();
            switch (opcao) {
                case 1:
                    System.out.println("Carregando arquivo Catalogo com séries e filmes...");
                    plataforma.carregarCatalogos();
                    break;
                case 2:
                    System.out.println("Carregando arquivo de Usuarios...");
                    plataforma.carregarCliente();
                    break;
                case 3:
                    try {
                        System.out.println("---Exibindo catalogo---");
                        System.out.println(plataforma.getCatalogo());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Erro. Voce deve carregar o arquivo Catalogo antes de solicitar sua exibicao.");
                    }
                    break;
                case 4:
                    cadastrarFilme();
                    break;
                case 5:
                    cadastrarSerie();
                    break;
                case 6:
                    cadastrarCliente();
                    break;
                case 7:
                    if (realizarLogin()) {
                        menuCliente();
                    } else{
                        System.out.println("Login invalido. Favor tentar logar novamente ou realizar seu cadastro.");
                    }
                    break;
                case 8:
                    System.out.println("Falta implementar audiencia"); //fazer o metodo
                    break;
                case 9:
                System.out.println("---Exibindo catalogo---");
                    System.out.println(plataforma.getCatalogo());

                    exibirMediaAvaliacaoCatalogo();
                    break;
                default:
                    break;
            }
            pausa();
        } while (opcao != 0);
        System.out.println("Obrigado por utilizar o " + plataforma.getNome() + "! Ate breve :)");
    }

    //Menu geral da plataforma que oferece serviços de streaming
    public static int menuFlix() {
        limparTela();
        System.out.println("Menu " + plataforma.getNome());
        System.out.println("=================================================");
        System.out.println("1 - Carregar arquivo Catalogo");
        System.out.println("2 - Carregar arquivo Usuario");
        System.out.println("3 - Exibir Catalogo");
        System.out.println("4 - Cadastrar e Salvar Filme");
        System.out.println("5 - Cadastrar e Salvar Serie");
        System.out.println("6 - Cadastrar Cliente");
        System.out.println("7 - Realizar login de um cliente");
        System.out.println("8 - Verificar audiencia de uma serie");
        System.out.println("9 - Verificar avaliacao de uma midia");
        System.out.println("0 - Sair");
        System.out.println("=================================================");
        System.out.print("\nDigite sua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());

        return opcao;
    }

    //Opcoes para o cliente (após logar)
    public static int opcoesCliente() {
        limparTela();
        System.out.println("Ola, bem vindo ao " + plataforma.getNome());
        System.out.println("==========================================================");
        System.out.println("1 - Carregar arquivo Catalogo");
        System.out.println("2 - Exibir Catalogo");
        System.out.println("3 - Assistir uma midia");
        System.out.println("4 - Verificar midias assistidas");
        System.out.println("5 - Verificar midias para assistir futuramente");
        System.out.println("6 - Adicionar midia na lista para assistir futuramente");
        System.out.println("7 - Filtrar catalogo");
        System.out.println("8 - Filtrar midias assistidas");
        System.out.println("9 - Filtrar midias para assistir futuramente");
        System.out.println("10 - Avaliar uma midia");
        System.out.println("0 - Sair");
        System.out.println("==========================================================");
        System.out.print("\nDigite sua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());
       
        return opcao;
    }

    //Menu para o cliente
    public static void menuCliente() {
            int op = opcoesCliente(); 
            do{            
                switch (op) {
                    case 1:
                        System.out.println("Carregando arquivo Catalogo com séries e filmes...");
                        plataforma.carregarCatalogos();
                        pausa();
                        break;
                    case 2:
                        try {
                            System.out.println("---Exibindo catalogo---");
                            System.out.println(plataforma.getCatalogo());
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Carregue o arquivo Catalogo antes de solicitar sua exibicao.");
                        }
                        pausa();
                        break;
                    case 3:
                        System.out.println("\n---Exibindo catalogo para ser assistido---");
                        plataforma.visualizarListaParaVerFuturamente();  
                        System.out.println("\nDigite o nome da midia que deseja assistir: "); 
                        String midiaEscolhida = teclado.nextLine();
                        
                        boolean verificaMidiaEscolhida;
                        verificaMidiaEscolhida = plataforma.assistirMidia(midiaEscolhida);
                    
                        if(verificaMidiaEscolhida){
                            System.out.println("\nAssistindo midia... \nMidia assistida.");
                        }
                        else{
                            System.out.println("Midia nao encontrada. Adicione uma midia na lista para assistir futuramente, para conseguir assistir.");
                        }

                        System.out.println("\nDeseja avaliar essa midia assistida? Digite '1' para sim e '2' para nao: ");
                        int opcao = Integer.parseInt(teclado.nextLine());
                        while(opcao != 1 && opcao != 2){
                            System.out.println("Opcao invalida. Digite '1' para sim e '2' para nao: ");
                            opcao = Integer.parseInt(teclado.nextLine());
                        }
                        if(opcao == 1){
                            avaliarMidia();
                        }
                        if(opcao == 2){
                            System.out.println("\nObrigado por assistir!");
                        }

                        pausa();
                        break;
                    case 4:
                        String listaAssistidos;
                        listaAssistidos = plataforma.visualizarListaDeAssistidos();
                        System.out.println(listaAssistidos);
                        pausa();
                        break;
                    case 5:
                        plataforma.visualizarListaParaVerFuturamente();  
                        pausa();
                    case 6:
                        plataforma.carregarCatalogos();
                        System.out.println("---Exibindo catalogo---");
                        System.out.println(plataforma.getCatalogo());

                        System.out.println("\nDigite o nome da midia que deseja assistir futuramente: "); 
                        String midia = teclado.nextLine();

                        try{
                            plataforma.adicionarMidiaNaListaParaVerFuturamente(midia);
                        } catch(Exception e){
                            System.out.println("\nMidia nao encontrada. Digite o nome da midia novamente: ");
                            midia = teclado.nextLine();
                            plataforma.adicionarMidiaNaListaParaVerFuturamente(midia);
                        }
                        pausa();
                        break;
                    case 7:
                        menuFiltro(); //catalogo
                        pausa();
                        break;
                    case 8:
                       //midia assistidas
                        menuFiltro();
                        pausa();
                        break;
                    case 9:
                        //assistir futuramente
                        menuFiltro();
                        pausa();
                        break;
                    case 10:
                        plataforma.carregarCatalogos();
                        System.out.println("---Exibindo catalogo---");
                        System.out.println(plataforma.getCatalogo());
                        avaliarMidia();
                        pausa();
                        break; 
                } 
                op = opcoesCliente();
            } while(op != 0);
            realizarLogoff();       
    }

    //Opcoes filtros
    public static int opcoesFiltro(){
        limparTela();
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
        int opcao = Integer.parseInt(teclado.nextLine());

        return opcao;
    }
   
    //Menu para os filtros
    public static void menuFiltro() {
        int op = opcoesFiltro(); 
        do{            
            switch (op) {
                case 1:
                    System.out.println("Para filtrar digite o genero: ");
                    String genero = teclado.nextLine();
                    plataforma.filtrarPorGenero(genero); 
                    pausa();
                    break;
                case 2:
                    System.out.println("Para filtrar digite o idioma: "); 
                    String idioma = teclado.nextLine(); 
                    plataforma.filtrarPorGenero(idioma); 
                    pausa();
                    break;
                case 3:
                    System.out.println("Para filtrar digite a quantidade de episodios: "); 
                    int qtd = Integer.parseInt(teclado.nextLine());
                    plataforma.filtrarPorQtdEpisodios(qtd); 
                    pausa();
                    break;
                case 4:
                    System.out.println("Para filtrar digite a duracao em minutos: "); 
                    int duracao = Integer.parseInt(teclado.nextLine());
                    plataforma.filtrarPorDuracao(duracao);  
                    pausa();
                    break;
                case 5:
                    System.out.println("Para filtrar digite o nome: "); 
                    String nome = teclado.nextLine();
                    plataforma.buscarCatalogo(nome); 
                    pausa();
                    break;
            } 
            op = opcoesFiltro();
        } while(op != 0);
              
}

    //metodo que limpa a tela no terminal
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //metodo que faz uma pausa 
    static void pausa() {
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }

    //Metodo para cadastrar um filme na plataforma
    private static void cadastrarFilme() {
        System.out.println("==========================");
        System.out.println("--Cadastro de Filme--");
        System.out.print("Por gentileza, digite o nome do filme: ");
        String nome = teclado.nextLine();
        System.out.print("Digite a data de lançamento: ");
        String dataLancamento = teclado.nextLine();
        System.out.print("Digite o Genero: ");
        String genero = teclado.nextLine();
        System.out.print("Digite o Idioma: ");
        String idioma = teclado.nextLine();
        System.out.print("Digite a duracao: ");
        int duracao = Integer.parseInt(teclado.nextLine());

        Filme filme = new Filme(nome, dataLancamento, genero, idioma, duracao);
        plataforma.adicionarCatalogo(filme);
        listaDeNovasSeries.add(filme);

        System.out.println("Filme cradastrado com sucesso!");
    }

    //Metodo para cadastrar uma serie na plataforma
    private static void cadastrarSerie() {
        System.out.println("==========================");
        System.out.println("--Cadastro de Serie--");
        System.out.print("Digite o nome da serie: ");
        String nome = teclado.nextLine();
        System.out.print("Digite a data de lançamento: ");
        String dataLancamento = teclado.nextLine();
        System.out.print("Digite o genero: ");
        String genero = teclado.nextLine();
        System.out.print("Digite o idioma: ");
        String idioma = teclado.nextLine();
        System.out.print("Digite a quantidade de episodios: ");
        int quantidadeEpisodios = Integer.parseInt(teclado.nextLine());

        Serie serie = new Serie(nome, dataLancamento, genero, idioma, quantidadeEpisodios);
        plataforma.adicionarCatalogo(serie);
        listaDeNovasSeries.add(serie);

        System.out.println("Serie cradastrada com sucesso!");
    }

    //Metodo para cadastrar um cliente na plataforma
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

        Cliente novoCliente = new Cliente(nome, nomeUsuario, senha);

        if (plataforma.adicionarCliente(novoCliente)) {
            System.out.println("\nCliente adicionado com sucesso!");
        } else {
            System.out.println("\nLogin invalido, já existe cliente cadastrado com esse login");
        }

    }

    //Metodo para realizar o login de um cliente na plataforma
    public static boolean realizarLogin() {
        System.out.println("==========================");
        System.out.println("--Realizando login--");
        System.out.println("Digite seu login: ");
        String login = teclado.nextLine();
        System.out.println("Digite sua senha: ");
        String senha = teclado.nextLine();
        return ((plataforma.login(login, senha) != null) ? true : false) ;
    }

    //Metodo para realizar o logoff de um cliente na plataforma
    public static void realizarLogoff() {
        plataforma.logoff();
        System.out.println("==========================");
        System.out.println("--Realizado logoff--");    
    }

     //Metodo para avaliar uma midia
     public static void avaliarMidia() {
        System.out.println("==========================");
        System.out.println("--Avaliando midia--");
        System.out.println("\nDigite o nome da midia que deseja avaliar: ");
        String texto = teclado.nextLine();
        Catalogo catalogo = plataforma.buscarCatalogo(texto);
        
        System.out.println("Deixe seu comentario sobre a midia: ");
        texto = teclado.nextLine();

        System.out.println("Digite sua avaliacao com estrelas de 1 a 5: ");
        int avaliacao = Integer.parseInt(teclado.nextLine());

        while (avaliacao > 5 || avaliacao < 1) {
            System.out.println("Numero invalido para a avaliacao. Digite sua avaliacao novamente: ");
            avaliacao = Integer.parseInt(teclado.nextLine());
        }
        //System.out.println("Midia: '" + catalogo.getNome() + "', avaliada com " + avaliacao + "estrelas.");

        plataforma.adicionarAvaliacao(avaliacao, texto, catalogo);
    }

    //Metodo para exibira media de avaliacao de uma midia
    public static void exibirMediaAvaliacaoCatalogo() {
        System.out.println("==========================");
        System.out.println("--Realizando avaliacao--");
        System.out.println("\nDigite o nome da midia que deseja avaliarr: ");
        String texto = teclado.nextLine();
        Catalogo catalogo = plataforma.buscarCatalogo(texto);
        BigDecimal media =  plataforma.mediaAvaliacao(catalogo);
        System.out.println("A avaliacao da midia " + catalogo.getNome() + " possui media de: "+ media);
    }

   

}
