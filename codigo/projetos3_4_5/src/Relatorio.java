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

   public String criarRelatorioClienteQueMaisAssistiu(){ //ok
        

        return "Cliente que mais assistiu: , total:";
    }

    public String criarRelatorioClienteComMaisAvaliacoes(){ //ok

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
