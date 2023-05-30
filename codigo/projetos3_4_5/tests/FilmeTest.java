import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;

public class FilmeTest {
    Filme filme;

    @BeforeEach
    public void setUp() {
        filme = new Filme("O Poderoso Chefão", "02/02/2017", "Suspense", "EN", 100);
    }

    @Test
    public void deveAdicionarRegistroNaAudienciaFilme(){
        filme.registrarAudiencia();
        filme.registrarAudiencia();
        filme.registrarAudiencia();
        
        assertEquals(3,filme.getAudiencia());
    }
}