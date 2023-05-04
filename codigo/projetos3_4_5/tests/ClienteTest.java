import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

public class ClienteTest {
    Cliente cliente;
    Serie serie1, serie2, serie3;
    Filme filme1, filme2, filme3;
    LinkedList<Catalogo> listacomquatroCatalogos;

    @BeforeEach
    public void prepare() {
        cliente = new Cliente("Nome Cliente", "login", "Senha");
        listacomquatroCatalogos = new LinkedList<>();

        serie1 = new Serie(0, "The Blacklist", "02/02/2017", "Suspense", "EN", 10);
        serie2 = new Serie(1, "Friends", "05/08/1999", "Comedia", "EN", 9);
        serie3 = new Serie(2, "Suits", "20/10/2015", "Drama", "EN", 5);
        filme1 = new Filme(3, "O Poderoso Chefão", "01/01/1972", "Drama", "EN", 120);
        filme2 = new Filme(4, "O Poderoso Chefão 2", "01/01/1974", "Drama", "EN", 120);
        filme3 = new Filme(5, "Minions", "30/06/2022", "Comedia", "EN", 180);

        listacomquatroCatalogos.add(serie1);
        listacomquatroCatalogos.add(serie2);
        listacomquatroCatalogos.add(filme1);
        listacomquatroCatalogos.add(filme2);
    }

    @Test
    public void deveAdicionarCatalogoNaListaParaVer() {
        for (Catalogo midia : listacomquatroCatalogos) {
            cliente.adicionarNaLista(midia);
        }

        assertEquals(4, cliente.getListaParaVer().size());
    }

    @Test
    public void deveRetirarCatalogoDaListaParaVer() {
        for (Catalogo midia : listacomquatroCatalogos) {
            cliente.adicionarNaLista(midia);
        }

        cliente.retirarDaLista("The Blacklist");

        assertEquals(3, cliente.getListaParaVer().size());
    }

    @Test
    public void naoDeveRetirarCatalogoDaListaCasoSeriePassadaComoParametroNaoExista() {
        for (Catalogo midia : listacomquatroCatalogos) {
            cliente.adicionarNaLista(midia);
        }

        cliente.retirarDaLista("Não Existo");

        assertEquals(4, cliente.getListaParaVer().size());
    }

    @Test
    public void deveFiltrarCatalogoPorGeneroDeAcordoComParametroPassado() {
        for (Catalogo serie : listacomquatroCatalogos) {
            cliente.adicionarNaLista(serie);
        }

        LinkedList<Catalogo> listaFiltrada = cliente.filtrarPorGenero("Comedia");

        assertEquals(1, listaFiltrada.size());
    }
    @Test
    public void deveAdicionarCatalogoNaListaJaAssistida(){
        for (Catalogo serie : listacomquatroCatalogos) {
            cliente.adicionarNaLista(serie);
        }
        cliente.registrarAudiencia(filme1);
        assertEquals(1, cliente.getListaJaVistas().size());
    }
    
}