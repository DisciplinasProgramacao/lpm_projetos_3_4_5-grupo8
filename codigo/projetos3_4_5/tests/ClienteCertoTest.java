import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

public class ClienteCertoTest {
    ClienteCerto cliente;
    Serie serie1, serie2, serie3;
    LinkedList<Serie> ListaComTresSeries;

    @BeforeEach
    public void prepare(){
        cliente = new ClienteCerto("Laura Melo", "1234");
        ListaComTresSeries = new LinkedList<>();

        serie1 = new Serie("The Blacklist", "Suspense", "EN", 10);
        serie2 = new Serie("Friends", "Comedia", "EN", 9);
        serie3 = new Serie("Suits", "Drama", "EN", 5);

        ListaComTresSeries.add(serie1);
        ListaComTresSeries.add(serie2);
        ListaComTresSeries.add(serie3);
    }

    /* 
    @Test
    public void deveAdicionarSeriesNaListaDeSeriesParaVer(){
        Serie listaDeSerie = new LinkedList<Serie>();

        for (Serie serie : arraySeries) {
            cliente.adicionarNaLista(serie);
        }

        assertArrayEquals(arraySeries, cliente.getListaParaVer().toArray());
       
    }*/


    @Test
    public void deveFiltrarPorGenero(){
        Serie arraySeries[] = new Serie[ListaComTresSeries.size()];

        for (Serie serie : arraySeries) {
            cliente.adicionarNaLista(serie);
        }

        LinkedList<Serie> listaFiltrada = cliente.filtrarPorGenero("Comedia");

        assertEquals(1, listaFiltrada.size());
    }
}