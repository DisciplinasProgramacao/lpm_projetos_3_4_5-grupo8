import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

        serie1 = new Serie("The Blacklist", "02/02/2017", "SUSPENSE", "EN", 10, false);
        serie2 = new Serie("Friends", "05/08/1999", "COMEDIA", "EN", 9, false);
        serie3 = new Serie("Suits", "20/10/2015", "DRAMA", "EN", 5, false);
        filme1 = new Filme("O Poderoso Chefão", "01/01/1972", "DRAMA", "EN", 120, false);
        filme2 = new Filme("O Poderoso Chefão 2", "01/01/1974", "DRAMA", "EN", 120, false);
        filme3 = new Filme("Minions", "30/06/2022", "COMEDIA", "EN", 180, false);

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
    @Test
    public void deveFiltrardasDuasListasPorGenero(){
        cliente.adicionarNaLista(filme1);
        cliente.adicionarNaLista(filme2);
        cliente.adicionarNaLista(filme3);
        cliente.adicionarNaLista(serie1);
        cliente.adicionarNaLista(serie2);
        cliente.adicionarNaLista(serie3);
        cliente.registrarAudiencia(filme1);
        cliente.registrarAudiencia(filme2);
        cliente.registrarAudiencia(serie1);
        assertEquals(3, cliente.filtrarPorGenero("Drama").size());
        assertEquals(2, cliente.filtrarPorGenero("Comedia").size());
        assertEquals(1, cliente.filtrarPorGenero("Suspense").size());
    }
    @Test
    public void deveFiltrarDasDuasListasPorIdioma(){
        cliente.adicionarNaLista(filme1);
        cliente.adicionarNaLista(filme2);
        cliente.adicionarNaLista(filme3);
        cliente.adicionarNaLista(serie1);
        cliente.adicionarNaLista(serie2);
        cliente.adicionarNaLista(serie3);
        cliente.registrarAudiencia(filme1);
        cliente.registrarAudiencia(filme2);
        cliente.registrarAudiencia(serie1);
        assertEquals(6, cliente.filtrarPorIdioma("EN").size());
    }
    @Test
    public void deveFiltrarDasDuasListasPorQuantidadeDeEpisodios(){
        cliente.adicionarNaLista(filme1);
        cliente.adicionarNaLista(filme2);
        cliente.adicionarNaLista(filme3);
        cliente.adicionarNaLista(serie1);
        cliente.adicionarNaLista(serie2);
        cliente.adicionarNaLista(serie3);
        cliente.registrarAudiencia(filme1);
        cliente.registrarAudiencia(filme2);
        cliente.registrarAudiencia(serie1);
        assertEquals(1, cliente.filtrarPorQtdEpisodios(10).size());
        assertEquals(1, cliente.filtrarPorQtdEpisodios(9).size());
        assertEquals(1, cliente.filtrarPorQtdEpisodios(5).size());
    }

    @Test
    public void naoEhEspecialista(){
        cliente.adicionarNaLista(filme1);
        cliente.adicionarNaLista(filme2);
        cliente.adicionarNaLista(filme3);
        cliente.adicionarNaLista(serie1);
        cliente.adicionarNaLista(serie2);
        cliente.adicionarNaLista(serie3);
        cliente.registrarAudiencia(filme1);
        cliente.registrarAudiencia(filme2);
        cliente.registrarAudiencia(serie1);
        cliente.tornarEspecialista();
        assertEquals(false, cliente.podeComentar());
    }

    @Test
    public void ehEspecialista(){
        cliente.adicionarNaLista(filme1);
        cliente.adicionarNaLista(filme2);
        cliente.adicionarNaLista(filme3);
        cliente.adicionarNaLista(serie1);
        cliente.adicionarNaLista(serie2);
        cliente.adicionarNaLista(serie3);
        cliente.registrarAudiencia(filme1);
        cliente.registrarAudiencia(filme2);
        cliente.registrarAudiencia(filme2);
        cliente.registrarAudiencia(serie1);
        cliente.registrarAudiencia(serie2);
        cliente.registrarAudiencia(serie3);
        cliente.tornarEspecialista();
        assertEquals(true, cliente.podeComentar());
    }

    @Test
    public void especialistaPodeComentar(){
        cliente.adicionarNaLista(filme1);
        cliente.adicionarNaLista(filme2);
        cliente.adicionarNaLista(filme3);
        cliente.adicionarNaLista(serie1);
        cliente.adicionarNaLista(serie2);
        cliente.adicionarNaLista(serie3);
        cliente.registrarAudiencia(filme1);
        cliente.registrarAudiencia(filme2);
        cliente.registrarAudiencia(serie1);
        cliente.registrarAudiencia(serie2);
        cliente.registrarAudiencia(serie3);
        cliente.tornarEspecialista();
        cliente.adicionarAvaliacao(5, "Muito bom", filme1);
    }

    @Test
    public void deveRetornarAvaliacaoCasoSejaEfetuadaComSucesso(){
        cliente.adicionarNaLista(filme1);
        cliente.registrarAudiencia(filme1);
        
        assertNotNull(cliente.adicionarAvaliacao(5, "Muito bom", filme1));
    }

    @Test
    public void deveRetornarNuloCasoClienteJaTenhaAvaliadoMidia(){
        cliente.adicionarNaLista(filme1);
        cliente.registrarAudiencia(filme1);
        cliente.adicionarAvaliacao(5, "Muito bom", filme1);
        
        assertNull(cliente.adicionarAvaliacao(5, "Muito bom", filme1));
    }

}