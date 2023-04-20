import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

public class ClienteTest {
    Cliente cliente;
    Serie serie1, serie2, serie3;
    LinkedList<Serie> listaComTresSeries;

    @BeforeEach
    public void prepare() {
        cliente = new Cliente("Nome Cliente", "login", "Senha");
        listaComTresSeries = new LinkedList<>();

        serie1 = new Serie(0, "The Blacklist", "02/02/2017", "Suspense", "EN", 10);
        serie2 = new Serie(1, "Friends", "05/08/1999", "Comedia", "EN", 9);
        serie3 = new Serie(2, "Suits", "20/10/2015", "Drama", "EN", 5);

        listaComTresSeries.add(serie1);
        listaComTresSeries.add(serie2);
        listaComTresSeries.add(serie3);
    }

    @Test
    public void deveAdicionarSeriesNaListaDeSeriesParaVer() {
        for (Serie serie : listaComTresSeries) {
            cliente.adicionarNaLista(serie);
        }

        assertEquals(3, cliente.getListaParaVer().size());
    }

    @Test
    public void deveRetirarSerieDaListaParaVer() {
        for (Catalogo serie : listaComTresSeries) {
            cliente.adicionarNaLista(serie);
        }

        cliente.removerDaLista("The Blacklist");

        assertEquals(2, cliente.getListaParaVer().size());
    }

    @Test
    public void naoDeveRetirarSerieDaListaCasoSeriePassadaComoParametroNaoExista() {
        for (Serie serie : listaComTresSeries) {
            cliente.adicionarNaLista(serie);
        }

        cliente.removerDaLista("Não Existo");

        assertEquals(3, cliente.getListaParaVer().size());
    }

    @Test
    public void deveFiltrarSeriePorGeneroDeAcordoComParametroPassado() {
        for (Catalogo serie : listaComTresSeries) {
            cliente.adicionarNaLista(serie);
        }

        LinkedList<Catalogo> listaFiltrada = cliente.filtrarPorGenero("Comedia");

        assertEquals(1, listaFiltrada.size());
    }
}