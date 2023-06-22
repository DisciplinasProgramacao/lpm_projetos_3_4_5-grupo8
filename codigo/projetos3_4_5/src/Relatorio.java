import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.text.DecimalFormat;

public class Relatorio {

    /**
     * Método construtor da classe Relatorio
     *
     * @param <k> 
     * @param <T>
     * @param comparador
     * @param sort
     * @param list
     * @return
     */
    public <k,T> LinkedList<T> streamDefault(Predicate<T> comparador, Comparator<T> sort, HashMap<k,T> list) {
        return list.values().stream()
                            .filter(comparador)
                            .sorted(sort.reversed())
                            .limit(10)
                            .collect(Collectors.toCollection(LinkedList::new));
    }

    public <k,T> LinkedList<T> streamDefault(Predicate<T> comparador, HashMap<k,T> list) {
        return list.values().stream()
                            .filter(comparador)
                            .limit(10)
                            .collect(Collectors.toCollection(LinkedList::new));
    }

    public <k,T> LinkedList<T> streamDefault(Comparator<T> sort, HashMap<k,T> list) {
        return list.values().stream()
                            .sorted(sort.reversed())
                            .limit(10)
                            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Método que cria um relatório de qual cliente assistiu mais mídias e quantas mídias.
     * 
     * O método filtra os clientes com pelo menos uma midia na sua lista de visualizações. 
     * Em seguida, contabiliza e classifica os clientes de acordo com o número de visualizações nas mídias. 
     * Por fim, retorna um relatório do cliente que mais assistiu conteúdos.
     * 
     * @param clientesMap (HashMap<String, Cliente> )
     * @return cliente que mais assistiu (string)
     */
    public String criarRelatorioClienteQueMaisAssistiu(HashMap<String, Cliente> clientesMap) {
        String retorno = "";

        LinkedList<Cliente> clientes = clientesMap.values().stream()
                .filter(c -> c.getListaJaVistas().size() > 0)
                .collect(Collectors.toCollection(LinkedList::new));
                Collections.sort(clientes, (a, b) -> { return Integer.compare(a.getListaJaVistas().size(), b.getListaJaVistas().size()); });
        
        if (!clientes.isEmpty()) {
            Cliente clienteMaisAssistiu = clientes.getLast();
            retorno = exibirRelatorioUsuario(clienteMaisAssistiu, clienteMaisAssistiu.getListaJaVistas().size(), "que mais assistiu");
        }
        return retorno;
    }

    /**
     * Método que cria um relatório de qual cliente tem mais avaliações, e quantas avaliações.
     * 
     * O método faz um stream buscando o cliente que possui o maior número de avaliações entre todos os clientes presentes no HashMap.
     * Caso haja clientes com avaliações é retornado qual cliente mais avaliou. Caso não, retorna uma mensagem que não há clientes com avaliações.
     * 
     * @param clientesMap (HashMap<String, Cliente> )
     * @return cliente que mais avaliou (string)
     */
    public void criarRelatorioClienteComMaisAvaliacoes(HashMap<String,Cliente> clientesMap){
        Predicate<Cliente> predicate = (c) -> c.getListaDeAvaliacoes().size() > 0;

        LinkedList<Cliente> clientes = streamDefault(predicate, clientesMap);

        if(!clientes.isEmpty()){
            exibirRelatorioUsuario(clientes.getLast(), clientes.getLast().getListaDeAvaliacoes().size(), "que mais avaliou");
        }else{
            System.out.println("Nao ha clientes com avaliacoes");
        }
    }
 

    /**
     * Método que cria um relatório, calculando a porcentagem dos clientes com pelo menos 15 avaliações;
     * 
     * O método recebe um HashMap de clientes que mapeia com a chave (cliente), usando stream e filtrando os que possuem no mínimo 15 avaliações.
     * 
     * @param clientesMap (HashMap<String, Cliente> )
     * @return porcentagem de clientes com pelo menos 15 avaliações (string)
     */
    public String criarRelatorioPorcentagemDeClienteNoMinQuinzeAvaliacoes(HashMap<String, Cliente> clientesMap){
        double porcentagemCliente;
        double clientesComAvaliacoesMinima = clientesMap.values().stream()
                                                              .filter(cliente -> cliente.getListaDeAvaliacoes().size() >= 15) //
                                                              .count();
    
        porcentagemCliente = ((clientesComAvaliacoesMinima * 100) /(double) clientesMap.size());
     
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String porcentagemFormatada = decimalFormat.format(porcentagemCliente);
       
        return exibirRelatorioPorcentagem(porcentagemFormatada);
    }
     

    /**
     * Método que cria um relatório de quais são as 10 mídias de melhor avaliação, com pelo menos 100 avaliações, em ordem decrescente.
     * 
     * O método recebe um HashMap de mídias do catálogo e usando stream, cria uma lista deas melhores mídias de acordo com a média de avaliação e a quantidade de avaliações.
     * 
     * @param catalogoMap (HashMap<Integer, Catalogo> )
     */
    public void criarRelatorioMidiasComMelhoresAvaliacoes(HashMap<Integer,Catalogo> catalogoMap) {
        Predicate<Catalogo> predicate = (c) -> c.quantidadeAvaliacoes() >= 100;
        Comparator<Catalogo> comparator = (a, b) -> a.mediaAvaliacao().compareTo(b.mediaAvaliacao());
        
        // depois ordena pelas mais avaliadas
        LinkedList<Catalogo> list = streamDefault(predicate, comparator,catalogoMap);

        if(!list.isEmpty()){
            exibirRelatorioCatalogo(list, "melhor avaliacao");
        }else{
            System.out.println("Nao ha midias com mais de 100 avaliacoes");
        }
    }

    /**
     * Método que cria um relatório de quais são as 10 mídias com mais visualizações, em ordem decrescente.
     * 
     * O método recebe um HashMap de mídias do catálogo e usando stream, cria uma lista das mídias mais vistas de acordo com a quantidade de visualizações(audiência).
     * 
     * @param catalogoMap (HashMap<Integer, Catalogo> )
     */
    public void criarRelatorioMidiasComMaisVisualizacoes(HashMap<Integer,Catalogo> catalogoMap) {
        Comparator<Catalogo> comparator = (a, b) -> Integer.compare(a.getAudiencia(), b.getAudiencia());
        
        LinkedList<Catalogo> list = streamDefault(comparator, catalogoMap);

        if(!list.isEmpty()){
            exibirRelatorioCatalogo(list, "mais visualizadas");
        }else{
            System.out.println("Nao ha midias vistas");
        }
    }



    /******************************** RELATORIOS DE ACORDO COM O GENERO ************************************ */
    /**
     * Método que retorna relatório de quais são as 10 mídias com mais visualizações, em ordem decrescente, filtradas pelo gênero.
     *
     * O método recebe um HashMap de mídias do catálogo e usando stream, cria uma lista das mídias mais vistas de acordo com a quantidade de visualizações(audiência) e o gênero.
     * 
     * @param genero (String)
     * @param catalogoMap (HashMap<Integer, Catalogo> )
     * @return lista com 1o midias mais visualizadas pelo genero (string) 
     */
    public String relatorioPorGeneroAudiencia(String genero, HashMap<Integer,Catalogo> catalogoMap) {
        String retorno = "";

        Predicate<Catalogo> predicate = (c) -> c.getGenero().equals(genero);
        Comparator<Catalogo> comparator = (a, b) -> Integer.compare(a.getAudiencia(), b.getAudiencia());
        
        LinkedList<Catalogo> list = streamDefault(predicate, comparator, catalogoMap);

        if(!list.isEmpty()){
            retorno = exibirRelatorioCatalogo(list, "mais audiencia");
        }else{
            System.out.println("Nao ha midias com esse genero");
        }
        return retorno;
    }
    /**
     * Método que retorna relatório de quais são as 10 mídias com as melhores avaliacoes, em ordem decrescente, filtradas pelo gênero.
     * 
     * O método recebe um HashMap de mídias do catálogo e usando stream, cria uma lista das mídias com melhor avaliação de acordo com a média de avaliação e o gênero.
     * 
     * @param genero (String)
     * @param catalogoMap (HashMap<Integer, Catalogo> )
     * 
     */
    public void relatorioPorGeneroAvaliacao(String genero, HashMap<Integer,Catalogo> catalogoMap) {
        Predicate<Catalogo> predicate = (c) -> c.getGenero().equals(genero);
        Comparator<Catalogo> comparator = (a, b) -> a.mediaAvaliacao().compareTo(b.mediaAvaliacao());

        LinkedList<Catalogo> list = 
        streamDefault(predicate,
                     comparator,
                     catalogoMap);
        if(!list.isEmpty()){
            exibirRelatorioCatalogo(list, "melhor avaliacao por genero");
        }else{
            System.out.println("Nao ha midias com esse genero");
        }
    }

  
    /******************************** EXIBICAO DOS RELATORIOS *****************************/

    /**
     * Método que exibe o relatório de midias do Catálogo
     * @param list (LinkedList<Catalogo>)
     * @param titulo (String)
     * @return String formatada com o relatório
     */
    public String exibirRelatorioCatalogo(LinkedList<Catalogo> list, String titulo){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("---Exibindo Relatorio de Midias com "+ titulo + "---");
        stringBuilder.append("\n--------------------------------------------------------");
        for(Catalogo catalogo : list){
            stringBuilder.append("\n" + catalogo.toString());
        }
        stringBuilder.append("\n--------------------------------------------------------");

        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

    /**
     * Método que exibe o relatório de usuários
     * @param cliente (Cliente)
     * @param quantidade (Integer)
     * @param titulo (String)
     * @return String formatada com o relatório
     */
    public <T> String exibirRelatorioUsuario(Cliente cliente, Integer quantidade, String titulo){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("---Exibindo Relatorio do Usuario "+ titulo + "---");
        stringBuilder.append("\n--------------------------------------------------------");
        stringBuilder.append("\nCliente: " + cliente.getNomeUsuario() + ", Quantidade: "+ quantidade);
        stringBuilder.append("\n--------------------------------------------------------");

        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

    /**
     * Método que exibe o relatório de porcentagem de usuários
     * @param porcentagem (String)
     * @return String formatada com o relatório
     */
    public <T> String exibirRelatorioPorcentagem( String porcentagem){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("---Exibindo Relatorio da porcentagem dos clientes com pelo menos 15 avalia\u00E7\u00F5es ---");
        stringBuilder.append("\n--------------------------------------------------------");
        stringBuilder.append("\nPorcentagem total: " + porcentagem + " %");
        stringBuilder.append("\n--------------------------------------------------------");

        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }
   
}
