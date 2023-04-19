import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;


public class PlataformaStreamingTest {
    private PlataformaStreaming plataforma1;
    private Cliente cliente1;
    private Serie serie1, serie2;


    @BeforeEach
    public void setUp() {
        plataforma1 = new PlataformaStreaming("Netflix");
        cliente1 = new Cliente("Ana Souza", "123");
        serie1 = new Serie("The Blacklist", "Suspense", "EN", 10);
        serie2 = new Serie("Black mirror", "Suspense", "PT", 10);
    }

    @Test
    public void deveAdicionarSerie() {
        plataforma1.adicionarSerie(serie1);
        assertEquals("The Blacklist", plataforma1.getSeries());
    }

    @Test
    public void deveAdicionarCliente() {
        plataforma1.adicionarCliente(cliente1);
        assertEquals("Ana Souza", plataforma1.getClientes());
    }

     
    @Test
    public void deveFiltrarSeriePorGenero(){
        plataforma1.adicionarSerie(serie1);
        assertEquals(1, plataforma1.filtrarPorGenero("Suspense"));
    }

    /*
    @Test
    public void deveFiltrarSeriePorIdioma(){
        serie1 = new Serie("The Blacklist", "Suspense", "EN", 10);
        plataforma1.adicionarSerie(serie1);
        assertEquals(1, plataforma1.filtrarPorIdioma("EN").size());
    }

    @Test
    public void deveFiltrarSeriePorQtdEpisodio(){
        serie1 = new Serie("The Blacklist", "Suspense", "EN", 10);
        plataforma1.adicionarSerie(serie1);
        assertEquals(1, plataforma1.filtrarPorQtdEpisodio(10).size());
    }

    @Test
    public void deveRegistrarAudiencia(){
        plataforma1.adicionarSerie(serie1);
        plataforma1.registrarAudiencia(serie1);
        assertEquals(1, plataforma1.getAudiencia().size());
    }
    */

    @Test
    public void deveRealizarLogin(){
        plataforma1.adicionarCliente(cliente1);
        plataforma1.login(cliente1);
        assertEquals(1, plataforma1.getClientes());
    }

    @Test
    public void deveRealizarLogoff(){
        plataforma1.adicionarCliente(cliente1);
        plataforma1.logoff(cliente1);
        assertEquals(0, plataforma1.getClientes().size());
    }

    @Test
    public void deveBuscarSeriesPorNome(){
        plataforma1.adicionarSerie(serie1);
        assertEquals("The Blacklist", plataforma1.buscarSerie("The Blacklist"));
    }

}
