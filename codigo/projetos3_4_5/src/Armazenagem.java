import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.function.Function;

public class Armazenagem {

    /**
     * Metodo que grava qualquer objeto no arquivo .csv
     * 
     * @param nomeArq nome do arquivo .csv
     * @param item    objeto a ser salvo
     */
    public static <T> void gravar(String nomeArq, T item) throws IOException {
        gravarAppendDef(nomeArq, item, true);
    }

    /**
     * Metodo que grava qualquer objeto no arquivo .csv nao dando append
     * 
     * @param nomeArq nome do arquivo .csv
     * @param item    objeto a ser salvo
     */
    public static <T> void gravarReescrevendoArquivo(String nomeArq, T item) throws IOException {
        gravarAppendDef(nomeArq, item, false);
    }

    private static <T> void gravarAppendDef(String nomeArq, T item, boolean append) throws IOException{
        FileWriter arq = new FileWriter("./codigo/projetos3_4_5/arquivos/" + nomeArq + ".csv", append);
        PrintWriter gravarArq = new PrintWriter(arq);
        StringBuilder saida = new StringBuilder();
        if(append)
            saida.append("\n");
        saida.append(item.toString());
        gravarArq.write(saida.toString());
        arq.close();
    }

    /**
     * Metodo que le um arquivo e retorna seu conteudo em uma lista de string, no qual cada item da lista representa uma linha
     * 
     * @param metodo  contrutor da classe
     * 
     * @param nomeArq nome do arquivo .csv
     */
    public static <T> LinkedList<T> ler(String nomeArq, Function<String, T> metodo) throws FileNotFoundException {
        LinkedList<T> list = new LinkedList<>();
        File file = new File("./codigo/projetos3_4_5/arquivos/" + nomeArq + ".csv");
        Scanner entrada = new Scanner(file, "UTF-8");
        entrada.nextLine();
        while (entrada.hasNext()) {
            list.add(metodo.apply(entrada.nextLine()));
        }
        entrada.close();
        return list;
    }

}
