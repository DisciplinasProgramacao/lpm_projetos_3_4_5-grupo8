import java.io.IOException;
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
            opcao = menuSites();
            limparTela();
            switch (opcao) {
                case 0:
                    System.out.println("Saindo...");
                    plataforma.logoff();
                    break;
                case 1:
                    System.out.println("Carregando arquivo de Séries e filmes...");
                    plataforma.carregarCatalogos();
                    break;
                case 2:
                    System.out.println("Carregando arquivo de Usuarios...");
                    plataforma.carregarCliente();
                    break;
                case 3:
                    System.out.println("Exibindo catálogo.");
                    try {
                        System.out.println(plataforma.getCatalogo());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Carregue o catalogo antes de exibir");
                    }
                    pausa();
                    break;
                case 4:
                    cadastrarFilme();
                    System.out.println("Filme cradastrado com sucesso!");
                    pausa();
                    break;
                case 5:
                    cadastrarSerie();
                    System.out.println("Serie cradastrada com sucesso!");
                    pausa();
                    break;
                case 6:
                    cadastrarCliente();
                    pausa();
                    break;
                case 7:
                    try {
                        escolherMidiaParaAssistir(plataforma.getClienteAtual());
                    } catch (NullPointerException e) { // cliente não logou
                    }
                    pausa();
                    break;
                case 8:
                    try {
                        avaliarCatalogo(plataforma.getClienteAtual());
                    } catch (NullPointerException e) { // cliente não logou
                    }

                    pausa();
                    break;
                default:
                    break;
            }
        } while (opcao != 0);

    }

    public static int menuSites() {
        limparTela();
        System.out.println("Menu " + plataforma.getNome());
        System.out.println("==========================");
        System.out.println("1 - Carregar Séries e filmes");
        System.out.println("2 - Carregar Usuarios");
        System.out.println("3 - Exibir Catalogo da plataforma");
        System.out.println("4 - Cadastrar Filme");
        System.out.println("5 - Cadastrar Serie");
        System.out.println("6 - Cadastrar Cliente");
        System.out.println("7 - Escolher midia para assistir");
        System.out.println("8 - Avaliar Series e Filmes assistidos");
        System.out.println("0 - Sair");
        System.out.println("==========================");
        System.out.print("\nDigite sua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());

        return opcao;
    }

    private static void avaliarCatalogo(Cliente cliente) {
        System.out.println(cliente.listarMidiasAssistidas());
        int numero = 0;

        System.out.println("Digite o numero de qual voce quer avaliar: ");
        numero = Integer.parseInt(teclado.nextLine());

        Catalogo catalogo = cliente.escolherCatalogo(numero);
        System.out.println("Digite sua estrela de 1 a 5 para " + catalogo.getNome() + ": ");
        numero = Integer.parseInt(teclado.nextLine());
        // cliente.avaliar(numero, catalogo);
    }

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
    }

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
    }

    public static void cadastrarCliente() {
        String nome, nomeUsuario, senha;
        System.out.println("==========================");
        System.out.println("Cadastro de Cliente");

        System.out.println("Nome: ");
        nome = teclado.nextLine();
        System.out.println("Nome de Usuário: ");
        nomeUsuario = teclado.nextLine();
        System.out.println("Senha: ");
        senha = teclado.nextLine();

        Cliente novoCliente = new Cliente(nome, nomeUsuario, senha);

        if (plataforma.adicionarCliente(novoCliente)) {
            System.out.println("Cliente adicionado com sucesso!");
        } else {
            System.out.println("Login inválido, já existe cliente cadastrado com esse login");
        }

    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa() {
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }

    // Metodo que permite o cliente avaliar uma midia ja vista posteriormente
    private static void avaliarCatalogo(Cliente cliente) {
        System.out.println("Ola " + cliente + "! Aqui esta toda sua midia assistida: ");
        System.out.println(cliente.listarMidiasAssistidas());
        int numero = 0;

        System.out.println("Digite o numero de qual midia voce deseja avaliar agora: ");
        numero = Integer.parseInt(teclado.nextLine());
        Catalogo catalogo = cliente.escolherCatalogo(numero);

        System.out.println("Digite sua estrela de 1 a 5 para " + catalogo.getNome() + ": ");
        numero = Integer.parseInt(teclado.nextLine());

        while (numero > 5 || numero < 1) {
            System.out.println("Numero invalido para a avaliacao. Digite sua avaliacao novamente: ");
            numero = Integer.parseInt(teclado.nextLine());
        }
        System.out.println("Midia: '" + catalogo.getNome() + "', avaliada com " + numero + "estrelas.");

        // fazer aqui a validacao se a midia ja foi avaliada ou nao!!

        // cliente.avaliar(numero, catalogo);
    }

    // Metodo que o cliente assiste uma midia e permite ele avaliar ou nao a midia
    // assistida
    public static void escolherMidiaParaAssistir(Cliente cliente) {
        int escolha = 0, numero = 0;

        System.out.println("Listando sua lista para assistir: ");
        String midiaParaAssistir = cliente.listarMidiasParaSeremAssistidas();

        System.out.println(midiaParaAssistir);

        /*
         * System.out.println("Digite o numero da midia voce deseja assistir: ");
         * numero = Integer.parseInt(teclado.nextLine());
         * 
         * System.out.println("Assistindo " + midiaParaAssistir.getNome() + "...");
         * System.out.
         * println("Midia assistida. Voce deseja avaliar agora a midia assistida? Caso sim digite 0, caso nao digite 1"
         * );
         * 
         * if (escolha==0){
         * avaliarCatalogo(ClienteTeste);
         * }
         * else{
         * System.out.println("Ok. Esperamos sua avaliacao em outro momento.");
         * }
         */

    }

}
