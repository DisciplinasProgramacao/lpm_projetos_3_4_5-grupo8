import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Leitura {
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

    private static <T> LinkedList<T> ler(String nomeArq) throws FileNotFoundException {
        LinkedList<T> list = new LinkedList<>();
        File file = new File("./codigo/projetos3_4_5/arquivos/" + nomeArq + ".csv");
        Scanner entrada = new Scanner(file, "UTF-8");
        String linha;
        String linhaAux[];
        while (entrada.hasNext()) {
            linha = entrada.nextLine();
            linhaAux = linha.split(";");
            T x = new T(linhaAux[0], linhaAux[1], linhaAux[2]);
            list.add(x);

        }
        return list;

    }
}
