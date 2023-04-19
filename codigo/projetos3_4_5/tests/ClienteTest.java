import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

public class ClienteTest {
    Cliente cliente;
    Serie serie1, serie2, serie3;
    LinkedList<Serie> listaComTresSeries;

    @BeforeEach
    public void prepare(){
        cliente = new Cliente("Nome Cliente", "Senha");
        listaComTresSeries = new LinkedList<>();

        serie1 = new Serie("The Blacklist", "Suspense", "EN", 10);
        serie2 = new Serie("Friends", "Comedia", "EN", 9);
        serie3 = new Serie("Suits", "Drama", "EN", 5);

        listaComTresSeries.add(serie1);
        listaComTresSeries.add(serie2);
        listaComTresSeries.add(serie3);
    }

    @Test
    public void deveAdicionarSeriesNaListaDeSeriesParaVer(){
        for (Serie serie : listaComTresSeries) {
            cliente.adicionarNaLista(serie);
        }

        assertEquals(3, cliente.getListaParaVer().size());
    }

    @Test
    public void deveRetirarSerieDaListaParaVer(){
        for (Serie serie : listaComTresSeries) {
            cliente.adicionarNaLista(serie);
        }

        cliente.retirarDaLista("The Blacklist");

        assertEquals(2, cliente.getListaParaVer().size());
    }

    @Test
    public void naoDeveRetirarSerieDaListaCasoSeriePassadaComoParametroNaoExista(){
        for (Serie serie : listaComTresSeries) {
            cliente.adicionarNaLista(serie);
        }

        cliente.retirarDaLista("Não Existo");

        assertEquals(3, cliente.getListaParaVer().size());
    }

    @Test
    public void deveFiltrarSeriePorGeneroDeAcordoComParametroPassado(){
        for (Serie serie : listaComTresSeries) {
            cliente.adicionarNaLista(serie);
        }

        LinkedList<Serie> listaFiltrada = cliente.filtrarPorGenero("Comedia");

        assertEquals(1, listaFiltrada.size());
    }
}