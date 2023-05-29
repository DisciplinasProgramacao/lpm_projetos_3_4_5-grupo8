import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class App {
    static Scanner teclado = new Scanner(System.in);
    static LinkedList<Catalogo> listaDeNovasSeries = new LinkedList<Catalogo>();
    static LinkedList<Catalogo> listaDeNovosFilmes = new LinkedList<Catalogo>();
    static PlataformaStreaming plataforma;
    static Cliente clienteTeste = new Cliente("Ana Luiza", "ana.luiza", "123");

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
                        System.out.println("Exibindo catalogo...");
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
       
                    if (realizarLogin() ) {
                       // subMenuCliente();
                        menuCliente();
                    } else{
                        System.out.println("Login invalido. Favor tentar logar novamente ou realizar seu cadastro.");
                    }
                    
                    break;
                case 8:
                    System.out.println("Falta implementar audiencia"); //fazer o metodo
                    break;
                case 9:
                    System.out.println("Falta implementar avaliacao"); //fazer o metodo
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

    //Menu de opcoes apos o cliente estar logado
    public static int subMenuCliente() {
        limparTela();
        System.out.println("Ola, bem vindo ao " + plataforma.getNome());
        System.out.println("==========================================================");
        System.out.println("1 - Carregar arquivo Catalogo");
        System.out.println("2 - Exibir Catalogo");
        System.out.println("3 - Assistir uma midia");
        System.out.println("4 - Verificar midias assistidas");
        System.out.println("5 - Verificar midias para assistir futuramente");
        System.out.println("6 - Adicionar midia na lista de assistidas");
        System.out.println("7 - Adicionar midia na lista para assistir futuramente");
        System.out.println("8 - Filtrar catalogo");
        System.out.println("9 - Filtrar midias assistidas");
        System.out.println("10 - Filtrar midias para assistir futuramente");
        System.out.println("11 - Avaliar uma midia");
        System.out.println("0 - Sair");
        System.out.println("==========================================================");
        System.out.print("\nDigite sua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());
       
        return opcao;
    }

    public static void menuCliente() {
        
            int op = subMenuCliente(); 
            do{            
                switch (op) {
                    case 1:
                        System.out.println("Carregando arquivo Catalogo com séries e filmes...");
                        plataforma.carregarCatalogos();
                        break;
                    case 2:
                        try {
                            System.out.println("Exibindo catalogo...");
                            System.out.println(plataforma.getCatalogo());
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Carregue o arquivo Catalogo antes de solicitar sua exibicao.");
                        }
                        break;
                    case 3:
                        System.out.println("Falta implementar escolha da midia"); //fazer o metodo 
                        pausa();
                        break;
                    case 4:
                        System.out.println("Falta implementar midias assistidas"); //fazer o metodo
                        pausa();
                        break;
                    case 5:
                        System.out.println("Falta implementar midias p assistir futuramente"); //fazer o metodo
                        pausa();
                        break;
                    case 6:
                        System.out.println("Falta implementar adc na lista assistida "); //fazer o metodo
                        pausa();
                        break;
                    case 7:
                        System.out.println("Falta implementar adc na lista p assistir futuramente"); //fazer o metodo
                        pausa();
                        break;
                    case 8:
                        System.out.println("Falta implementar filtro catalogo"); //fazer o metodo
                        pausa();
                        break;
                    case 9:
                        System.out.println("Falta implementar filtro assistido"); //fazer o metodo
                        pausa();
                        break;
                    case 10:
                        System.out.println("Falta implementar filtro assistir futuramente"); //fazer o metodo
                        pausa();
                        break;
                    case 11:
                        System.out.println("Falta implementar avaliacao midia"); //fazer o metodo
                        pausa();
                        break; 
                } 
                op = subMenuCliente();
            } while(op != 0);
            realizarLogoff();       
    }

    public static int subMenuFiltro(){
        limparTela();
        System.out.println("Menu Filtros");
        System.out.println("==========================================================");
        System.out.println("1 - Filtrar por genero");
        System.out.println("2 - Filtrar por idioma");
        System.out.println("3 - Filtrar por quantidade de episodios");
        System.out.println("4 - Filtrar por duracao em minutos");
        System.out.println("5 - Filtrar por nome");
        System.out.println("6 - Sair");
        System.out.println("==========================================================");
        System.out.print("\nDigite sua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());

        return opcao;
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
        System.out.print("Digite o nome da série: ");
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
        System.out.println("Cadastro de Cliente");

        System.out.println("Nome: ");
        nome = teclado.nextLine();
        System.out.println("Nome de Usuario (login): ");
        nomeUsuario = teclado.nextLine();
        System.out.println("Senha: ");
        senha = teclado.nextLine();

        Cliente novoCliente = new Cliente(nome, nomeUsuario, senha);

        if (plataforma.adicionarCliente(novoCliente)) {
            System.out.println("Cliente adicionado com sucesso!");
        } else {
            System.out.println("Login invalido, já existe cliente cadastrado com esse login");
        }

    }

    //Metodo para realizar o login de um cliente na plataforma
    public static boolean realizarLogin() {
        System.out.println("Digite seu login: ");
        String login = teclado.nextLine();
        System.out.println("Digite sua senha: ");
        String senha = teclado.nextLine();
        return ((plataforma.login(login, senha) != null) ? true : false) ;
    }

    //Metodo para realizar o logoff de um cliente na plataforma
    public static void realizarLogoff() {
        plataforma.logoff();    
        System.out.println("Saindo...");
    }

}
