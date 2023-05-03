import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Armazenagem {
    public static <T> void gravar(String nomeArq, LinkedList<T> list) throws IOException {
        FileWriter arq = new FileWriter("./codigo/projetos3_4_5/arquivos/" + nomeArq + ".csv");
        PrintWriter gravarArq = new PrintWriter(arq);
        StringBuilder saida = new StringBuilder();
        for (T key : list) {
            saida.append(key.toString());
            saida.append("\n");
        }
        String saidaAux = saida.toString();
        saidaAux = saidaAux.substring(0, saidaAux.length() - 1);
        gravarArq.write(saidaAux);
        arq.close();
    }

    public static LinkedList<Catalogo> lerSerie(String nomeArq) throws FileNotFoundException {
        LinkedList<Catalogo> list = new LinkedList<>();
    public static LinkedList<String> ler(String nomeArq) throws FileNotFoundException {
        LinkedList<String> list = new LinkedList<>();
        File file = new File("./codigo/projetos3_4_5/arquivos/" + nomeArq + ".csv");
        Scanner entrada = new Scanner(file, "UTF-8");
        String linha;
        String linhaAux[];
        Boolean flag = true;
        while (entrada.hasNext()) {
            linha = entrada.nextLine();
            linhaAux = linha.split(";");
            if (flag) {
                linhaAux[0] = linhaAux[0].substring(1, linhaAux[0].length());
                linha = linha.substring(1, linha.length());
                flag = false;
            }
            Serie x = new Serie(Integer.parseInt(linhaAux[0]), linhaAux[1], linhaAux[2]);
            list.add(x);
            list.add(linha);
        }
        entrada.close();
        return list;
    }

}
