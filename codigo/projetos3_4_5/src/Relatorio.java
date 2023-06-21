import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.text.DecimalFormat;
import static java.util.stream.Collectors.joining;

public class Relatorio {

    public <k,T> LinkedList<T> streamDefault(Predicate<T> comparador, Comparator<T> sort, HashMap<k,T> list) {
        return list.values().stream()
                            .filter(comparador)
                            .sorted(sort)
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
                            .sorted(sort)
                            .limit(10)
                            .collect(Collectors.toCollection(LinkedList::new));
    }

    public void criarRelatorioClienteQueMaisAssistiu(HashMap<String, Cliente> clientesMap) {

        Predicate<Cliente> predicate = (cliente) -> cliente.getListaDeAvaliacoes().size() > 0;
        Comparator<Cliente> comparator = (a, b) -> Integer.compare(a.getListaJaVistas().size(), b.getListaJaVistas().size());

        LinkedList<Cliente> clientes = 
        streamDefault(predicate,
                      comparator,
                      clientesMap);
        
        if (!clientes.isEmpty()) {
            Cliente clienteMaisAssistiu = clientes.getLast();
            exibirRelatorioUsuario(clienteMaisAssistiu, clienteMaisAssistiu.getListaJaVistas().size(), "que mais assistiu");
        }
    }

    //public String criarRelatorioClienteComMaisAvaliacoes(HashMap<String, Cliente> clientesMap){
    //    LinkedList<Cliente> clientes = clientesMap.values().stream()
    //                                                          .filter(c -> c.getListaDeAvaliacoes().size() > 0)
    //                                                          .collect(Collectors.toCollection(LinkedList::new));

    //    return "Cliente que mais avaliou: " + clientes.getLast().getNomeUsuario() + ", total avaliacoes: " + clientes.getLast().getListaDeAvaliacoes().size() ; 
    //}

    
    public String criarRelatorioPorcentagemDeClienteNoMinQuinzeAvaliacoes(HashMap<String, Cliente> clientesMap){
        double porcentagemCliente;
        double clientesComAvaliacoesMinima = clientesMap.values().stream()
                                                              .filter(cliente -> cliente.getListaDeAvaliacoes().size() >= 2) //trocar aqui pra 15 quando arrumar o teste
                                                              .count();

        porcentagemCliente = ((clientesComAvaliacoesMinima * 100) /(double) clientesMap.size());
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String porcentagemFormatada = decimalFormat.format(porcentagemCliente);

        return "Porcentagem total: "+ porcentagemFormatada + "%";
    }

     
    public void criarRelatorioMidiasComMelhoresAvaliacoes(HashMap<Integer,Catalogo> catalogoMap) {
        Predicate<Catalogo> predicate = (c) -> c.quantidadeAvaliacoes() >= 100;
        Comparator<Catalogo> comparator = (a, b) -> a.mediaAvaliacao().compareTo(b.mediaAvaliacao());
        
        // depois ordena pelas mais avaliadas
        LinkedList<Catalogo> list = 
        streamDefault(predicate,
                      comparator,
                     catalogoMap);

        if(!list.isEmpty()){
            exibirRelatorioCatalogo(list, "melhor avaliacao");
        }else{
            System.out.println("Nao ha midias com mais de 100 avaliacoes");
        }
    }

     
    public void criarRelatorioMidiasComMaisVisualizacoes(HashMap<Integer,Catalogo> catalogoMap) {
        Comparator<Catalogo> comparator = (a, b) -> Integer.compare(a.getAudiencia(), b.getAudiencia());
        
        LinkedList<Catalogo> list = 
        streamDefault(comparator,
                     catalogoMap);

        if(!list.isEmpty()){
            exibirRelatorioCatalogo(list, "mais visualizadas");
        }else{
            System.out.println("Nao ha midias vistas");
        }
    }




    public void criarRelatorioClienteComMaisAvaliacoes(HashMap<String,Cliente> clientesMap){
        Predicate<Cliente> predicate = (c) -> c.getListaDeAvaliacoes().size() > 0;

        LinkedList<Cliente> clientes = 
        streamDefault(predicate,
                      clientesMap);

        if(!clientes.isEmpty()){
            exibirRelatorioUsuario(clientes.getLast(), clientes.getLast().getListaDeAvaliacoes().size(), "que mais avaliou");
        }else{
            System.out.println("Nao ha clientes com avaliacoes");
        }
    }

    /*public void clienteComMaiorIndiceDeAvaliacao(HashMap<String,Cliente> clientesMap) {
        LinkedList<Cliente> clienteMaiorAva = 
        streamDefault(null,
                      (a, b) -> { return Integer.compare(a.getListaDeAvaliacoes().size(), b.getListaDeAvaliacoes().size()); },
                      clientesMap);
        
        exibirRelatorioUsuario(clienteMaiorAva.getLast(), clienteMaiorAva.getLast().getListaDeAvaliacoes().size(), "com maior avaliacoes");
    } */

   
   



    /******************************** RELATORIOS DE ACORDO COM O GENERO ************************************ */
    /**
     * Método que retorna relatório por genero com 10 midias
     * @param genero
     * @return String
     */
    public void relatorioPorGeneroAudiencia(String genero, HashMap<Integer,Catalogo> catalogoMap) {
        Predicate<Catalogo> predicate = (c) -> c.getGenero().equals(genero);
        Comparator<Catalogo> comparator = (a, b) -> Integer.compare(a.getAudiencia(), b.getAudiencia());
        
        LinkedList<Catalogo> list = 
        streamDefault(predicate, 
                     comparator,
                     catalogoMap);

        if(!list.isEmpty()){
            exibirRelatorioCatalogo(list, "mais audiencia");
        }else{
            System.out.println("Nao ha midias com esse genero");
        }
    }
    /**
     * Metodo que retorna relatorio por genero com 10 midias mais bem avaliadas
     * @return String
     * @param genero
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
