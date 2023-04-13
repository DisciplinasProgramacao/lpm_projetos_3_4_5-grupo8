
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;


public class SerieTest {
    Serie serie;

    @BeforeEach
    public void setUp() {
        serie = new Serie("The Walking Dead", "Ação", "ingles", 2010);
    }

    @Test
    public void AdicionandoMaisUmRegistroAudiencia(){
        serie.registrarAudiencia();
        assertEquals(1,serie.getAudiencia());
    }
    @Test
    public void validarQuantidadeEpisodiosMenor3(){
        assertNotEquals("Quantidade de episódios inválida", serie.validarQuantidadeEpisodios(200));
    }

}
