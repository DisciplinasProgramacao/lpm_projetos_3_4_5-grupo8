import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class Relatorio {
    private final HashMap<String, Cliente> clientes;
    private final HashMap<Integer, Catalogo> catalogos;

    public Relatorio(HashMap<String, Cliente> clientes, HashMap<Integer, Catalogo> catalogos) {
        this.clientes = clientes;
        this.catalogos = catalogos;
    }

    /**
     * Metodo que retorna as 10 midias de melhor avaliacao do catalogo, ordenada de modo decrescente
     * @return
     */

    public void midiasMelhorAvaliadas() {
        // depois ordena pelas mais avaliadas
        LinkedList<Catalogo> list = streamDefault(c -> ((Catalogo) c).quantidadeAvaliacoes() >= 100, 
                            (a, b) -> { return ((Catalogo) a).mediaAvaliacao().compareTo(((Catalogo) b).mediaAvaliacao()); });
        this.exibirRelatorio(list, "melhor avaliacao");
    }

    /**
     * Metodo que retorna as 10 midias com mais visualizações do catalogo, ordenada de modo decrescente
     * @return
     */
    public void midiaComMaisVisualizacao() {
        LinkedList<Catalogo> list = streamDefault(null, 
                            (a, b) -> { return Integer.compare(((Catalogo) a).getAudiencia(), ((Catalogo) b).getAudiencia()); });
        this.exibirRelatorio(list, "mais visualizadas");
    }

    public LinkedList<Catalogo> streamDefault(Predicate comparador, Comparator sort) {
        return (LinkedList<Catalogo>) catalogos.values().stream()
                                                        .filter(comparador)
                                                        .sorted(sort)
                                                        .limit(10)
                                                        .collect(Collectors.toCollection(LinkedList::new));
    }

   
    /**
     * Método que retorna relatório por genero com 10 midias
     * @param genero
     * @return String
     */
    public String relatorioPorGeneroAudiencia(String genero) {
        LinkedList<Catalogo> catalogos = this.catalogos.values().stream()
                                                                .filter(c -> c.getGenero().equals(genero))
                                                                .sorted((a, b) -> { return Integer.compare(a.getAudiencia(), b.getAudiencia()); })
                                                                .limit(10)
                                                                .collect(Collectors.toCollection(LinkedList::new));

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Relatório por gênero: " + genero + "\n");

        int contador = 0;

        for (Catalogo midia : catalogos) {
            contador++;
            stringBuilder.append("\n[" + contador + "] " + midia.getNome());
        }

        return stringBuilder.toString();
    }
    /**
     * Metodo que retorna relatorio por genero com 10 midias mais bem avaliadas
     * @return String
     * @param genero
     */
    public void relatorioPorGeneroAvaliacao(String genero) {
        LinkedList<Catalogo> catalogos = this.catalogos.values().stream()
                                                                .filter(c -> c.getGenero().equals(genero))
                                                                .sorted((a, b) -> { return ((Catalogo) a).mediaAvaliacao().compareTo(((Catalogo) b).mediaAvaliacao()); })
                                                                .limit(10)
                                                                .collect(Collectors.toCollection(LinkedList::new));

        // StringBuilder stringBuilder = new StringBuilder();

        // stringBuilder.append("Relatório por gênero: " + genero + "\n");

        // int contador = 0;

        // for (Catalogo midia : catalogos) {
        //     contador++;
        //     stringBuilder.append("\n[" + contador + "] " + midia.getNome());
        // }


        this.exibirRelatorio(catalogos, "relatorioPorGeneroAvaliacao");
    }

    /**
     * Metodo que retorna qual cliente tem mais avaliacoes e quantas avaliacoes
     * @return
     */
    public String clienteComMaiorIndiceDeAvaliacao() {
        LinkedList<Cliente> clienteMaiorAva = new LinkedList<Cliente>();
        String clienteComMaiorAvaliacao = "";

        for (String key: this.clientes.keySet()) {
            clienteMaiorAva.add(this.clientes.get(key));
        }

        Collections.sort(clienteMaiorAva, (a, b) -> { return Integer.compare(a.getListaDeAvaliacoes().size(), b.getListaDeAvaliacoes().size()); });
        
        clienteComMaiorAvaliacao = "Cliente: " + clienteMaiorAva.getLast() +  "; Qtd avaliacoes: " + clienteMaiorAva.getLast().getListaDeAvaliacoes().size();

        return clienteComMaiorAvaliacao;
    }

     /**
     * Metodo que retorna a porcentagem dos clientes com pelo menos 15 avaliações; coloquei 3 ali pra testar mais facil. Só alterar p 15 depois.
     * @return
     */
    public String calcularPorcentagemDeClienteComMinimoQuinzeAvaliacoes(){
        String porcentagemCliente;

        LinkedList<Cliente> clientesComAvaliacoesMinima = new LinkedList<Cliente>();
    
        for (String key: this.clientes.keySet()) {
            if(clientes.get(key).quantidadeDeAvaliacoes() >= 3){
                clientesComAvaliacoesMinima.add(this.clientes.get(key));
            }
        }

        porcentagemCliente = (clientesComAvaliacoesMinima.size() * 100) / this.clientes.size() + "%";

        return porcentagemCliente;
    }
    /**
     * Metodo que retorna qual cliente assistiu mais midias
     * @return
     */
    public String criarRelatorioClienteQueMaisAssistiu(){
        LinkedList<Cliente> clientes = this.clientes.values().stream()
                                                              .filter(c -> c.getListaJaVistas().size() > 0)
                                                              .collect(Collectors.toCollection(LinkedList::new));
      
        return "Cliente que mais assistiu: " + clientes.getLast().getNomeUsuario() + ", total: " + clientes.getLast().getListaJaVistas().size() ;
    }

    public String criarRelatorioClienteComMaisAvaliacoes(){
        LinkedList<Cliente> clientes = this.clientes.values().stream()
                                                              .filter(c -> c.getListaDeAvaliacoes().size() > 0)
                                                              .collect(Collectors.toCollection(LinkedList::new));

        return "Cliente que mais avaliou: " + clientes.getLast().getNomeUsuario() + ", total avaliacoes: " + clientes.getLast().getListaDeAvaliacoes().size() ; 
    }

    public String criarRelatorioPorcentagemDeClienteNoMinQuinzeAvaliacoes(){

        return "Porcentagem total: ";
    }

    public void exibirRelatorio(LinkedList<Catalogo> list, String titulo){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("---Exibindo Relatorio de Midias com "+ titulo + "---");
        stringBuilder.append("\n--------------------------------------------------------");
        for(Catalogo catalogo : list){
            stringBuilder.append("\n" + catalogo.toString());
        }
        stringBuilder.append("\n--------------------------------------------------------");

        System.out.println(stringBuilder);
    }

   
}
