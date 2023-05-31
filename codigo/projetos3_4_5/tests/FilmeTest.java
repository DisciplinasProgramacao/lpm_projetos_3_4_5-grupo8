import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class FilmeTest {
    Filme filme;
    Avaliacao avaliacao1, avaliacao2;

    @BeforeEach
    public void setUp() {
        filme = new Filme("O Poderoso Chefão", "02/02/2017", "Suspense", "EN", 100);
        avaliacao1 = new Avaliacao("Joao Feliz", 4);
        avaliacao2 = new Avaliacao("Maria Animada", 5, "muito bom");
    }

    @Test
    public void deveAdicionarRegistroNaAudienciaFilme(){
        filme.registrarAudiencia();
        filme.registrarAudiencia();
        filme.registrarAudiencia();
        
        assertEquals(3,filme.getAudiencia());
    }


    @Test
    public void deveInformarQueMidiaNaoPossuiAvaliacoesCasoNenhumaTenhaSidoFeita(){
        System.out.println(filme.mostrarAvaliacoes());
        assertTrue(filme.mostrarAvaliacoes().contains("Ainda nao existem avaliacoes para essa midia"));
    }

    @Test
    public void deveRetornarAvaliacaoMediaDaMida(){
        filme.avaliarMidia(avaliacao1);
        filme.avaliarMidia(avaliacao2);

        assertEquals(new BigDecimal(4.5), filme.mediaAvaliacao());
    }

    @Test
    public void deveRetornarAvaliacaoMediaIgualAZeroCasoNaoPossuaAvaliacoes(){
        assertEquals(new BigDecimal(0.0), filme.mediaAvaliacao());
    }    
}