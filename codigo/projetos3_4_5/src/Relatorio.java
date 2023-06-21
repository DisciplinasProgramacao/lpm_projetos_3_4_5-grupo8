import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.text.DecimalFormat;

public class Relatorio {

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

    public String criarRelatorioClienteQueMaisAssistiu(HashMap<String, Cliente> clientesMap) {
        String retorno = "";

        Predicate<Cliente> predicate = (cliente) -> cliente.getListaJaVistas().size() > 0;
        Comparator<Cliente> comparator = (a, b) -> Integer.compare(a.getListaJaVistas().size(), b.getListaJaVistas().size());

        LinkedList<Cliente> clientes = 
        streamDefault(predicate,
                      comparator,
                      clientesMap);
        
        if (!clientes.isEmpty()) {
            Cliente clienteMaisAssistiu = clientes.getLast();
            retorno = exibirRelatorioUsuario(clienteMaisAssistiu, clienteMaisAssistiu.getListaJaVistas().size(), "que mais assistiu");
        }
        return retorno;
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
 

    
    public String criarRelatorioPorcentagemDeClienteNoMinQuinzeAvaliacoes(HashMap<String, Cliente> clientesMap){
        double porcentagemCliente;
        double clientesComAvaliacoesMinima = clientesMap.values().stream()
                                                              .filter(cliente -> cliente.getListaDeAvaliacoes().size() >= 15) //
                                                              .count();
    
        porcentagemCliente = ((clientesComAvaliacoesMinima * 100) /(double) clientesMap.size());
    
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String porcentagemFormatada = decimalFormat.format(porcentagemCliente);
        System.out.println(porcentagemFormatada);

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



    /******************************** RELATORIOS DE ACORDO COM O GENERO ************************************ */
    /**
     * Método que retorna relatório por genero com 10 midias
     * @param genero
     * @return String
     */
    public String relatorioPorGeneroAudiencia(String genero, HashMap<Integer,Catalogo> catalogoMap) {
        String retorno = "";

        Predicate<Catalogo> predicate = (c) -> c.getGenero().equals(genero);
        Comparator<Catalogo> comparator = (a, b) -> Integer.compare(a.getAudiencia(), b.getAudiencia());
        
        LinkedList<Catalogo> list = 
        streamDefault(predicate, 
                     comparator,
                     catalogoMap);

        if(!list.isEmpty()){
            retorno = exibirRelatorioCatalogo(list, "mais audiencia");
        }else{
            System.out.println("Nao ha midias com esse genero");
        }
        return retorno;
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

    public <T> String exibirRelatorioUsuario(Cliente cliente, Integer quantidade, String titulo){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("---Exibindo Relatorio do Usuario "+ titulo + "---");
        stringBuilder.append("\n--------------------------------------------------------");
        stringBuilder.append("\nCliente: " + cliente.getNomeUsuario() + ", Quantidade: "+ quantidade);
        stringBuilder.append("\n--------------------------------------------------------");

        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

   
}
