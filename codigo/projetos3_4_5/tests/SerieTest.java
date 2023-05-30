import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;


public class SerieTest {
    Serie serie;
    
    @BeforeEach
    public void setUp() {
        serie = new Serie("The Blacklist", "02/02/2017", "Suspense", "EN", 10);
    }

    @Test
    public void deveAdicionarRegistroNaAudienciaSerie(){
        serie.registrarAudiencia();
        serie.registrarAudiencia();
        serie.registrarAudiencia();
        
        assertEquals(3,serie.getAudiencia());
    }
}