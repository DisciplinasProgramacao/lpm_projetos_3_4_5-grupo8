import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;


public class SerieTest {
    Serie serie;

    @BeforeEach
    public void setUp() {
        serie = new Serie(0, "The Blacklist", "02/02/2017", "Suspense", "EN", 10);
    }

    @Test
    public void deveAdicionarRegistroNaAudiencia(){
        serie.registrarAudiencia();
        serie.registrarAudiencia();
        serie.registrarAudiencia();
        
        assertEquals(3,serie.getAudiencia());
    }
}