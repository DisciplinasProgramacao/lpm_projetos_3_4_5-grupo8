import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class PlataformaStreamingTest {
    private PlataformaStreaming plataforma1;
    private Cliente cliente1;
    private Serie serie1, serie2;
    private Filme filme1, filme2;

    @BeforeEach
    public void setUp() {
        plataforma1 = new PlataformaStreaming("Netflix");
        cliente1 = new Cliente("Ana Souza", "aninha12", "123");
        serie1 = new Serie(0, "The Blacklist", "02/02/2017", "Suspense", "EN", 10);
        serie2 = new Serie(1, "Black mirror", "05/05/2018", "Terror", "PT", 10);
        filme1 = new Filme(3, "O Poderoso Chefão", "01/01/1972", "Drama", "EN", 120);
        filme2 = new Filme(4, "O Poderoso Chefão 2", "01/01/1974", "Drama", "EN", 120);
    }

    @Test
    public void deveadicionarCatalogo() {
        plataforma1.adicionarCatalogo(serie1);
        plataforma1.adicionarCatalogo(filme1);
        assertEquals("The Blacklist, O Poderoso Chefão", plataforma1.getCatalogo());
    }

    @Test
    public void deveAdicionarCliente() {
        plataforma1.adicionarCliente(cliente1);
        assertEquals("aninha12", plataforma1.getClientes());
    }

    @Test
    public void deveFiltrarPorGenero() {
        plataforma1.adicionarCatalogo(serie1);
        plataforma1.adicionarCatalogo(filme2);
        plataforma1.adicionarCatalogo(filme1);
        assertEquals(2, plataforma1.filtrarPorGenero("Drama").size());
    }

    @Test
    public void deveFiltrarPorIdioma(){
        plataforma1.adicionarCatalogo(serie1);
        plataforma1.adicionarCatalogo(filme2);
        assertNotNull(plataforma1.filtrarPorIdioma("EN").contains(serie1));
        assertNotNull(plataforma1.filtrarPorIdioma("EN").contains(filme2));
    }

    @Test
    public void deveFiltrarSeriePorQtdEpisodio(){
        plataforma1.adicionarCatalogo(serie1);
        assertEquals(1, plataforma1.filtrarPorQtdEpisodios(10).size());
    }

    @Test
    public void deveFiltrarFilmePorIdioma(){
        plataforma1.adicionarCatalogo(filme1);
        assertNotNull(plataforma1.filtrarPorIdioma("EN").contains(filme1));
    }
/*
    @Test
    public void deveRegistrarAudiencia(){
        plataforma1.adicionarCatalogo(serie1);
        plataforma1.registrarAudiencia(serie1);
        assertEquals(1, plataforma1.getAudiencia().size());
    }
    

    @Test
    public void deveRealizarLogin() {
        plataforma1.adicionarCliente(cliente1);
        plataforma1.login("aninha12", "123");
        assertEquals(cliente1, plataforma1.getClienteAtual());
    }

    @Test
    public void deveRealizarLogoff() {
        plataforma1.adicionarCliente(cliente1);
        plataforma1.login("aninha12", "123");
        plataforma1.logoff();
        assertNull(plataforma1.getClienteAtual());
    }
    */
    @Test
    public void deveBuscarSeriesPorNomeComToString() {
        plataforma1.adicionarCatalogo(serie1);
        plataforma1.adicionarCatalogo(filme2);
        assertEquals("The Blacklist, 02/02/2017, EN, Suspense, 10 episódios", plataforma1.buscarCatalogo("The Blacklist").toString());
        assertEquals("O Poderoso Chefão 2, 01/01/1974, EN, Drama, 120 min", plataforma1.buscarCatalogo("O Poderoso Chefão 2").toString());
    }
    
}
