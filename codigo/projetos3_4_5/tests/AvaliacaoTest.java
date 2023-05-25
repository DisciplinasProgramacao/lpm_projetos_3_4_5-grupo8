import org.junit.jupiter.api.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

public class AvaliacaoTest {
    Avaliacao avaliacao;
    Cliente clienteNormal, clienteEspecialista;
    Serie serie1, serie2, serie3;
    Filme filme1, filme2, filme3;
    Catalogo catalogo;

    @BeforeEach
    public void prepare() throws Exception {
        clienteNormal = new Cliente("Nome Cliente Normal", "login", "Senha");
        clienteEspecialista = new Cliente("Nome Cliente Especialista", "login", "Senha");

        serie1 = new Serie("The Blacklist", "02/02/2017", "Suspense", "EN", 10);
        serie2 = new Serie("Friends", "05/08/1999", "Comedia", "EN", 9);
        serie3 = new Serie("Suits", "20/10/2015", "Drama", "EN", 5);
        filme1 = new Filme("O Poderoso Chefão", "01/01/1972", "Drama", "EN", 120);
        filme2 = new Filme("O Poderoso Chefão 2", "01/01/1974", "Drama", "EN", 120);
        filme3 = new Filme("Minions", "30/06/2022", "Comedia", "EN", 180);

}

    @Test
    public void avaliarUmaSerie(){
        clienteNormal.adicionarAvaliacao(5, "", serie1);
        assertEquals(new BigDecimal(5), serie1.mediaAvaliacao());
    }

//    @Test
//    public void avaliarUmaSerieComComentario(){
//        avaliacao.init(clienteEspecialista.getLogin(), 3, "mais ou menos");
//        assertEquals("Nota: 3\nComentário: mais ou menos", avaliacao.toString());
//    }

    @Test
    public void deveRetornarMediaAvaliacaoDeUmaSerie(){
        clienteNormal.adicionarAvaliacao(5, "", serie1);
        clienteEspecialista.adicionarAvaliacao(4, "", serie1);
        assertEquals(new BigDecimal(4.5), serie1.mediaAvaliacao());
    }

    @Test
    public void AvaliarUmaFilme(){
        clienteNormal.adicionarAvaliacao(1, "", filme1);
        assertEquals(new BigDecimal(1), filme1.mediaAvaliacao());
    }

//    @Test
//    public void AvaliarUmaFilmeComComentario(){
//        avaliacao.init(clienteEspecialista.getLogin(), 4, "ate que é bom");
//        assertEquals("Nota: 3\nComentário: ate que é bom", avaliacao.toString());
//    }

    @Test
    public void deveRetornarMediaAvaliacaoDeUmaFilme(){
        clienteNormal.adicionarAvaliacao(1, "", filme1);
        clienteEspecialista.adicionarAvaliacao(2, "", filme1);
        assertEquals(new BigDecimal(1.5), filme1.mediaAvaliacao());
    }


}
