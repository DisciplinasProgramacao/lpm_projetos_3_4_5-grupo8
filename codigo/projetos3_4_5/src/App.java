import java.util.LinkedList;
import java.util.Scanner;

public class App {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // LinkedList<Serie> a = new LinkedList<>();
        // Serie serie = new Serie(12, "mulher maravilha", "12-2-2022");
        // a.add(serie);
        // Leitura.gravar("testeSave", a);
        
        int opcao = -1;

        do {
            opcao = menuSites();
            limparTela();
            switch (opcao) {
                case 1:
                System.out.println("Carregando arquivo de Séries....");
                break;
                case 2:
                System.out.println("Carregando arquivo de Filmes....");
                break;
            }
         } while(opcao != 0);
        System.out.println("Saindo...");

    }

    public static int menuSites() {
        limparTela();
        System.out.println("Menu");
        System.out.println("==========================");
        System.out.println("1 - Carregar Séries");
        System.out.println("2 - Carregar Filmes");
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
}
