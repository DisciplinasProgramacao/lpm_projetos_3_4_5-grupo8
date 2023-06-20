import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class Relatorio {
    private final HashMap<String, Cliente> clientes;
    private final HashMap<Integer, Catalogo> catalogo;

    public Relatorio(HashMap<String, Cliente> clientes, HashMap<Integer, Catalogo> catalogo) {
        this.clientes = clientes;
        this.catalogo = catalogo;
    }

    
    public String criarRelatorioClienteQueMaisAssistiu(){
        

        return "Cliente que mais assistiu: , total:";
    }

    /* Metodo alternativo para o cliente com mais avaliacoes usando streams FALTA TESTAR
    public String criarRelatorioClienteComMaisAvaliacoes(LinkedList<Cliente> clientes){
        Cliente cliente = Collections.max(clientes.stream().collect(Collectors.toMap(c -> c, c -> c.getListaDeAvaliacoes().size())).entrySet(), Map.Entry.comparingByValue()).getKey();

        return "Cliente com mais avaliacoes: " + cliente.getNomeUsuario() + ", com "
                + cliente.getListaDeAvaliacoes().size() + " avaliacoes";
    }*/


    public String criarRelatorioClienteComMaisAvaliacoes(){

        return "Cliente que mais avaliou: ";
    }

    public String criarRelatorioPorcentagemDeClienteNoMinQuinzeAvaliacoes(){

        return "Porcentagem total: ";
    }

    public String criarRelatorioDezMelhoresMidiasAvaliadas(){

        return "10 melhores midias avaliadas: ";
    }

    public String criarRelatorioDezMelhoresMidiasAssistidas(){

        return "10 melhores midias assistidas: ";
    }

    public String criarRelatorioDezMelhoresMidiasAvaliadasPorGenero(){

        return "10 melhores midias assistidas: ";
    }

    public String criarRelatorioDezMelhoresMidiasAssistidasPorGenero(){

        return "10 melhores midias assistidas: ";
    }

   
}
