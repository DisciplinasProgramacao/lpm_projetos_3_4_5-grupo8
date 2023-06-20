import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import java.math.BigDecimal;

public class PlataformaStreamingTest {
    private PlataformaStreaming plataforma1;
    private Cliente cliente1, clienteLogado;
    private Serie serie1, serie2;
    private Filme filme1, filme2, filme3;

    @BeforeEach
    public void setUp() throws IllegalArgumentException, IOException {
        plataforma1 = new PlataformaStreaming("Netflix");
        cliente1 = new Cliente("Ana Souza", "aninha12", "123");
        clienteLogado = new Cliente("To logado", "logado", "login");

        serie1 = new Serie("The Blacklist", "02/02/2017", "Suspense", "EN", 10);
        serie2 = new Serie("Black mirror", "05/05/2018", "Policial", "PT", 10);

        filme1 = new Filme("O Poderoso Chefão 1", "01/01/1972", "Drama", "EN", 120);
        filme2 = new Filme("O Poderoso Chefão 2", "01/01/1974", "Drama", "EN", 120);
        filme3 = new Filme("Minions", "30/06/2022", "Comedia", "EN", 180);

        plataforma1.adicionarCliente(clienteLogado);
    }

    @Test
    public void naoDeveRetornarExcecaoCasoClienteSejaAdicionadoNaPlataforma() {
        assertDoesNotThrow(() -> plataforma1.adicionarCliente(cliente1));
    }

    @Test
    public void deveAdicionarSeriesNaPlataforma() throws IOException {
        plataforma1.adicionarCatalogo(serie1);
        plataforma1.adicionarCatalogo(serie2);

        assertTrue(plataforma1.getCatalogo().contains("The Blacklist"));
        assertTrue(plataforma1.getCatalogo().contains("Black mirror"));
    }

    @Test
    public void deveRetornarClienteAtualCasoLoginSejaEfetuadoComSucesso() throws Exception {
        plataforma1.adicionarCliente(cliente1);
        plataforma1.login("aninha12", "123");

        assertEquals(cliente1, plataforma1.getClienteAtual());
    }

    @Test
    public void deveRetornarNullPointerExceptionAoBuscarClienteAtualCasoClienteInformeDadosDeLoginInvalidos() throws Exception {
        plataforma1.adicionarCliente(cliente1);
        plataforma1.login("aninha12", "senhaErrada");
        Assertions.assertThrows(NullPointerException.class, () -> plataforma1.getClienteAtual());
    }

    @Test
    public void deveRetornarNullPointerExceptionAoBuscarClienteAtualLogoffSejaEfetuado() throws IllegalArgumentException, IOException {
        plataforma1.adicionarCliente(cliente1);
        plataforma1.login("aninha12", "123");
        plataforma1.logoff();
        Assertions.assertThrows(NullPointerException.class, () -> plataforma1.getClienteAtual());
    }

    @Test
    public void deveAdicionarFilmesNaPlataforma() throws IOException {
        plataforma1.adicionarCatalogo(filme1);
        plataforma1.adicionarCatalogo(filme2);
        plataforma1.adicionarCatalogo(filme3);

        assertTrue(plataforma1.getCatalogo().contains("O Poderoso Chefão 1"));
        assertTrue(plataforma1.getCatalogo().contains("O Poderoso Chefão 2"));
        assertTrue(plataforma1.getCatalogo().contains("Minions"));
    }

    @Test
    public void deveFiltrarCatalogoPorQuantidadeDeFilmesPorGenero() throws IOException {
        plataforma1.adicionarCatalogo(filme1);
        plataforma1.adicionarCatalogo(filme2);
        plataforma1.adicionarCatalogo(filme3);

        assertEquals(2, plataforma1.filtrarCatalogo("Drama").size());
    }

    @Test
    public void deveFiltrarCatalogoPorIdioma() throws IOException {
        plataforma1.adicionarCatalogo(serie1);
        plataforma1.adicionarCatalogo(filme2);

        assertNotNull(plataforma1.filtrarCatalogo("EN").contains(serie1));
        assertNotNull(plataforma1.filtrarCatalogo("EN").contains(filme2));
    }

    @Test
    public void deveFiltrarCatalogoPorQtdEpisodio() throws IOException {
        plataforma1.adicionarCatalogo(serie1);
        plataforma1.adicionarCatalogo(serie2);

        assertEquals(2, plataforma1.filtrarPorQtdEpisodios(10).size());
    }

    @Test
    public void deveFiltrarporDuracao() throws IOException {
        plataforma1.adicionarCatalogo(filme1);
        plataforma1.adicionarCatalogo(filme2);
        plataforma1.adicionarCatalogo(filme3);

        assertEquals(2, plataforma1.filtrarPorDuracao(120).size());
    }

    @Test
    public void deveBuscarMidiaPeloNome() throws IOException {
        plataforma1.adicionarCatalogo(serie1);
        plataforma1.adicionarCatalogo(filme2);

        assertNotNull(plataforma1.buscarCatalogo("The Blacklist"));
        assertNotNull(plataforma1.buscarCatalogo("O Poderoso Chefão 2"));
    }

    @Test
    public void deveRetornarNullPointerExceptionCasoNaoEncontreMidiaPeloNome() {
        Assertions.assertThrows(NullPointerException.class, () -> plataforma1.buscarCatalogo("Não serei encontrada"));
    }

    @Test
    public void deveDeveLancarExcecaoCasoAssistaMidiaComSucesso() throws IOException {
        plataforma1.login("logado", "login");
        plataforma1.adicionarCatalogo(serie1);
        plataforma1.adicionarCatalogo(filme2);

        plataforma1.adicionarMidiaNaListaParaVerFuturamente("The Blacklist");
        plataforma1.adicionarMidiaNaListaParaVerFuturamente("O Poderoso Chefão 2");

        assertDoesNotThrow(() -> plataforma1.assistirMidia("O Poderoso Chefão 2"));

        assertDoesNotThrow(() -> plataforma1.assistirMidia("The Blacklist"));
    }

    @Test
    public void deveRetornarIndexOutOfBoundsExceptionCasoSoliciteAssistirMidiaQueNaoExistaNaListaParaVer() throws IOException {
        plataforma1.login("logado", "login");
        plataforma1.adicionarCatalogo(serie1);

        plataforma1.adicionarMidiaNaListaParaVerFuturamente("The Blacklist");

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> plataforma1.assistirMidia("O Poderoso Chefão 2"));
    }

    @Test
    public void deveRetornarListaDeMidiasJaAssistidas() throws IOException {
        plataforma1.login("logado", "login");
        plataforma1.adicionarCatalogo(filme2);

        plataforma1.adicionarMidiaNaListaParaVerFuturamente("O Poderoso Chefão 2");
        plataforma1.assistirMidia("O Poderoso Chefão 2");

        assertTrue(plataforma1.visualizarListaDeAssistidos().contains("O Poderoso Chefão 2"));
    }

    @Test
    public void deveAdicionarAvaliacaoEmUmaMidiaJaAssistidaComSucesso() throws IOException {
        plataforma1.login("logado", "login");
        plataforma1.adicionarCatalogo(filme2);

        plataforma1.adicionarMidiaNaListaParaVerFuturamente("O Poderoso Chefão 2");
        plataforma1.assistirMidia("O Poderoso Chefão 2");

        assertTrue(plataforma1.adicionarAvaliacao(5, "", filme2).toString().contains("Nota: 5"));
    }

    @Test
    public void deveRetornarFalsoCasoTenteAvaliarMidiaNaoAssistida() throws IOException {
        plataforma1.login("logado", "login");
        plataforma1.adicionarCatalogo(filme2);

        plataforma1.adicionarMidiaNaListaParaVerFuturamente("O Poderoso Chefão 2");

        assertNull(plataforma1.adicionarAvaliacao(5, "", filme2));
    }

    @Test
    public void deveRetornarFalsoCasoTenteAvaliarDuasVezesAhMesmaMidia() throws IOException {
        plataforma1.login("logado", "login");
        plataforma1.adicionarCatalogo(filme2);

        plataforma1.adicionarMidiaNaListaParaVerFuturamente("O Poderoso Chefão 2");
        plataforma1.assistirMidia("O Poderoso Chefão 2");

        plataforma1.adicionarAvaliacao(5, "", filme2);
        assertNull(plataforma1.adicionarAvaliacao(5, "", filme2));
    }

    @Test
    public void naoDeveAdicionarComentarioEmAvaliacaoCasoClienteNaoSejaEspecialista() throws IOException {
        plataforma1.login("logado", "login");
        plataforma1.adicionarCatalogo(filme2);

        plataforma1.adicionarMidiaNaListaParaVerFuturamente("O Poderoso Chefão 2");
        plataforma1.assistirMidia("O Poderoso Chefão 2");

        Avaliacao avaliacaoFeita = plataforma1.adicionarAvaliacao(5, "", filme2);
        plataforma1.adicionarComentarioAvaliacaoExistente(avaliacaoFeita, "filme maravilhoso");

        assertFalse(plataforma1.visualizarListaDeAssistidos().contains("filme maravilhoso"));
    }

    @Test
    public void deveRetornarMediaDeAvaliacoesQueUmaMidiaRecebeu() throws IOException {
        plataforma1.login("logado", "login");
        plataforma1.adicionarCatalogo(filme2);

        plataforma1.adicionarMidiaNaListaParaVerFuturamente("O Poderoso Chefão 2");
        plataforma1.assistirMidia("O Poderoso Chefão 2");

        plataforma1.adicionarAvaliacao(5, "", filme2);
        plataforma1.logoff();

        plataforma1.adicionarCliente(cliente1);
        plataforma1.login("aninha12", "123");
        plataforma1.adicionarMidiaNaListaParaVerFuturamente("O Poderoso Chefão 2");
        plataforma1.assistirMidia("O Poderoso Chefão 2");

        plataforma1.adicionarAvaliacao(3, "", filme2);

        assertEquals(new BigDecimal(4.0), plataforma1.mediaAvaliacao(filme2));
    }
}
