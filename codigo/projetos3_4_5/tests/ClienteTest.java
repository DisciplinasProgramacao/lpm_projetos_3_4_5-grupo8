
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class ClienteTest {
    Cliente cliente;
    Serie serie1, serie2, serie3;
    Lista<Serie> tresSeries;

    @BeforeEach
    public void prepare(){
        cliente = new Cliente("");
        tresSeries = new Lista<>();

        serie1 = new Serie("The Blacklist", "Suspense", "EN", 200);
        serie2 = new Serie("Friends", "Comédia", "EN", 100);
        serie3 = new Serie("Suits", "Drama", "EN", 200);

        tresSeries.add(serie1);
        tresSeries.add(serie2);
        tresSeries.add(serie3);
    }

    @Test
    public void deveAdicionarSeriesNaListaDeSeriesParaVer(){
        Serie arraySeries[] = new Serie[tresSeries.size()];

        for (Serie serie : arraySeries) {
            cliente.adicionarNaLista(serie);
        }

        assertEquals(3, cliente.getListaParaVer().size());
    }

    @Test
    public void deveRetirarSerieDaListaParaVer(){
        Serie arraySeries[] = new Serie[tresSeries.size()];

        for (Serie serie : arraySeries) {
            cliente.adicionarNaLista(serie);
        }

        cliente.retirarDaLista("The Blacklist");

        assertEquals(2, cliente.getListaParaVer().size());
    }

    @Test
    public void naoDeveRetirarSerieDaListaCasoSeriePassadaComoParametroNaoExista(){
        Serie arraySeries[] = new Serie[tresSeries.size()];

        for (Serie serie : arraySeries) {
            cliente.adicionarNaLista(serie);
        }

        cliente.retirarDaLista("Não Existo");

        assertEquals(3, cliente.getListaParaVer().size());
    }

}
