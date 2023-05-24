import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;


public class FilmeSerieTest {
    Serie serie;
    Filme filme;
    @BeforeEach
    public void setUp() {
        filme = new Filme("O Poderoso Chefão", "02/02/2017", "Suspense", "EN", 100);
        serie = new Serie("The Blacklist", "02/02/2017", "Suspense", "EN", 10);
    }

    @Test
    public void deveAdicionarRegistroNaAudienciaSerie(){
        serie.registrarAudiencia();
        serie.registrarAudiencia();
        serie.registrarAudiencia();
        
        assertEquals(3,serie.getAudiencia());
    }
    @Test
    public void deveAdicionarRegistroNaAudienciaFilme(){
        filme.registrarAudiencia();
        filme.registrarAudiencia();
        filme.registrarAudiencia();
        
        assertEquals(3,filme.getAudiencia());
    }
}