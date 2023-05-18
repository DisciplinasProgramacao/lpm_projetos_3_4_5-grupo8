import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class App {
    static Scanner teclado = new Scanner(System.in);
    static LinkedList<Catalogo> listaDeNovasSeries = new LinkedList<Catalogo>();
    static LinkedList<Catalogo> listaDeNovosFilmes = new LinkedList<Catalogo>();
    static Cliente ClienteTeste = new Cliente("Arthur", "arthur", "1234");

    public static void main(String[] args) throws Exception {
        PlataformaStreaming plataforma = new PlataformaStreaming("JoaoCaramflix");
        int opcao;

        do {
            opcao = menuSites();
            limparTela();
            switch (opcao) {
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
                    System.out.println(plataforma.getCatalogo());
                    pausa();
                    break;
                case 4:
                    System.out.println("Exibindo filmes novos...");
                    for (Catalogo catalogo : listaDeNovosFilmes) {
                        System.out.println("#================================#");
                        System.out.println(catalogo.toString());
                    }
                    pausa();
                    break;
                case 5:
                    cadastrarFilme(plataforma);
                    System.out.println("Filme cradastrado com sucesso!");
                    pausa();
                    break;
                case 6:
                    criarSerie(plataforma);
                    System.out.println("Série criada com sucesso!");
                    pausa();
                    break;
                case 7:
                    System.out.println("Exibindo séries novas...");
                    for (Catalogo catalogo : listaDeNovasSeries) {
                        System.out.println("#================================#");
                        System.out.println(catalogo.toString());
                    }
                    pausa();
                    break;
                case 8:
                    cadastrarCliente(plataforma);
                    pausa();
                    break;
                case 9:
                    avaliarCatalogo(ClienteTeste);
                    pausa();
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
        System.out.println("Saindo...");

    }

    public static int menuSites() {
        limparTela();
        System.out.println("Menu");
        System.out.println("==========================");
        System.out.println("1 - Carregar Séries e filmes");
        System.out.println("2 - Carregar Usuarios");
        System.out.println("3 - Exibir Catalagos");
        System.out.println("4 - Exibir filmes novos");
        System.out.println("5 - Criar e salvar Filme");
        System.out.println("6 - Criar e salvar Série");
        System.out.println("7 - Exibir séries novas");
        System.out.println("8 - Cadastrar Cliente");
        System.out.println("9 - Avaliar Series e Filmes Vistos");
        System.out.println("==========================");
        System.out.print("\nDigite sua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());

        return opcao;
    }

    private static void avaliarCatalogo(Cliente cliente) {
        cliente.CatalogoJaVisto();
        int numero = 0;
        System.out.println("Digite o numero de qual voce quer avaliar: ");
        numero = Integer.parseInt(teclado.nextLine());
        Catalogo catalogo = cliente.EscolherCatalogo(numero);
        System.out.println("Qual sua nota de 0 a 10 para sua " + catalogo.getNome() + ": ");
        numero = Integer.parseInt(teclado.nextLine());
        cliente.avaliar(numero, catalogo);
    }

    private static void criarSerie(PlataformaStreaming plataformaStreaming) {
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
        plataformaStreaming.adicionarCatalogo(serie);
        listaDeNovasSeries.add(serie);
        try {
            Armazenagem.gravar("POO_Series", serie);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cadastrarFilme(PlataformaStreaming plataformaStreaming) {
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
        plataformaStreaming.adicionarCatalogo(filme);
        listaDeNovasSeries.add(filme);

        try {
            Armazenagem.gravar("POO_Filmes", filme);
        } catch (IOException e) {
            e.printStackTrace();
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

    public static void cadastrarCliente(PlataformaStreaming plataformaStreaming) {
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

        if (plataformaStreaming.adicionarCliente(novoCliente)) {
            System.out.println("Cliente adicionado com sucesso!");
        } else {
            System.out.println("Login inválido, já existe cliente cadastrado com esse login");
        }
        ;
    }
}
