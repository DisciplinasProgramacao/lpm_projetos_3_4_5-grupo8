import java.util.LinkedList;
import java.util.Scanner;

public class App {
    static Scanner teclado = new Scanner(System.in);
    static LinkedList<Catalogo> listaDeNovasSeries = new LinkedList<Catalogo>();
    static Cliente ClienteTeste = new Cliente("Arthur", "arthur", "1234");

    public static void main(String[] args) throws Exception {
        PlataformaStreaming plataforma = new PlataformaStreaming("JoaoCaramflix");
        int opcao = -1;

        do {
            opcao = menuSites();
            limparTela();
            switch (opcao) {
                case 1:
                    System.out.println("Carregando arquivo de Séries...");
                    plataforma.adicionarCatalogos(Armazenagem.lerSerie("POO_Series"));
                    break;
                case 2:
                    System.out.println("Carregando arquivo de Filmes...");
                    plataforma.adicionarCatalogos(Armazenagem.lerSerie("POO_Series"));
                    break;
                case 3:
                    System.out.println("Carregando arquivo de Usuarios...");
                    plataforma.adicionarCliente(Armazenagem.lerCliente("POO_Series"));
                    break;
                case 4:
                    System.out.println("Exibindo catálogo.");
                    System.out.println(plataforma.getCatalogo());
                    pausa();
                    break;
                case 7:
                    Catalogo serie = criarSerie();
                    plataforma.adicionarCatalogo(serie);
                    System.out.println("Série criada com sucesso!");
                    listaDeNovasSeries.add(serie);
                    pausa();
                    break;
                case 8:
                    System.out.println("Exibindo séries novas...");
                    for(Catalogo catalogo : listaDeNovasSeries){
                        System.out.println("#================================#");
                        System.out.println(catalogo.toString());
                    }
                    pausa();
                    break;
                case 9:
                    Serie.salvar("POO_Series_salvar", listaDeNovasSeries);
                    System.out.println("Serie salva com sucesso!");
                    pausa();
                    break;
                case 10:
                    cadastrarCliente(plataforma);
                    pausa();
                    break;
                case 11:
                    avaliarCatalogo(ClienteTeste);
                    pausa();
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
        System.out.println("Saindo...");

    }

    private static void avaliarCatalogo(Cliente cliente) {
        cliente.CatalogoJaVisto();
        int numero = 0;
        System.out.println("Digite o numero de qual voce quer avaliar: ");
        numero = Integer.parseInt(teclado.nextLine());
        Catalogo catalogo = cliente.EscolherCatalogo(numero);
        System.out.println("Qual sua nota de 0 a 10 para sua "+catalogo.getNome()+": ");
        numero = Integer.parseInt(teclado.nextLine());
        catalogo.avaliar(numero);
    }

    private static Serie criarSerie() {
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
        return new Serie(0, nome, dataLancamento, genero, idioma, quantidadeEpisodios);
    }

    public static int menuSites() {
        limparTela();
        System.out.println("Menu");
        System.out.println("==========================");
        System.out.println("1 - Carregar Séries");
        System.out.println("2 - Carregar Filmes");
        System.out.println("3 - Carregar Usuarios");
        System.out.println("4 - Exibir Catalagos");
        System.out.println("7 - Criar Série");
        System.out.println("8 - Exibir séries novas");
        System.out.println("9 - Salvar Série");
        System.out.println("10 - Cadastrar Cliente");
        System.out.println("11 - Avaliar Series e Filmes Vistos");
        System.out.println("==========================");
        System.out.print("\nDigite sua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());

        return opcao;
    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa() {
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }

    public static void cadastrarCliente(PlataformaStreaming plataformaStreaming){
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
        
        if(plataformaStreaming.adicionarCliente(novoCliente)){
            System.out.println("Cliente adicionado com sucesso!");
        }else{
            System.out.println("Login inválido, já existe cliente cadastrado com esse login");
        };
    }
}
