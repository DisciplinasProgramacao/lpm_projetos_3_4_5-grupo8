import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

public class AssistidoTest {
    Assistido midiaAssistida;
    Filme filme;
    Avaliacao avaliacao;

    @BeforeEach
    public void prepare() {
        filme = new Filme("O Poderoso Chefão", "02/02/2017", "Suspense", "EN", 100);
        midiaAssistida = new Assistido(filme, LocalDate.now());
        avaliacao = new Avaliacao("Joao Feliz", 4);
    }

    @Test
    public void deveRetornarAvaliacaoEfetuada(){
        assertEquals(avaliacao, midiaAssistida.adicionarAvaliacao(avaliacao));
        
    }

    @Test
    public void deveAdicionarAvaliacaoNaMidiaAssistida(){
        midiaAssistida.adicionarAvaliacao(avaliacao);
        assertTrue(filme.mostrarAvaliacoes().contains("Joao Feliz")); 
    }

    @Test
    public void deveConterAvaliacaoNaStringCasoPossuaAvaliacoes(){
        midiaAssistida.adicionarAvaliacao(avaliacao);
        assertTrue(midiaAssistida.toString().contains("Joao Feliz"));         
    }

    @Test
    public void naoDeveConterAvaliacaoNaStringCasoNaoPossuaAvaliacoes(){
        assertFalse(midiaAssistida.toString().contains("Joao Feliz"));         
    }

}
