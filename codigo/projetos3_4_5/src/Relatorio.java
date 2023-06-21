import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class Relatorio {

    /**
     * Metodo que retorna as 10 midias de melhor avaliacao do catalogo, ordenada de modo decrescente
     * @return
     */

    public void midiasMelhorAvaliadas(HashMap<Integer,Catalogo> listCatalogo) {
        // depois ordena pelas mais avaliadas
        LinkedList<Catalogo> list = 
        streamDefault(c -> c.quantidadeAvaliacoes() >= 100, 
                     (a, b) -> { return a.mediaAvaliacao().compareTo(b.mediaAvaliacao()); },
                     listCatalogo);
        exibirRelatorioCatalogo(list, "melhor avaliacao");
    }

    /**
     * Metodo que retorna as 10 midias com mais visualizações do catalogo, ordenada de modo decrescente
     * @return
     */
    public void midiaComMaisVisualizacao(HashMap<Integer,Catalogo> listCatalogo) {
        LinkedList<Catalogo> list = 
        streamDefault(null, 
                     (a, b) -> { return Integer.compare(a.getAudiencia(), b.getAudiencia()); },
                     listCatalogo);

        exibirRelatorioCatalogo(list, "mais visualizadas");
    }
   
    /**
     * Método que retorna relatório por genero com 10 midias
     * @param genero
     * @return String
     */
    public void relatorioPorGeneroAudiencia(String genero, HashMap<Integer,Catalogo> listCatalogo) {
        LinkedList<Catalogo> list = 
        streamDefault(c -> c.getGenero().equals(genero), 
                     (a, b) -> { return Integer.compare(a.getAudiencia(), b.getAudiencia()); },
                     listCatalogo);

        exibirRelatorioCatalogo(list, "mais audiencia");
    }
    /**
     * Metodo que retorna relatorio por genero com 10 midias mais bem avaliadas
     * @return String
     * @param genero
     */
    public void relatorioPorGeneroAvaliacao(String genero, HashMap<Integer,Catalogo> listCatalogo) {
        LinkedList<Catalogo> list = 
        streamDefault(c -> c.getGenero().equals(genero),
                     (a, b) -> { return a.mediaAvaliacao().compareTo(b.mediaAvaliacao()); },
                     listCatalogo);

        exibirRelatorioCatalogo(list, "melhor avaliacao por genero");
    }

    public <k,T> LinkedList<T> streamDefault(Predicate<T> comparador, Comparator<T> sort, HashMap<k,T> list) {
        return list.values().stream()
                            .filter(comparador)
                            .sorted(sort)
                            .limit(10)
                            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Metodo que retorna qual cliente tem mais avaliacoes e quantas avaliacoes
     * @return
     */
    public void clienteComMaiorIndiceDeAvaliacao(HashMap<String,Cliente> clientesMap) {
        LinkedList<Cliente> clienteMaiorAva = 
        streamDefault(null,
                      (a, b) -> { return Integer.compare(a.getListaDeAvaliacoes().size(), b.getListaDeAvaliacoes().size()); },
                      clientesMap);
        
        exibirRelatorioUsuario(clienteMaiorAva.getLast(), clienteMaiorAva.getLast().getListaDeAvaliacoes().size(), "com maior avaliacoes");
    }

     /**
     * Metodo que retorna a porcentagem dos clientes com pelo menos 15 avaliações; coloquei 3 ali pra testar mais facil. Só alterar p 15 depois.
     * @return
     */
    public String calcularPorcentagemDeClienteComMinimoQuinzeAvaliacoes(HashMap<String,Cliente> clientesMap) {
        String porcentagemCliente;

        LinkedList<Cliente> clientesComAvaliacoesMinima = new LinkedList<Cliente>();
    
        for (String key: clientesMap.keySet()) {
            if(clientesMap.get(key).quantidadeDeAvaliacoes() >= 3){
                clientesComAvaliacoesMinima.add(clientesMap.get(key));
            }
        }

        porcentagemCliente = (clientesComAvaliacoesMinima.size() * 100) / clientesMap.size() + "%";

        return porcentagemCliente;
    }
    /**
     * Metodo que retorna qual cliente assistiu mais midias
     * @return
     */
    public void criarRelatorioClienteQueMaisAssistiu(HashMap<String,Cliente> clientesMap){
        LinkedList<Cliente> clientes = 
        streamDefault(c -> c.getListaJaVistas().size() > 0,
                      null,
                      clientesMap);

        exibirRelatorioUsuario(clientes.getLast(), clientes.getLast().getListaJaVistas().size(), "que mais assistiu");
    }

    public void criarRelatorioClienteComMaisAvaliacoes(HashMap<String,Cliente> clientesMap){
        LinkedList<Cliente> clientes = 
        streamDefault(c -> c.getListaDeAvaliacoes().size() > 0,
                      null,
                      clientesMap);
        exibirRelatorioUsuario(clientes.getLast(), clientes.getLast().getListaDeAvaliacoes().size(), "que mais avaliou");
    }

    public String criarRelatorioPorcentagemDeClienteNoMinQuinzeAvaliacoes(){

        return "Porcentagem total: ";
    }

    public void exibirRelatorioCatalogo(LinkedList<Catalogo> list, String titulo){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("---Exibindo Relatorio de Midias com "+ titulo + "---");
        stringBuilder.append("\n--------------------------------------------------------");
        for(Catalogo catalogo : list){
            stringBuilder.append("\n" + catalogo.toString());
        }
        stringBuilder.append("\n--------------------------------------------------------");

        System.out.println(stringBuilder);
    }

    public <T> void exibirRelatorioUsuario(Cliente cliente, Integer quantidade, String titulo){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("---Exibindo Relatorio do Usuario "+ titulo + "---");
        stringBuilder.append("\n--------------------------------------------------------");
        stringBuilder.append("\nCliente: " + cliente.getNomeUsuario() + ", Quantidade: "+ quantidade);
        stringBuilder.append("\n--------------------------------------------------------");

        System.out.println(stringBuilder);
    }

   
}
