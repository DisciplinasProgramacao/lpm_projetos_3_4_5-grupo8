import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;

public class SerieTest {
    Serie serie;
    Avaliacao avaliacao1, avaliacao2;

    @BeforeEach
    public void prepare() {
        serie = new Serie("The Blacklist", "02/02/2017", "Suspense", "EN", 10, false);
        avaliacao1 = new Avaliacao("eu", 4);
        avaliacao2 = new Avaliacao("voce", 5, "muito bom");
    }

    @Test
    public void deveAdicionarRegistroNaAudienciaSerie(){
        serie.registrarAudiencia();
        serie.registrarAudiencia();
        serie.registrarAudiencia();
        
        assertEquals(3,serie.getAudiencia());
    }

    @Test
    public void deveAdicionarAvaliacaoNaMidia(){
        serie.avaliarMidia(avaliacao1);
        serie.avaliarMidia(avaliacao2);

        assertTrue(serie.mostrarAvaliacoes().contains("Nota: 5"));
        assertTrue(serie.mostrarAvaliacoes().contains("Comentario: muito bom"));
    }

    @Test
    public void deveInformarQueMidiaNaoPossuiAvaliacoesCasoNenhumaTenhaSidoFeita(){
        System.out.println(serie.mostrarAvaliacoes());
        assertTrue(serie.mostrarAvaliacoes().contains("Ainda nao existem avaliacoes para essa midia"));
    }

    @Test
    public void deveRetornarAvaliacaoMediaDaMida(){
        serie.avaliarMidia(avaliacao1);
        serie.avaliarMidia(avaliacao2);

        assertEquals(new BigDecimal(4.5), serie.mediaAvaliacao());
    }

    @Test
    public void deveRetornarAvaliacaoMediaIgualAZeroCasoNaoPossuaAvaliacoes(){
        assertEquals(new BigDecimal(0.0), serie.mediaAvaliacao());
    }

    @Test
    public void deveLancarExcecaoCasoGeneroDoFilmeNaoExistaNoEnum(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Serie("Suits", "02/02/2017", "GeneroInvalido", "EN", 10, false));
    }

    @Test
    public void naoDeveLancarExcecaoCasoGeneroDoFilmeExistaNoEnum(){
        assertDoesNotThrow(() -> new Filme("Fullmetal Brotherhood","02/02/2003", "Anime", "EN", 100, false));
    }

}