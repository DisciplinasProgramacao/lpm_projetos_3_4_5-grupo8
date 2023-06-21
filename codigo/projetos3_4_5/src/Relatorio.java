import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class Relatorio {

    public <k,T> LinkedList<T> streamDefault(Predicate<T> comparador, Comparator<T> sort, HashMap<k,T> list) {
        return list.values().stream()
                            .filter(comparador)
                            .sorted(sort)
                            .limit(10)
                            .collect(Collectors.toCollection(LinkedList::new));
    }


    
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

    
    /*public void clienteComMaiorIndiceDeAvaliacao(HashMap<String,Cliente> clientesMap) {
        LinkedList<Cliente> clienteMaiorAva = 
        streamDefault(null,
                      (a, b) -> { return Integer.compare(a.getListaDeAvaliacoes().size(), b.getListaDeAvaliacoes().size()); },
                      clientesMap);
        
        exibirRelatorioUsuario(clienteMaiorAva.getLast(), clienteMaiorAva.getLast().getListaDeAvaliacoes().size(), "com maior avaliacoes");
    } */

    /*
    public void criarRelatorioMidiasComMelhoresAvaliacoes(HashMap<Integer,Catalogo> listCatalogo) {
        // depois ordena pelas mais avaliadas
        LinkedList<Catalogo> list = 
        streamDefault(c -> c.quantidadeAvaliacoes() >= 100, 
                     (a, b) -> { return a.mediaAvaliacao().compareTo(b.mediaAvaliacao()); },
                     listCatalogo);
        exibirRelatorioCatalogo(list, "melhor avaliacao");
    }*/

    /* 
    public void criarRelatorioMidiasComMaisVisualizacoes(HashMap<Integer,Catalogo> listCatalogo) {
        LinkedList<Catalogo> list = 
        streamDefault(null, 
                     (a, b) -> { return Integer.compare(a.getAudiencia(), b.getAudiencia()); },
                     listCatalogo);

        exibirRelatorioCatalogo(list, "mais visualizadas");
    }*/
   



    /******************************** RELATORIOS DE ACORDO COM O GENERO ************************************ */
    /**
     * Método que retorna relatório por genero com 10 midias
     * @param genero
     * @return String
     */
    /* 
    public void relatorioPorGeneroAudiencia(String genero, HashMap<Integer,Catalogo> listCatalogo) {
        LinkedList<Catalogo> list = 
        streamDefault(c -> c.getGenero().equals(genero), 
                     (a, b) -> { return Integer.compare(a.getAudiencia(), b.getAudiencia()); },
                     listCatalogo);

        exibirRelatorioCatalogo(list, "mais audiencia");
    }*/
    /**
     * Metodo que retorna relatorio por genero com 10 midias mais bem avaliadas
     * @return String
     * @param genero
     */
    /* 
    public void relatorioPorGeneroAvaliacao(String genero, HashMap<Integer,Catalogo> listCatalogo) {
        LinkedList<Catalogo> list = 
        streamDefault(c -> c.getGenero().equals(genero),
                     (a, b) -> { return a.mediaAvaliacao().compareTo(b.mediaAvaliacao()); },
                     listCatalogo);

        exibirRelatorioCatalogo(list, "melhor avaliacao por genero");
    }*/

  
  



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
