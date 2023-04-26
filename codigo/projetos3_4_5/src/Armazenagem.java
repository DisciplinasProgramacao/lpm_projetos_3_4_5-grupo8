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

    public static LinkedList<Serie> lerSerie(String nomeArq) throws FileNotFoundException {
        LinkedList<Serie> list = new LinkedList<>();
        File file = new File("./codigo/projetos3_4_5/arquivos/" + nomeArq + ".csv");
        Scanner entrada = new Scanner(file, "UTF-8");
        String linha;
        String linhaAux[];
        while (entrada.hasNext()) {
            linha = entrada.nextLine();
            linhaAux = linha.split(";");
            Serie x = new Serie(Integer.parseInt(linhaAux[0]), linhaAux[1], linhaAux[2]);
            list.add(x);
        }
        entrada.close();
        return list;
    }

    public static LinkedList<Cliente> lerCliente(String nomeArq) throws FileNotFoundException {
        LinkedList<Cliente> list = new LinkedList<>();
        File file = new File("./codigo/projetos3_4_5/arquivos/" + nomeArq + ".csv");
        Scanner entrada = new Scanner(file, "UTF-8");
        String linha;
        String linhaAux[];
        while (entrada.hasNext()) {
            linha = entrada.nextLine();
            linhaAux = linha.split(";");
            Cliente x = new Cliente(linhaAux[0], linhaAux[1], linhaAux[2]);
            list.add(x);
        }
        entrada.close();
        return list;
    }

    public static LinkedList<Cliente> lerAudiencia(String nomeArq, LinkedList<Cliente> list, LinkedList<Serie> series)
            throws FileNotFoundException {
        File file = new File("./codigo/projetos3_4_5/arquivos/" + nomeArq + ".csv");
        Scanner entrada = new Scanner(file, "UTF-8");
        String linha;
        String linhaAux[];
        while (entrada.hasNext()) {
            linha = entrada.nextLine();
            linhaAux = linha.split(";");
            for (Cliente x : list) {
                if (x.getLogin().equals(linhaAux[0])) {
                    if (linhaAux[1].equals("F")) {
                        for (Serie z : series) {
                            if (z.getId() == Integer.parseInt(linhaAux[2])) {
                                x.adicionarNaLista(z);
                            }
                        }
                    } else {
                        x.retirarDaLista(linhaAux[2]);
                    }
                }
            }
        }
        return list;
    }
}
